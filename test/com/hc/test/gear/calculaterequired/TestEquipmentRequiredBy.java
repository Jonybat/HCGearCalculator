package com.hc.test.gear.calculaterequired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.hc.gear.AbstractEquipment;
import com.hc.test.gear.AbstractEquipmentTest;

public class TestEquipmentRequiredBy extends AbstractEquipmentTest {

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

    /**
     * Tests that nothing requires Yew Bolt Thrower to be crafted
     */
    @Test
    public void testYewBoldThrowerRequiredBy() {
        Set<AbstractEquipment> requiredByYewBoltThrower = yewBoltThrower
                .requiredBy();

        assertEquals(0, requiredByYewBoltThrower.size());
    }

    /**
     * Tests that Yew Bolt Thrower is required to craft Thunder Sword and
     * Emerald Ring
     */
    @Test
    public void testEulsScepterOfDivinityRequiredBy() {
        Set<AbstractEquipment> requiredByEulsScepterOfDivinity = eulsScepterOfDivinity
                .requiredBy();

        AbstractEquipment[] higherItems = new AbstractEquipment[] {
                thunderSword, emeraldRing };
        List<AbstractEquipment> expectedRequiredByEulsScepterOfDivinity = Arrays
                .asList(higherItems);

        assertTrue(requiredByEulsScepterOfDivinity
                .containsAll(expectedRequiredByEulsScepterOfDivinity));
        assertEquals(expectedRequiredByEulsScepterOfDivinity.size(),
                requiredByEulsScepterOfDivinity.size());

    }
}
