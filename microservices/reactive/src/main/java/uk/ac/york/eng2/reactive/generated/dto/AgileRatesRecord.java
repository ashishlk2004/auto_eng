package uk.ac.york.eng2.reactive.generated.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.time.Instant;

/**
 * Kafka payload for the {@code agile.rates} topic, referenced by the generated producer and
 * consumer. Slots: {@code json} (string, body), {@code validFrom} (timestamp, body),
 * {@code validTo} (timestamp, body).
 */
@Serdeable
public class AgileRatesRecord {
    private String json;
    private Instant validFrom;
    private Instant validTo;

    public AgileRatesRecord() {}

    public AgileRatesRecord(String json, Instant validFrom, Instant validTo) {
        this.json = json;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getValidTo() {
        return validTo;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }
}
