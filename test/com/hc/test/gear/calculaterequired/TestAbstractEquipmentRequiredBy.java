package com.hc.test.gear.calculaterequired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.hc.gear.AbstractEquipment;
import com.hc.test.gear.AbstractEquipmentTest;

public class TestAbstractEquipmentRequiredBy extends
        AbstractEquipmentTest {

    /**
     * Tests that Demon Edge is required to craft Copper Cudgel, Yew Bolt
     * Thrower, All Around Shoes and nothing else
     */
    @Test
    public void testDemonEdgeRequiredBy() {
        AbstractEquipment[] higherItems = new AbstractEquipment[] {
                copperCudgel, yewBoltThrower, allAroundShoes };
        List<AbstractEquipment> expectedRequiredByDemonEdge = Arrays
                .asList(higherItems);

        Set<AbstractEquipment> requiredByDemonEdge = demonEdge
                .requiredBy();

        assertTrue(requiredByDemonEdge
                .containsAll(expectedRequiredByDemonEdge));
        assertEquals(expectedRequiredByDemonEdge.size(),
                requiredByDemonEdge.size());
    }
}
