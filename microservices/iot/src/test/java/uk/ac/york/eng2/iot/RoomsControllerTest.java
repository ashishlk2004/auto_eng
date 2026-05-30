package uk.ac.york.eng2.iot;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import uk.ac.york.eng2.iot.domain.Room;
import uk.ac.york.eng2.iot.dto.RoomDTO;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
@Property(name = "kafka.enabled", value = "false")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomsControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @Order(1)
    void list_includesSeedRooms() {
        Room[] rooms = client.toBlocking().retrieve(HttpRequest.GET("/rooms"), Room[].class);
        long matches = Arrays.stream(rooms).filter(r -> "Bedroom".equals(r.getName())).count();
        assertTrue(matches >= 1, "Bedroom should be present from seed data");
    }

    @Test
    @Order(2)
    void create_thenFetch_thenDelete() {
        RoomDTO dto = new RoomDTO();
        dto.setName("Test-Kitchen");
        HttpResponse<Void> created = client.toBlocking().exchange(
                HttpRequest.POST("/rooms", dto), Void.class);
        assertEquals(HttpStatus.CREATED, created.getStatus());
        String location = created.getHeaders().get("Location");
        assertNotNull(location);

        Room fetched = client.toBlocking().retrieve(HttpRequest.GET(location), Room.class);
        assertEquals("Test-Kitchen", fetched.getName());

        HttpResponse<Void> deleted = client.toBlocking().exchange(
                HttpRequest.DELETE(location), Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleted.getStatus());
    }

    @Test
    @Order(3)
    void create_rejectsDuplicateName() {
        RoomDTO dto = new RoomDTO();
        dto.setName("Bedroom"); // already present in seed data
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.POST("/rooms", dto)));
        assertEquals(HttpStatus.CONFLICT, ex.getStatus());
    }

    @Test
    @Order(4)
    void create_rejectsBlankName() {
        RoomDTO dto = new RoomDTO();
        dto.setName(" ");
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.POST("/rooms", dto)));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }

    @Test
    @Order(5)
    void getMissingRoom_returns404() {
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.GET("/rooms/999999"), Room.class));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }
}
