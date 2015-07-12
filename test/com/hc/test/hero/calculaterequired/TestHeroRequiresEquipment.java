package com.hc.test.hero.calculaterequired;

import static com.hc.hero.GearSetNameConstants.SET_ORANGE;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_1;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_2;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_3;
import static com.hc.hero.GearSetNameConstants.SET_PURPLE_4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hc.HCGearCalculator;
import com.hc.hero.AbstractHero;
import com.hc.test.hero.AbstractHeroTest;

public class TestHeroRequiresEquipment extends AbstractHeroTest {

    /**
     * Tests that Admiral needs to equip Dirks of Cicero at some point
     */
    @Test
    public void testAdmiral_Requires_DirksOfCicero() {
        assertTrue(admiral.requires(dirksOfCicero, false));
    }

    /**
     * Tests that Admiral needs to equip Holy Garment at some point
     */
    @Test
    public void testAdmiral_Requires_HolyGarment() {
        assertTrue(admiral.requires(holyGarment, false));
    }

    /**
     * Tests that Admiral needs to equip All Around Shoes at some point
     */
    @Test
    public void testAdmiral_Requires_AllAroundShoes() {
        assertTrue(admiral.requires(allAroundShoes, false));
    }

    /**
     * Tests that Admiral does not need to equip Bag of Holding at any point
     */
    @Test
    public void testAdmiral_Requires_BagOfHolding() {
        assertFalse(admiral.requires(bagOfHolding));
    }

    /**
     * Tests that Admiral requires (or doesn't require) Dirks of Cicero in
     * specific set intervals
     */
    @Test
    public void testAdmiral_Requires_DirksOfCicero_filterSets() {
        assertTrue(admiral.requires(dirksOfCicero, SET_PURPLE, null,
                false));
        assertTrue(admiral.requires(dirksOfCicero, null, SET_PURPLE_1,
                false));
        assertTrue(admiral.requires(dirksOfCicero, null, SET_PURPLE,
                false));
        assertTrue(admiral.requires(dirksOfCicero, SET_PURPLE,
                SET_PURPLE_1, false));
        assertTrue(admiral.requires(dirksOfCicero, SET_PURPLE_1,
                SET_PURPLE, false));
        assertFalse(admiral.requires(dirksOfCicero, SET_ORANGE,
                SET_ORANGE, false));
        assertFalse(admiral.requires(dirksOfCicero, SET_ORANGE, null,
                false));
    }

    /**
     * Tests that Admiral requires (or doesn't require) All Around Shoes in
     * specific set intervals
     */
    @Test
    public void testAdmiral_Requires_AllAroundShoes_filterSets() {
        assertTrue(admiral.requires(allAroundShoes, SET_ORANGE, null,
                false));
        assertTrue(admiral.requires(allAroundShoes, null, SET_ORANGE,
                false));
        assertFalse(admiral.requires(allAroundShoes, SET_PURPLE_3,
                SET_PURPLE_3, false));
        assertTrue(admiral.requires(allAroundShoes, SET_PURPLE_4,
                SET_PURPLE_4, false));
        assertTrue(admiral.requires(allAroundShoes, SET_ORANGE,
                SET_ORANGE, false));
        assertFalse(admiral.requires(allAroundShoes, null,
                SET_PURPLE_2, false));
        assertFalse(admiral.requires(allAroundShoes, SET_PURPLE_2,
                SET_PURPLE, false));
    }

    /**
     * Tests that Pilot needs to equip Demon Edge at some point
     */
    @Test
    public void testPilot_Requires_DemonEdge() {
        assertTrue(pilot.requires(demonEdge, false));
    }

    /**
     * Tests that Admiral does not need to equip Demon Edge at some point, but
     * needs Demon Edge to craft something else
     */
    @Test
    public void testAdmiral_Requires_DemonEdge() {
        assertFalse(admiral.requires(demonEdge, false));
        assertTrue(admiral.requires(demonEdge, true));
    }

    /**
     * Tests which heroes need to equip Demon Edge at some point
     */
    @Test
    public void testGetHeroesThat_Require_DemonEdge() {

        List<AbstractHero> heroes = HCGearCalculator.getInstance()
                .getHeroesThatRequire(demonEdge, false);

        AbstractHero[] expectedHeroesArray = new AbstractHero[] {
                ancientProtector, arcaneSapper, cleric, cloudWalker,
                depthsVoice, drunkenMaster, emberBlade, ferryman,
                hiddenNeedle, lunarGuardian, mountain, ninjaAssassin,
                pilot, rifleman, savageOne, shadowleaf, sniper,
                tuskedStorm, vanguardWarrior, vengeanceSpirit,
                wanderingSpearman };
        List<AbstractHero> expectedHeroes = Arrays
                .asList(expectedHeroesArray);

        assertTrue(heroes.containsAll(expectedHeroes));
        assertEquals(expectedHeroes.size(), heroes.size());
    }

}
