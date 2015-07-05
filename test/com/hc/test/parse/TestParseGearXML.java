package com.hc.test.parse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hc.test.AbstractEquipmentTest;

public class TestParseGearXML extends AbstractEquipmentTest {

    @Test
    public void testParseGlovesOfHaste() {
        String glovesOfHasteString = "Name: Gloves of Haste\tColor:green\nChildren:";
        assertEquals(glovesOfHasteString, glovesOfHaste.toString());
    }

    @Test
    public void testParseDeceiversSword() {
        String deceiversSwordString = "Name: Deceiver's Sword\tColor:orange\nChildren: 1 Kreln Horn 1 Silver Lance 1 Titan's Axe";
        assertEquals(deceiversSwordString, deceiversSword.toString());
    }

}
