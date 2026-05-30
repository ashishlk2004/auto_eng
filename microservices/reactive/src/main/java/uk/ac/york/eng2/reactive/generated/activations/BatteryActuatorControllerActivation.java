package uk.ac.york.eng2.reactive.generated.activations;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.york.eng2.reactive.generated.dto.BatteryDecisionRecord;

/**
 * Hand-written activation for the {@code BatteryActuatorController} component.
 *
 * <p>The generated {@code BatteryDecisionConsumer} persists the {@code battery.decision} slot and
 * then calls {@link #onTrigger(BatteryDecisionRecord)}. This is the "manual" half of the generation
 * round-trip and is never overwritten by the generator. The production behaviour for this scenario
 * is implemented in the hand-written
 * {@code uk.ac.york.eng2.reactive.components.BatteryActuatorController}.</p>
 */
@Singleton
public class BatteryActuatorControllerActivation {

    private static final Logger LOG = LoggerFactory.getLogger(BatteryActuatorControllerActivation.class);

    public void onTrigger(BatteryDecisionRecord record) {
        // Manually-completed behaviour: push the decided target state to every battery actuator in
        // IoTM via the generated OpenAPI client.
        LOG.debug("BatteryActuatorController activated with target state {}", record.getTargetState());
    }
}
