package uk.ac.york.eng2.iot.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.iot.domain.Room;
import uk.ac.york.eng2.iot.dto.RoomDTO;
import uk.ac.york.eng2.iot.repositories.RoomRepository;

import java.net.URI;
import java.util.Optional;

@Tag(name = "rooms")
@Controller("/rooms")
public class RoomsController {

    @Inject
    private RoomRepository roomRepository;

    @Get
    public Iterable<Room> list() {
        return roomRepository.findAll();
    }

    @Get("/{id}")
    public HttpResponse<Room> getRoom(@PathVariable Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.map(HttpResponse::ok).orElseGet(HttpResponse::notFound);
    }

    @Post
    @Transactional
    public HttpResponse<Void> createRoom(@Body RoomDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            return HttpResponse.badRequest();
        }
        if (roomRepository.findByName(dto.getName()).isPresent()) {
            return HttpResponse.status(io.micronaut.http.HttpStatus.CONFLICT);
        }
        Room room = new Room();
        room.setName(dto.getName());
        room = roomRepository.save(room);
        return HttpResponse.created(URI.create("/rooms/" + room.getId()));
    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Room> updateRoom(@PathVariable Long id, @Body RoomDTO dto) {
        Optional<Room> existing = roomRepository.findById(id);
        if (existing.isEmpty()) {
            return HttpResponse.notFound();
        }
        Room room = existing.get();
        if (dto.getName() != null && !dto.getName().isBlank()) {
            room.setName(dto.getName());
        }
        room = roomRepository.update(room);
        return HttpResponse.ok(room);
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deleteRoom(@PathVariable Long id) {
        if (!roomRepository.existsById(id)) {
            return HttpResponse.notFound();
        }
        roomRepository.deleteById(id);
        return HttpResponse.noContent();
    }
}
