package uk.ac.york.eng2.iot.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.iot.domain.Actuator;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActuatorRepository extends CrudRepository<Actuator, Long> {
    Optional<Actuator> findByName(String name);
    List<Actuator> findByType(String type);
    List<Actuator> findByRoomId(Long roomId);
}
