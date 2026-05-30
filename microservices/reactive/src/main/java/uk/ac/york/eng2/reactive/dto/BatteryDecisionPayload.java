package uk.ac.york.eng2.reactive.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class BatteryDecisionPayload {
    private String targetState;

    public BatteryDecisionPayload() {}

    public BatteryDecisionPayload(String targetState) {
        this.targetState = targetState;
    }

    public String getTargetState() {
        return targetState;
    }

    public void setTargetState(String targetState) {
        this.targetState = targetState;
    }
}
