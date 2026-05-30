package uk.ac.york.eng2.reactive.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.reactive.dto.BatteryDecisionPayload;

@KafkaClient(id = "battery-decision-producer")
public interface BatteryDecisionProducer {

    @Topic("${topic.battery-decision:battery-decision}")
    void publish(@KafkaKey String key, BatteryDecisionPayload payload);
}
