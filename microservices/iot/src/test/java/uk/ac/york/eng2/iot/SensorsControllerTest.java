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
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.iot.domain.Room;
import uk.ac.york.eng2.iot.domain.Sensor;
import uk.ac.york.eng2.iot.dto.ReadingDTO;
import uk.ac.york.eng2.iot.dto.SensorDTO;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
@Property(name = "kafka.enabled", value = "false")
class SensorsControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void list_returnsSeedSensors() {
        Sensor[] sensors = client.toBlocking().retrieve(HttpRequest.GET("/sensors"), Sensor[].class);
        assertTrue(sensors.length >= 1);
    }

    @Test
    void create_andDelete_succeeds() {
        Room[] rooms = client.toBlocking().retrieve(HttpRequest.GET("/rooms"), Room[].class);
        Long roomId = rooms[0].getId();

        SensorDTO dto = new SensorDTO();
        dto.setName("Test-Humidity");
        dto.setType("humidity");
        dto.setRoomId(roomId);
        HttpResponse<Void> created = client.toBlocking().exchange(
                HttpRequest.POST("/sensors", dto), Void.class);
        assertEquals(HttpStatus.CREATED, created.getStatus());

        String location = created.getHeaders().get("Location");
        HttpResponse<Void> deleted = client.toBlocking().exchange(HttpRequest.DELETE(location), Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleted.getStatus());
    }

    @Test
    void create_failsWithUnknownRoom() {
        SensorDTO dto = new SensorDTO();
        dto.setName("Bad-Sensor");
        dto.setType("any");
        dto.setRoomId(987654L);
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.POST("/sensors", dto)));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }

    @Test
    void readings_returnsEmptyForUnknownSensor() {
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.GET("/sensors/9999999/readings"), Object.class));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }

    @Test
    void readings_returnsListForExistingSensor() {
        Sensor[] sensors = client.toBlocking().retrieve(HttpRequest.GET("/sensors"), Sensor[].class);
        Long id = sensors[0].getId();
        ReadingDTO[] readings = client.toBlocking().retrieve(
                HttpRequest.GET("/sensors/" + id + "/readings"), ReadingDTO[].class);
        assertNotNull(readings);
        // Could be empty since simulation is disabled in tests
        Arrays.stream(readings).forEach(r -> assertNotNull(r.getTakenAt()));
    }
}
