package uk.ac.york.eng2.reactive.generated.dto;

import io.micronaut.serde.annotation.Serdeable;

/**
 * Kafka payload for the {@code current.import.rate} topic, referenced by the generated producer
 * and consumer. Slot: {@code rate} (double, body).
 */
@Serdeable
public class CurrentImportRateRecord {
    private double rate;

    public CurrentImportRateRecord() {}

    public CurrentImportRateRecord(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
