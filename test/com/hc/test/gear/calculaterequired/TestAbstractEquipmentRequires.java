package com.hc.test.gear.calculaterequired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hc.test.gear.AbstractEquipmentTest;

public class TestAbstractEquipmentRequires extends
        AbstractEquipmentTest {

    @Test
    public void testDeceiversSword_Requires_SanadorianWardenHelm() {
        assertTrue(deceiversSword.requires(sanadorianWardenHelm));
    }

    @Test
    public void testDragonsScale_Requires_BagOfHolding() {
        assertFalse(dragonsScale.requires(bagOfHolding));
    }

}
