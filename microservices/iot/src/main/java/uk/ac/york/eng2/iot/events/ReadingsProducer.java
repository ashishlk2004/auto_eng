package uk.ac.york.eng2.iot.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.iot.dto.ReadingDTO;

/**
 * Kafka producer used to broadcast every new sensor reading.
 */
@KafkaClient(id = "readings-producer")
public interface ReadingsProducer {

    @Topic("${topic.readings:sensor-readings}")
    void publishReading(@KafkaKey Long sensorId, ReadingDTO reading);
}
