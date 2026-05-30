package uk.ac.york.eng2.iot.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.iot.domain.Actuator;
import uk.ac.york.eng2.iot.domain.Room;
import uk.ac.york.eng2.iot.dto.ActuatorDTO;
import uk.ac.york.eng2.iot.dto.ActuatorStateDTO;
import uk.ac.york.eng2.iot.repositories.ActuatorRepository;
import uk.ac.york.eng2.iot.repositories.RoomRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "actuators")
@Controller("/actuators")
public class ActuatorsController {

    @Inject
    private ActuatorRepository actuatorRepository;

    @Inject
    private RoomRepository roomRepository;

    @Get
    public Iterable<Actuator> list(@QueryValue Optional<String> type) {
        if (type.isPresent()) {
            return actuatorRepository.findByType(type.get());
        }
        return actuatorRepository.findAll();
    }

    @Get("/{id}")
    public HttpResponse<Actuator> getActuator(@PathVariable Long id) {
        return actuatorRepository.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Post
    @Transactional
    public HttpResponse<Void> createActuator(@Body ActuatorDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()
                || dto.getType() == null || dto.getType().isBlank()
                || dto.getRoomId() == null
                || dto.getTargetState() == null || dto.getTargetState().isBlank()) {
            return HttpResponse.badRequest();
        }
        if (actuatorRepository.findByName(dto.getName()).isPresent()) {
            return HttpResponse.status(io.micronaut.http.HttpStatus.CONFLICT);
        }
        Optional<Room> room = roomRepository.findById(dto.getRoomId());
        if (room.isEmpty()) {
            return HttpResponse.badRequest();
        }
        Actuator actuator = new Actuator();
        actuator.setName(dto.getName());
        actuator.setType(dto.getType());
        actuator.setRoom(room.get());
        actuator.setTargetState(dto.getTargetState());
        actuator = actuatorRepository.save(actuator);
        return HttpResponse.created(URI.create("/actuators/" + actuator.getId()));
    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Actuator> updateActuator(@PathVariable Long id, @Body ActuatorDTO dto) {
        Optional<Actuator> existing = actuatorRepository.findById(id);
        if (existing.isEmpty()) {
            return HttpResponse.notFound();
        }
        Actuator actuator = existing.get();
        if (dto.getName() != null && !dto.getName().isBlank()) {
            actuator.setName(dto.getName());
        }
        if (dto.getType() != null && !dto.getType().isBlank()) {
            actuator.setType(dto.getType());
        }
        if (dto.getTargetState() != null && !dto.getTargetState().isBlank()) {
            actuator.setTargetState(dto.getTargetState());
        }
        if (dto.getRoomId() != null) {
            Optional<Room> room = roomRepository.findById(dto.getRoomId());
            if (room.isEmpty()) {
                return HttpResponse.badRequest();
            }
            actuator.setRoom(room.get());
        }
        actuator = actuatorRepository.update(actuator);
        return HttpResponse.ok(actuator);
    }

    @Put("/{id}/state")
    @Transactional
    public HttpResponse<Actuator> updateState(@PathVariable Long id, @Body ActuatorStateDTO dto) {
        if (dto.getTargetState() == null || dto.getTargetState().isBlank()) {
            return HttpResponse.badRequest();
        }
        Optional<Actuator> existing = actuatorRepository.findById(id);
        if (existing.isEmpty()) {
            return HttpResponse.notFound();
        }
        Actuator actuator = existing.get();
        actuator.setTargetState(dto.getTargetState());
        actuator = actuatorRepository.update(actuator);
        return HttpResponse.ok(actuator);
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteActuator(@PathVariable Long id) {
        if (!actuatorRepository.existsById(id)) {
            return HttpResponse.notFound();
        }
        actuatorRepository.deleteById(id);
        return HttpResponse.noContent();
    }
}
