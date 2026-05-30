package uk.ac.york.eng2.reactive.generated.dto;

import io.micronaut.serde.annotation.Serdeable;

/**
 * Kafka payload for the {@code battery.decision} topic, referenced by the generated producer and
 * consumer. Slot: {@code targetState} (string, body).
 */
@Serdeable
public class BatteryDecisionRecord {
    private String targetState;

    public BatteryDecisionRecord() {}

    public BatteryDecisionRecord(String targetState) {
        this.targetState = targetState;
    }

    public String getTargetState() {
        return targetState;
    }

    public void setTargetState(String targetState) {
        this.targetState = targetState;
    }
}
