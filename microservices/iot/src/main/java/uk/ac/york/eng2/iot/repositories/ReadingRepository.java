package uk.ac.york.eng2.iot.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.iot.domain.Reading;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long> {
    List<Reading> findBySensorIdOrderByTakenAtDesc(Long sensorId);
    Optional<Reading> findFirstBySensorIdOrderByTakenAtDesc(Long sensorId);
}
