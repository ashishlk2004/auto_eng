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
import uk.ac.york.eng2.iot.domain.Actuator;
import uk.ac.york.eng2.iot.domain.Room;
import uk.ac.york.eng2.iot.dto.ActuatorDTO;
import uk.ac.york.eng2.iot.dto.ActuatorStateDTO;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
@Property(name = "kafka.enabled", value = "false")
class ActuatorsControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void list_returnsSeedActuators() {
        Actuator[] actuators = client.toBlocking().retrieve(HttpRequest.GET("/actuators"), Actuator[].class);
        assertTrue(Arrays.stream(actuators).anyMatch(a -> "battery".equals(a.getType())));
    }

    @Test
    void list_canFilterByType() {
        Actuator[] heaters = client.toBlocking().retrieve(
                HttpRequest.GET("/actuators?type=heater"), Actuator[].class);
        assertTrue(heaters.length >= 1);
        Arrays.stream(heaters).forEach(a -> assertEquals("heater", a.getType()));
    }

    @Test
    void updateState_changesTargetState() {
        Actuator[] all = client.toBlocking().retrieve(HttpRequest.GET("/actuators?type=battery"), Actuator[].class);
        Long batteryId = all[0].getId();

        ActuatorStateDTO state = new ActuatorStateDTO();
        state.setTargetState("import_to 90");
        HttpResponse<Actuator> resp = client.toBlocking().exchange(
                HttpRequest.PUT("/actuators/" + batteryId + "/state", state), Actuator.class);
        assertEquals(HttpStatus.OK, resp.getStatus());
        Optional<Actuator> body = resp.getBody();
        assertTrue(body.isPresent());
        assertEquals("import_to 90", body.get().getTargetState());
    }

    @Test
    void updateState_emptyBodyRejected() {
        ActuatorStateDTO state = new ActuatorStateDTO();
        state.setTargetState(" ");
        Actuator[] all = client.toBlocking().retrieve(HttpRequest.GET("/actuators?type=battery"), Actuator[].class);
        Long batteryId = all[0].getId();
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.PUT("/actuators/" + batteryId + "/state", state)));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }

    @Test
    void updateState_unknownActuatorReturns404() {
        ActuatorStateDTO state = new ActuatorStateDTO();
        state.setTargetState("off");
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.PUT("/actuators/9999999/state", state)));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }

    @Test
    void create_succeedsAndConflictsOnDuplicate() {
        Room[] rooms = client.toBlocking().retrieve(HttpRequest.GET("/rooms"), Room[].class);

        ActuatorDTO dto = new ActuatorDTO();
        dto.setName("Test-Actuator");
        dto.setType("heater");
        dto.setRoomId(rooms[0].getId());
        dto.setTargetState("off");
        HttpResponse<Void> created = client.toBlocking().exchange(HttpRequest.POST("/actuators", dto), Void.class);
        assertEquals(HttpStatus.CREATED, created.getStatus());

        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.POST("/actuators", dto)));
        assertEquals(HttpStatus.CONFLICT, ex.getStatus());
    }
}
