package uk.ac.york.eng2.reactive.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.reactive.dto.AgileRatesPayload;

@KafkaClient(id = "agile-rates-producer")
public interface AgileRatesProducer {

    @Topic("${topic.agile-rates:agile-rates}")
    void publish(@KafkaKey String key, AgileRatesPayload payload);
}
