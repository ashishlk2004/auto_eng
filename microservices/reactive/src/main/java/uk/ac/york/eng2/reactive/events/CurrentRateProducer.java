package uk.ac.york.eng2.reactive.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.reactive.dto.RatePayload;

@KafkaClient(id = "current-rate-producer")
public interface CurrentRateProducer {

    @Topic("${topic.current-import-rate:current-import-rate}")
    void publishImport(@KafkaKey String key, RatePayload payload);

    @Topic("${topic.current-export-rate:current-export-rate}")
    void publishExport(@KafkaKey String key, RatePayload payload);
}
