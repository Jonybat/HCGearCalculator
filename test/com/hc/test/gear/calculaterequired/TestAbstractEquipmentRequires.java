package com.hc.test.gear.calculaterequired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hc.test.gear.AbstractEquipmentTest;

public class TestAbstractEquipmentRequires extends
        AbstractEquipmentTest {

    /**
     * Tests that Deceiver's Sword requires Sanadorian Warden Helm to be crafted
     */
    @Test
    public void testDeceiversSword_Requires_SanadorianWardenHelm() {
        assertTrue(deceiversSword.requires(sanadorianWardenHelm));
    }

    /**
     * Tests that Dragon's Scale does not require Bag of Holding to be crafted
     */
    @Test
    public void testDragonsScale_Requires_BagOfHolding() {
        assertFalse(dragonsScale.requires(bagOfHolding));
    }

}
