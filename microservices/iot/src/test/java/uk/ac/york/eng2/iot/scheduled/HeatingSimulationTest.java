package uk.ac.york.eng2.iot.scheduled;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the pure simulation logic in {@link HeatingSimulation}. These don't require
 * Micronaut to run, so they are fast and deterministic.
 */
class HeatingSimulationTest {

    private HeatingSimulation sim;

    @BeforeEach
    void setUp() {
        sim = new HeatingSimulation();
        sim.setOutsideCelsius(10.0);
        sim.setStepCelsius(0.5);
    }

    @Test
    void heaterOff_dropsTowardsOutside() {
        double next = sim.computeNewTemperature(15.0, false, Optional.empty());
        assertEquals(14.5, next, 1e-9);
    }

    @Test
    void heaterOff_doesNotGoBelowOutside() {
        double next = sim.computeNewTemperature(10.2, false, Optional.empty());
        assertEquals(10.0, next, 1e-9);
    }

    @Test
    void heaterOn_climbsTowardsTarget() {
        double next = sim.computeNewTemperature(18.0, true, Optional.of(21));
        assertEquals(18.5, next, 1e-9);
    }

    @Test
    void heaterOn_doesNotExceedTarget() {
        double next = sim.computeNewTemperature(20.9, true, Optional.of(21));
        assertEquals(21.0, next, 1e-9);
    }

    @Test
    void parseTarget_handlesNumeric() {
        assertEquals(Optional.of(22), sim.parseTarget("22"));
    }

    @Test
    void parseTarget_handlesNonNumeric() {
        assertEquals(Optional.empty(), sim.parseTarget("off"));
    }
}
