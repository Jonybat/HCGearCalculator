package com.hc.test.calculateRequired;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.hc.gear.AbstractEquipment;
import com.hc.gear.calculator.IncrementerHashMap;
import com.hc.gear.calculator.RequiredRawMaterialsCalculator;
import com.hc.test.AbstractEquipmentTest;

public class TestCalculateRequiredRawMaterials extends
        AbstractEquipmentTest {

    private RequiredRawMaterialsCalculator getRawMaterialsCalculator() {
        return new RequiredRawMaterialsCalculator();
    }

    /**
     * Wrapper for {@link Assert#assertEquals(long, long) assertEquals(expected,
     * materials.get(equipment)}
     *
     * @param expected
     *            expected value
     * @param equipment
     *            equipment to get from the {@code materials}
     * @param materials
     */
    private void _assertEquals(int expected,
            AbstractEquipment equipment,
            Map<AbstractEquipment, Integer> materials) {

        assertEquals(expected, materials.get(equipment).intValue());
    }

    /**
     * Tests all required materials to craft a Deceiver's Sword having nothing
     * in the inventory
     */
    @Test
    public void testCalculateDeceiversSwordRawMaterials() {

        RequiredRawMaterialsCalculator calculator = getRawMaterialsCalculator();
        Map<AbstractEquipment, Integer> rawMaterials = calculator
                .getMissingRawMaterials(deceiversSword);

        _assertEquals(60, titansAxeFragment, rawMaterials);
        _assertEquals(50, silverLanceFragment, rawMaterials);
        _assertEquals(50, krelnHornReelFragment, rawMaterials);
        _assertEquals(50, roughHewnAxeFragment, rawMaterials);
        _assertEquals(1, tuskSigil, rawMaterials);
        _assertEquals(1, helmofMagnusThorne, rawMaterials);
        assertEquals(6, rawMaterials.entrySet().size());
    }

    /**
     * Tests all required materials to craft a Deceiver's Sword having 3
     * Sanadorian Warden Helm and 15 Kreln Horn Reel Fragment in the inventory
     */
    @Test
    public void testCalculateDeceiversSwordRawMaterials_HavingSanadorianWardenHelm_KrelnHornReelFragment() {

        IncrementerHashMap inventory = new IncrementerHashMap();
        inventory.put(sanadorianWardenHelm, 3);
        inventory.put(krelnHornReelFragment, 15);

        RequiredRawMaterialsCalculator calculator = getRawMaterialsCalculator();
        Map<AbstractEquipment, Integer> rawMaterials = calculator
                .getMissingRawMaterials(deceiversSword, inventory);

        _assertEquals(60, titansAxeFragment, rawMaterials);
        _assertEquals(50, silverLanceFragment, rawMaterials);
        _assertEquals(35, krelnHornReelFragment, rawMaterials);
        _assertEquals(50, roughHewnAxeFragment, rawMaterials);
        assertEquals(4, rawMaterials.entrySet().size());
    }
}
