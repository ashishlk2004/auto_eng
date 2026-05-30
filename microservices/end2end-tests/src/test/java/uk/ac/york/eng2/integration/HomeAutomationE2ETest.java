package uk.ac.york.eng2.integration;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.HttpClientConfiguration;
import io.micronaut.http.client.DefaultHttpClientConfiguration;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.integration.dto.Actuator;
import uk.ac.york.eng2.integration.dto.Component;
import uk.ac.york.eng2.integration.dto.Room;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * End-to-end tests that exercise the orchestrated set of containerised microservices.
 *
 * These tests assume {@code docker compose up} has already started the {@code iot},
 * {@code reactive}, {@code rates}, MariaDB and Kafka containers, with IoTM exposed on
 * {@code localhost:8080} and RCM on {@code localhost:8081}.
 */
@MicronautTest
class HomeAutomationE2ETest {

    @Property(name = "iot.base-url")
    String iotBaseUrl;

    @Property(name = "reactive.base-url")
    String reactiveBaseUrl;

    @Inject
    io.micronaut.http.client.HttpClientRegistry<HttpClient> clientRegistry;

    private HttpClient iot;
    private HttpClient reactive;

    @BeforeAll
    static void noop() {
        // no-op (kept to ensure JUnit picks up the class)
    }

    HttpClient clientFor(String baseUrl) {
        try {
            HttpClientConfiguration cfg = new DefaultHttpClientConfiguration();
            cfg.setReadTimeout(Duration.ofSeconds(30));
            return HttpClient.create(new URL(baseUrl), cfg);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    void setUpClients() {
        if (iot == null) iot = clientFor(iotBaseUrl);
        if (reactive == null) reactive = clientFor(reactiveBaseUrl);
    }

    @Test
    void iot_returnsSeedRooms() {
        setUpClients();
        Room[] rooms = iot.toBlocking().retrieve(HttpRequest.GET("/rooms"), Room[].class);
        assertNotNull(rooms);
        assertTrue(Arrays.stream(rooms).anyMatch(r -> "Bedroom".equals(r.getName())));
        assertTrue(Arrays.stream(rooms).anyMatch(r -> "Living Room".equals(r.getName())));
    }

    @Test
    void rcm_registersComponentsOnStartup() {
        setUpClients();
        await().atMost(60, TimeUnit.SECONDS).pollInterval(2, TimeUnit.SECONDS).untilAsserted(() -> {
            Component[] all = reactive.toBlocking().retrieve(HttpRequest.GET("/components"), Component[].class);
            assertTrue(Arrays.stream(all).anyMatch(c -> "AgileRatesFetcher".equals(c.getName())));
            assertTrue(Arrays.stream(all).anyMatch(c -> "CurrentRateExtractor".equals(c.getName())));
            assertTrue(Arrays.stream(all).anyMatch(c -> "BatteryDecisionMaker".equals(c.getName())));
            assertTrue(Arrays.stream(all).anyMatch(c -> "BatteryActuatorController".equals(c.getName())));
        });
    }

    @Test
    void reactiveFlow_updatesBatteryActuator() {
        setUpClients();
        // Wait until the reactive pipeline has had a chance to push a decision through to IoTM
        await().atMost(2, TimeUnit.MINUTES).pollInterval(5, TimeUnit.SECONDS).untilAsserted(() -> {
            Actuator[] batteries = iot.toBlocking().retrieve(
                    HttpRequest.GET("/actuators?type=battery"), Actuator[].class);
            assertTrue(batteries.length >= 1, "Expected at least one battery actuator from seed data");
            String state = batteries[0].getTargetState();
            assertNotNull(state, "Battery state should never be null");
            // The decision maker should have produced one of these three target states by now.
            assertTrue(
                state.startsWith("import_to") || state.startsWith("export_to") || "sell_excess".equals(state),
                "Battery state should be set by the reactive flow, but was: " + state);
        });
    }

    @Test
    void iot_rejectsMalformedRoom() {
        setUpClients();
        Room body = new Room();
        body.setName(""); // blank
        HttpResponse<?> resp;
        try {
            resp = iot.toBlocking().exchange(HttpRequest.POST("/rooms", body));
        } catch (io.micronaut.http.client.exceptions.HttpClientResponseException ex) {
            resp = ex.getResponse();
        }
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatus());
    }
}
