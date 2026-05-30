package uk.ac.york.eng2.iot.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.iot.domain.Reading;
import uk.ac.york.eng2.iot.domain.Room;
import uk.ac.york.eng2.iot.domain.Sensor;
import uk.ac.york.eng2.iot.dto.ReadingDTO;
import uk.ac.york.eng2.iot.dto.SensorDTO;
import uk.ac.york.eng2.iot.repositories.ReadingRepository;
import uk.ac.york.eng2.iot.repositories.RoomRepository;
import uk.ac.york.eng2.iot.repositories.SensorRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "sensors")
@Controller("/sensors")
public class SensorsController {

    @Inject
    private SensorRepository sensorRepository;

    @Inject
    private RoomRepository roomRepository;

    @Inject
    private ReadingRepository readingRepository;

    @Get
    public Iterable<Sensor> list() {
        return sensorRepository.findAll();
    }

    @Get("/{id}")
    public HttpResponse<Sensor> getSensor(@PathVariable Long id) {
        return sensorRepository.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Post
    @Transactional
    public HttpResponse<Void> createSensor(@Body SensorDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()
                || dto.getType() == null || dto.getType().isBlank()
                || dto.getRoomId() == null) {
            return HttpResponse.badRequest();
        }
        if (sensorRepository.findByName(dto.getName()).isPresent()) {
            return HttpResponse.status(io.micronaut.http.HttpStatus.CONFLICT);
        }
        Optional<Room> room = roomRepository.findById(dto.getRoomId());
        if (room.isEmpty()) {
            return HttpResponse.badRequest();
        }
        Sensor sensor = new Sensor();
        sensor.setName(dto.getName());
        sensor.setType(dto.getType());
        sensor.setRoom(room.get());
        sensor = sensorRepository.save(sensor);
        return HttpResponse.created(URI.create("/sensors/" + sensor.getId()));
    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Sensor> updateSensor(@PathVariable Long id, @Body SensorDTO dto) {
        Optional<Sensor> existing = sensorRepository.findById(id);
        if (existing.isEmpty()) {
            return HttpResponse.notFound();
        }
        Sensor sensor = existing.get();
        if (dto.getName() != null && !dto.getName().isBlank()) {
            sensor.setName(dto.getName());
        }
        if (dto.getType() != null && !dto.getType().isBlank()) {
            sensor.setType(dto.getType());
        }
        if (dto.getRoomId() != null) {
            Optional<Room> room = roomRepository.findById(dto.getRoomId());
            if (room.isEmpty()) {
                return HttpResponse.badRequest();
            }
            sensor.setRoom(room.get());
        }
        sensor = sensorRepository.update(sensor);
        return HttpResponse.ok(sensor);
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteSensor(@PathVariable Long id) {
        if (!sensorRepository.existsById(id)) {
            return HttpResponse.notFound();
        }
        sensorRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    @Get("/{id}/readings")
    public HttpResponse<List<ReadingDTO>> getReadings(@PathVariable Long id) {
        if (!sensorRepository.existsById(id)) {
            return HttpResponse.notFound();
        }
        List<Reading> readings = readingRepository.findBySensorIdOrderByTakenAtDesc(id);
        List<ReadingDTO> dtos = readings.stream().map(r -> {
            ReadingDTO d = new ReadingDTO();
            d.setId(r.getId());
            d.setSensorId(r.getSensor().getId());
            d.setSensorName(r.getSensor().getName());
            d.setTakenAt(r.getTakenAt());
            d.setValue(r.getValue());
            return d;
        }).collect(Collectors.toList());
        return HttpResponse.ok(dtos);
    }
}
