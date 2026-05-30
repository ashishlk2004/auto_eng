package uk.ac.york.eng2.reactive.components;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pure-logic tests for {@link CurrentRateExtractor#findRatesForInstant(String, Instant)}.
 */
class CurrentRateExtractorTest {

    private static final String SAMPLE_JSON = "{\n" +
            "  \"rates\":[\n" +
            "    {\n" +
            "      \"deliveryStart\":\"2026-05-20T00:00:00Z\",\n" +
            "      \"deliveryEnd\":\"2026-05-20T00:30:00Z\",\n" +
            "      \"agileRate\":{\"result\":{\"rate\":8.5}},\n" +
            "      \"agileOutgoingRate\":{\"result\":{\"rate\":4.0}}\n" +
            "    },\n" +
            "    {\n" +
            "      \"deliveryStart\":\"2026-05-20T00:30:00Z\",\n" +
            "      \"deliveryEnd\":\"2026-05-20T01:00:00Z\",\n" +
            "      \"agileRate\":{\"result\":{\"rate\":20.0}},\n" +
            "      \"agileOutgoingRate\":{\"result\":{\"rate\":15.5}}\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    void findRates_returnsFirstWindow() {
        CurrentRateExtractor x = new CurrentRateExtractor();
        Optional<double[]> result = x.findRatesForInstant(SAMPLE_JSON, Instant.parse("2026-05-20T00:15:00Z"));
        assertTrue(result.isPresent());
        assertEquals(8.5, result.get()[0], 1e-9);
        assertEquals(4.0, result.get()[1], 1e-9);
    }

    @Test
    void findRates_returnsSecondWindow() {
        CurrentRateExtractor x = new CurrentRateExtractor();
        Optional<double[]> result = x.findRatesForInstant(SAMPLE_JSON, Instant.parse("2026-05-20T00:45:00Z"));
        assertTrue(result.isPresent());
        assertEquals(20.0, result.get()[0], 1e-9);
        assertEquals(15.5, result.get()[1], 1e-9);
    }

    @Test
    void findRates_outsideRange_isEmpty() {
        CurrentRateExtractor x = new CurrentRateExtractor();
        Optional<double[]> result = x.findRatesForInstant(SAMPLE_JSON, Instant.parse("2026-05-20T05:00:00Z"));
        assertTrue(result.isEmpty());
    }

    @Test
    void findRates_invalidJson_returnsEmpty() {
        CurrentRateExtractor x = new CurrentRateExtractor();
        Optional<double[]> result = x.findRatesForInstant("not json", Instant.now());
        assertTrue(result.isEmpty());
    }
}
