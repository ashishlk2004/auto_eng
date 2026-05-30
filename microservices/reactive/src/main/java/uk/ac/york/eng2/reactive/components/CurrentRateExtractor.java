package uk.ac.york.eng2.reactive.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.york.eng2.reactive.dto.AgileRatesPayload;
import uk.ac.york.eng2.reactive.dto.RatePayload;
import uk.ac.york.eng2.reactive.events.CurrentRateProducer;
import uk.ac.york.eng2.reactive.util.ComponentRegistration;
import uk.ac.york.eng2.reactive.util.TopicSlotService;

import java.time.Instant;
import java.util.Iterator;
import java.util.Optional;

/**
 * Reactive Component 2: CurrentRateExtractor.
 *
 * Triggered both by new records on {@code agile-rates}, and by a periodic schedule (every 30
 * minutes). Extracts the import and export rates that apply to the current half-hour slot
 * and publishes them onto the {@code current-import-rate} and {@code current-export-rate} topics.
 */
@Singleton
@KafkaListener(groupId = "current-rate-extractor", offsetReset = OffsetReset.EARLIEST)
public class CurrentRateExtractor {

    public static final String NAME = "CurrentRateExtractor";
    public static final String IMPORT_TOPIC = "current-import-rate";
    public static final String EXPORT_TOPIC = "current-export-rate";

    private static final Logger LOG = LoggerFactory.getLogger(CurrentRateExtractor.class);

    @Inject
    private CurrentRateProducer producer;

    @Inject
    private TopicSlotService slotService;

    @Inject
    private ComponentRegistration registration;

    private final ObjectMapper mapper = new ObjectMapper();

    private volatile String latestJson;
    private volatile Instant latestValidFrom;
    private volatile Instant latestValidTo;

    @PostConstruct
    void register() {
        registration.ensureExists(NAME);
    }

    @Topic("${topic.agile-rates:agile-rates}")
    public void onAgileRates(AgileRatesPayload payload) {
        // Save slot values as they arrive
        if (payload.getJson() != null) {
            latestJson = payload.getJson();
            slotService.saveText("agile-rates", "json", latestJson);
        }
        if (payload.getValidFrom() != null) {
            latestValidFrom = payload.getValidFrom();
            slotService.saveTimestamp("agile-rates", "validFrom", latestValidFrom);
        }
        if (payload.getValidTo() != null) {
            latestValidTo = payload.getValidTo();
            slotService.saveTimestamp("agile-rates", "validTo", latestValidTo);
        }
        // Pre-activation: only proceed when all required inputs are present
        if (latestJson != null && latestValidFrom != null && latestValidTo != null) {
            extractAndPublish();
        }
    }

    @Scheduled(fixedDelay = "${reactive.rate-extract.fixed-delay:30m}", initialDelay = "30s")
    public void scheduledExtract() {
        // Time-based trigger: only proceed when all inputs are present
        if (latestJson != null && latestValidFrom != null && latestValidTo != null) {
            extractAndPublish();
        }
    }

    void extractAndPublish() {
        Instant now = Instant.now();
        Optional<double[]> rates = findRatesForInstant(latestJson, now);
        if (rates.isEmpty()) {
            LOG.warn("No current rate found for {} (valid range {} to {})", now, latestValidFrom, latestValidTo);
            return;
        }
        double importRate = rates.get()[0];
        double exportRate = rates.get()[1];

        producer.publishImport(IMPORT_TOPIC, new RatePayload(importRate));
        producer.publishExport(EXPORT_TOPIC, new RatePayload(exportRate));

        slotService.saveDouble(IMPORT_TOPIC, "rate", importRate);
        slotService.saveDouble(EXPORT_TOPIC, "rate", exportRate);

        LOG.info("Extracted current rates: import={}, export={}", importRate, exportRate);
    }

    /**
     * Returns [import, export] rates for the half-hour slot that contains {@code at}, if any.
     */
    Optional<double[]> findRatesForInstant(String json, Instant at) {
        try {
            JsonNode root = mapper.readTree(json);
            JsonNode rates = root.path("rates");
            if (rates.isMissingNode() || !rates.isArray()) {
                return Optional.empty();
            }
            Iterator<JsonNode> it = rates.elements();
            while (it.hasNext()) {
                JsonNode slot = it.next();
                Instant start = Instant.parse(slot.path("deliveryStart").asText());
                Instant end = Instant.parse(slot.path("deliveryEnd").asText());
                if (!at.isBefore(start) && at.isBefore(end)) {
                    double imp = slot.path("agileRate").path("result").path("rate").asDouble();
                    double exp = slot.path("agileOutgoingRate").path("result").path("rate").asDouble();
                    return Optional.of(new double[] { imp, exp });
                }
            }
        } catch (Exception ex) {
            LOG.warn("Could not parse agile rates JSON: {}", ex.getMessage());
        }
        return Optional.empty();
    }
}
