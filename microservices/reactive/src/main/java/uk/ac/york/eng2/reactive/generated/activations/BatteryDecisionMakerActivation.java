package uk.ac.york.eng2.reactive.generated.activations;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.york.eng2.reactive.generated.dto.CurrentExportRateRecord;
import uk.ac.york.eng2.reactive.generated.dto.CurrentImportRateRecord;

/**
 * Hand-written activation for the {@code BatteryDecisionMaker} component.
 *
 * <p>This component is triggered by two topics ({@code current.import.rate} and
 * {@code current.export.rate}), so the generated consumers call one {@code onTrigger} overload
 * each. This is the "manual" half of the generation round-trip and is never overwritten by the
 * generator. The production behaviour for this scenario is implemented in the hand-written
 * {@code uk.ac.york.eng2.reactive.components.BatteryDecisionMaker}.</p>
 */
@Singleton
public class BatteryDecisionMakerActivation {

    private static final Logger LOG = LoggerFactory.getLogger(BatteryDecisionMakerActivation.class);

    public void onTrigger(CurrentImportRateRecord record) {
        // Manually-completed behaviour: record the latest import rate and, once both rates are
        // available, decide the battery target state and publish it onto battery.decision.
        LOG.debug("BatteryDecisionMaker activated with import rate {}", record.getRate());
    }

    public void onTrigger(CurrentExportRateRecord record) {
        // Manually-completed behaviour: record the latest export rate and, once both rates are
        // available, decide the battery target state and publish it onto battery.decision.
        LOG.debug("BatteryDecisionMaker activated with export rate {}", record.getRate());
    }
}
