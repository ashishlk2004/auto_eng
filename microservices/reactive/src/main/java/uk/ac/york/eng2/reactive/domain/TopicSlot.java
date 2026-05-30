package uk.ac.york.eng2.reactive.domain;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.Instant;

@Serdeable
@Entity
@Table(name = "topic_slot", uniqueConstraints = @UniqueConstraint(name = "uk_topic_slot", columnNames = {"topic_name", "slot_name"}))
public class TopicSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "topic_name", nullable = false)
    private String topicName;

    @Column(name = "slot_name", nullable = false)
    private String slotName;

    @Column(name = "slot_type", nullable = false)
    private String slotType;

    @Column(name = "timestamp_value")
    private Instant timestampValue;

    @Column(name = "text_value", columnDefinition = "longtext")
    private String textValue;

    @Column(name = "double_value")
    private Double doubleValue;

    @Column(name = "long_value")
    private Long longValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public Instant getTimestampValue() {
        return timestampValue;
    }

    public void setTimestampValue(Instant timestampValue) {
        this.timestampValue = timestampValue;
        this.slotType = SlotType.TIMESTAMP.getDbValue();
        this.textValue = null;
        this.doubleValue = null;
        this.longValue = null;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
        this.slotType = SlotType.TEXT.getDbValue();
        this.timestampValue = null;
        this.doubleValue = null;
        this.longValue = null;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
        this.slotType = SlotType.DOUBLE.getDbValue();
        this.timestampValue = null;
        this.textValue = null;
        this.longValue = null;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
        this.slotType = SlotType.LONG.getDbValue();
        this.timestampValue = null;
        this.textValue = null;
        this.doubleValue = null;
    }
}
