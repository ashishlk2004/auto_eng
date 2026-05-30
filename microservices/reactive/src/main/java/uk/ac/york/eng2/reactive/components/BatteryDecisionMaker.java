package uk.ac.york.eng2.reactive.components;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.york.eng2.reactive.dto.BatteryDecisionPayload;
import uk.ac.york.eng2.reactive.dto.RatePayload;
import uk.ac.york.eng2.reactive.events.BatteryDecisionProducer;
import uk.ac.york.eng2.reactive.util.ComponentRegistration;
import uk.ac.york.eng2.reactive.util.TopicSlotService;

/**
 * Reactive Component 3: BatteryDecisionMaker.
 *
 * Triggered whenever new {@code rate} slots arrive on {@code current-import-rate} or
 * {@code current-export-rate}. Pre-activation requires both slots to be available. The activation
 * section decides on the next battery target state and publishes it onto {@code battery-decision}.
 */
@Singleton
@KafkaListener(groupId = "battery-decision-maker", offsetReset = OffsetReset.EARLIEST)
public class BatteryDecisionMaker {

    public static final String NAME = "BatteryDecisionMaker";
    public static final String TOPIC = "battery-decision";

    private static final Logger LOG = LoggerFactory.getLogger(BatteryDecisionMaker.class);

    @Inject
    private BatteryDecisionProducer producer;

    @Inject
    private TopicSlotService slotService;

    @Inject
    private ComponentRegistration registration;

    @Value("${reactive.battery.import-cheap-threshold:12}")
    private double importCheapThreshold;

    @Value("${reactive.battery.export-expensive-threshold:12}")
    private double exportExpensiveThreshold;

    @Value("${reactive.battery.charge-target:80}")
    private int chargeTarget;

    @Value("${reactive.battery.discharge-floor:20}")
    private int dischargeFloor;

    private volatile Double importRate;
    private volatile Double exportRate;

    @PostConstruct
    void register() {
        registration.ensureExists(NAME);
    }

    @Topic("${topic.current-import-rate:current-import-rate}")
    public void onImportRate(RatePayload payload) {
        importRate = payload.getRate();
        slotService.saveDouble("current-import-rate", "rate", importRate);
        tryActivate();
    }

    @Topic("${topic.current-export-rate:current-export-rate}")
    public void onExportRate(RatePayload payload) {
        exportRate = payload.getRate();
        slotService.saveDouble("current-export-rate", "rate", exportRate);
        tryActivate();
    }

    void tryActivate() {
        if (importRate == null || exportRate == null) {
            return; // Pre-activation: skip if any input is missing
        }
        String decision = decide(importRate, exportRate);
        producer.publish(TOPIC, new BatteryDecisionPayload(decision));
        slotService.saveText(TOPIC, "targetState", decision);
        LOG.info("Battery decision: {} (importRate={}, exportRate={})", decision, importRate, exportRate);
    }

    /** Pure decision logic, easy to unit-test. */
    String decide(double importRate, double exportRate) {
        if (importRate <= importCheapThreshold) {
            return "import_to " + chargeTarget;
        } else if (exportRate >= exportExpensiveThreshold) {
            return "export_to " + dischargeFloor;
        } else {
            return "sell_excess";
        }
    }
}
