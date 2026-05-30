package uk.ac.york.eng2.reactive.components;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.york.cs.eng2.reactive.iot.api.ActuatorsApi;
import uk.ac.york.cs.eng2.reactive.iot.model.Actuator;
import uk.ac.york.cs.eng2.reactive.iot.model.ActuatorStateDTO;
import uk.ac.york.eng2.reactive.dto.BatteryDecisionPayload;
import uk.ac.york.eng2.reactive.util.ComponentRegistration;
import uk.ac.york.eng2.reactive.util.TopicSlotService;

import java.util.List;

/**
 * Reactive Component 4: BatteryActuatorController.
 *
 * Triggered by new records on the {@code battery-decision} topic. Calls IoTM via its generated
 * OpenAPI client to update the {@code target_state} of every battery actuator.
 */
@Singleton
@KafkaListener(groupId = "battery-actuator-controller", offsetReset = OffsetReset.EARLIEST)
public class BatteryActuatorController {

    public static final String NAME = "BatteryActuatorController";
    public static final String BATTERY_TYPE = "battery";

    private static final Logger LOG = LoggerFactory.getLogger(BatteryActuatorController.class);

    @Inject
    private ActuatorsApi actuatorsApi;

    @Inject
    private TopicSlotService slotService;

    @Inject
    private ComponentRegistration registration;

    @PostConstruct
    void register() {
        registration.ensureExists(NAME);
    }

    @Topic("${topic.battery-decision:battery-decision}")
    public void onDecision(BatteryDecisionPayload payload) {
        if (payload == null || payload.getTargetState() == null) {
            return; // Pre-activation: missing input
        }
        slotService.saveText("battery-decision", "targetState", payload.getTargetState());
        try {
            List<Actuator> batteries = actuatorsApi.list(BATTERY_TYPE);
            if (batteries == null) {
                return;
            }
            ActuatorStateDTO state = new ActuatorStateDTO();
            state.setTargetState(payload.getTargetState());
            for (Actuator a : batteries) {
                if (a.getId() == null) continue;
                actuatorsApi.updateState(a.getId(), state);
                LOG.info("Updated battery actuator {} to state {}", a.getId(), payload.getTargetState());
            }
        } catch (Exception ex) {
            LOG.warn("Failed to push battery decision to IoTM: {}", ex.getMessage());
        }
    }
}
