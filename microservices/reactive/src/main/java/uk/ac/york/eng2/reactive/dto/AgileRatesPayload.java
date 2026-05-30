package uk.ac.york.eng2.reactive.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.time.Instant;

@Serdeable
public class AgileRatesPayload {
    private String json;
    private Instant validFrom;
    private Instant validTo;

    public AgileRatesPayload() {}

    public AgileRatesPayload(String json, Instant validFrom, Instant validTo) {
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
