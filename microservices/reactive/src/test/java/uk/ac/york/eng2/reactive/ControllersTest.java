package uk.ac.york.eng2.reactive;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Disabled;
import uk.ac.york.eng2.reactive.domain.Component;
import uk.ac.york.eng2.reactive.domain.TopicSlot;

import java.util.Arrays;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

/**
 * Integration tests for the RCM HTTP resources. These require Testcontainers (Kafka + MariaDB)
 * to be available; if Docker is not available these tests will be skipped automatically.
 */
@MicronautTest(transactional = false)
@Property(name = "reactive.agile-fetch.fixed-delay", value = "365d")
@Property(name = "reactive.rate-extract.fixed-delay", value = "365d")
class ControllersTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void components_areRegisteredOnStartup() {
        // All four reactive components register themselves via @PostConstruct
        await().atMost(15, TimeUnit.SECONDS).untilAsserted(() -> {
            Component[] all = client.toBlocking().retrieve(HttpRequest.GET("/components"), Component[].class);
            assertTrue(Arrays.stream(all).anyMatch(c -> "AgileRatesFetcher".equals(c.getName())));
            assertTrue(Arrays.stream(all).anyMatch(c -> "CurrentRateExtractor".equals(c.getName())));
            assertTrue(Arrays.stream(all).anyMatch(c -> "BatteryDecisionMaker".equals(c.getName())));
            assertTrue(Arrays.stream(all).anyMatch(c -> "BatteryActuatorController".equals(c.getName())));
        });
    }

    @Test
    void componentsById_unknownReturns404() {
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.GET("/components/9999999"), Component.class));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }

    @Test
    void slots_listEmptyOrPopulated() {
        TopicSlot[] slots = client.toBlocking().retrieve(HttpRequest.GET("/topic-slots"), TopicSlot[].class);
        assertNotNull(slots);
    }

    @Test
    void slot_unknownReturns404() {
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class, () ->
                client.toBlocking().exchange(HttpRequest.GET("/topic-slots/nope/none"), TopicSlot.class));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }
}
