package uk.ac.york.eng2.reactive.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.york.cs.eng2.reactive.rates.api.RatesApi;
import uk.ac.york.cs.eng2.reactive.rates.model.AgileRatesResponse;
import uk.ac.york.eng2.reactive.dto.AgileRatesPayload;
import uk.ac.york.eng2.reactive.events.AgileRatesProducer;
import uk.ac.york.eng2.reactive.util.ComponentRegistration;
import uk.ac.york.eng2.reactive.util.TopicSlotService;

import java.time.Instant;

/**
 * Reactive Component 1: AgileRatesFetcher.
 *
 * Triggered by a time-based trigger ({@code on startup and every 12 hours after that}).
 * Calls the Agile Rates API and publishes an {@link AgileRatesPayload} record with three slots
 * ({@code json}, {@code validFrom}, {@code validTo}) onto the {@code agile-rates} topic.
 */
@Singleton
public class AgileRatesFetcher {

    public static final String NAME = "AgileRatesFetcher";
    public static final String TOPIC = "agile-rates";

    private static final Logger LOG = LoggerFactory.getLogger(AgileRatesFetcher.class);

    @Inject
    private RatesApi ratesApi;

    @Inject
    private AgileRatesProducer producer;

    @Inject
    private ComponentRegistration registration;

    @Inject
    private TopicSlotService slotService;

    // ObjectMapper configured to handle ZonedDateTime / Instant from the OpenAPI-generated
    // AgileRatesResponse, and to emit ISO-8601 strings rather than numeric timestamps so the
    // downstream JsonNode walk in CurrentRateExtractor can parse them.
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @PostConstruct
    void register() {
        registration.ensureExists(NAME);
    }

    @Scheduled(fixedDelay = "${reactive.agile-fetch.fixed-delay:12h}", initialDelay = "0s")
    public void fetchAndPublish() {
        try {
            AgileRatesResponse response = ratesApi.getRates();
            if (response == null || response.getRates() == null || response.getRates().isEmpty()) {
                LOG.warn("Empty rates response - skipping publication");
                return;
            }
            Instant validFrom = response.getRates().get(0).getDeliveryStart().toInstant();
            Instant validTo = response.getRates().get(response.getRates().size() - 1).getDeliveryEnd().toInstant();
            String json;
            try {
                json = mapper.writeValueAsString(response);
            } catch (JsonProcessingException ex) {
                LOG.warn("Could not serialise agile rates JSON: {}", ex.getMessage());
                json = "{}";
            }

            AgileRatesPayload payload = new AgileRatesPayload(json, validFrom, validTo);
            producer.publish(TOPIC, payload);

            // Save slots locally too (as the consumer would normally do)
            slotService.saveText(TOPIC, "json", json);
            slotService.saveTimestamp(TOPIC, "validFrom", validFrom);
            slotService.saveTimestamp(TOPIC, "validTo", validTo);

            LOG.info("Published agile rates valid from {} to {}", validFrom, validTo);
        } catch (Exception ex) {
            LOG.error("Failed to fetch / publish agile rates", ex);
        }
    }
}
