package com.hc.test.hero.calculaterequired;

import static com.hc.hero.GearSetNameConstants.SET_ORANGE;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_1;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_2;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_3;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_4;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hc.test.hero.AbstractHeroTest;

public class TestHeroRequiresEquipment extends AbstractHeroTest {

    @Test
    public void testAdmiralRequiresDirksOfCicero() {
        assertTrue(admiral.requires(dirksOfCicero));
    }

    @Test
    public void testAdmiralRequiresHolyGarment() {
        assertTrue(admiral.requires(holyGarment));
    }

    @Test
    public void testAdmiralRequiresAllAroundShoes() {
        assertTrue(admiral.requires(allAroundShoes));
    }

    @Test
    public void testAdmiralRequiresBagOfHolding() {
        assertFalse(admiral.requires(bagOfHolding));
    }

    @Test
    public void testAdmiralRequiresDirksOfCicero_filterSets() {
        assertTrue(admiral.requires(dirksOfCicero, SET_PURPLE, null));
        assertTrue(admiral.requires(dirksOfCicero, null, SET_PURPLE_1));
        assertTrue(admiral.requires(dirksOfCicero, null, SET_PURPLE));
        assertTrue(admiral.requires(dirksOfCicero, SET_PURPLE,
                SET_PURPLE_1));
        assertTrue(admiral.requires(dirksOfCicero, SET_PURPLE_1,
                SET_PURPLE));
        assertFalse(admiral.requires(dirksOfCicero, SET_ORANGE,
                SET_ORANGE));
        assertFalse(admiral.requires(dirksOfCicero, SET_ORANGE, null));
    }

    @Test
    public void testAdmiralRequiresAllAroundShoes_filterSets() {
        assertTrue(admiral.requires(allAroundShoes, SET_ORANGE, null));
        assertTrue(admiral.requires(allAroundShoes, null, SET_ORANGE));
        assertFalse(admiral.requires(allAroundShoes, SET_PURPLE_3,
                SET_PURPLE_3));
        assertTrue(admiral.requires(allAroundShoes, SET_PURPLE_4,
                SET_PURPLE_4));
        assertTrue(admiral.requires(allAroundShoes, SET_ORANGE,
                SET_ORANGE));
        assertFalse(admiral
                .requires(allAroundShoes, null, SET_PURPLE_2));
        assertFalse(admiral.requires(allAroundShoes, SET_PURPLE_2,
                SET_PURPLE));
    }

}
