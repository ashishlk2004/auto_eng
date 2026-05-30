package uk.ac.york.eng2.reactive.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class RatePayload {
    private double rate;

    public RatePayload() {}

    public RatePayload(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
