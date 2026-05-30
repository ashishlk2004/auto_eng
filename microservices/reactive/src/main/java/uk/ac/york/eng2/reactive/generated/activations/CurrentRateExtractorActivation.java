package uk.ac.york.eng2.reactive.generated.activations;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.york.eng2.reactive.generated.dto.AgileRatesRecord;

/**
 * Hand-written activation for the {@code CurrentRateExtractor} component.
 *
 * <p>The generated {@code AgileRatesConsumer} persists the {@code agile.rates} slots and then calls
 * {@link #onTrigger(AgileRatesRecord)}. This is the "manual" half of the generation round-trip and
 * is never overwritten by the generator. The production behaviour for this scenario is implemented
 * in the hand-written {@code uk.ac.york.eng2.reactive.components.CurrentRateExtractor}; this
 * activation documents the shape a fully generator-driven implementation would take.</p>
 */
@Singleton
public class CurrentRateExtractorActivation {

    private static final Logger LOG = LoggerFactory.getLogger(CurrentRateExtractorActivation.class);

    public void onTrigger(AgileRatesRecord record) {
        // Manually-completed behaviour: extract the import/export rate for the current half-hour
        // window from the agile-rates JSON and publish them onto current.import.rate and
        // current.export.rate.
        LOG.debug("CurrentRateExtractor activated for agile rates valid {} - {}",
                record.getValidFrom(), record.getValidTo());
    }
}
