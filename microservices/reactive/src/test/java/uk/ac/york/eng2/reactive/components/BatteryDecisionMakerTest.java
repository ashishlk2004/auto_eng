package uk.ac.york.eng2.reactive.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the pure decision logic of {@link BatteryDecisionMaker}.
 */
class BatteryDecisionMakerTest {

    private BatteryDecisionMaker maker;

    @BeforeEach
    void setUp() throws Exception {
        maker = new BatteryDecisionMaker();
        set(maker, "importCheapThreshold", 12.0);
        set(maker, "exportExpensiveThreshold", 12.0);
        set(maker, "chargeTarget", 80);
        set(maker, "dischargeFloor", 20);
    }

    private static void set(Object target, String name, Object value) throws Exception {
        Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(target, value);
    }

    @Test
    void cheapImport_charges() {
        String decision = maker.decide(8.0, 10.0);
        assertEquals("import_to 80", decision);
    }

    @Test
    void expensiveExport_discharges() {
        String decision = maker.decide(15.0, 18.0);
        assertEquals("export_to 20", decision);
    }

    @Test
    void normalConditions_sellExcess() {
        String decision = maker.decide(14.0, 10.0);
        assertEquals("sell_excess", decision);
    }

    @Test
    void importAtThreshold_stillCharges() {
        String decision = maker.decide(12.0, 5.0);
        assertEquals("import_to 80", decision);
    }
}
