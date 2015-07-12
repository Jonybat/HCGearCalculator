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

    @Test
    public void testAdmiral_Requires_DirksOfCicero() {
        assertTrue(admiral.requires(dirksOfCicero));
    }

    @Test
    public void testAdmiral_Requires_HolyGarment() {
        assertTrue(admiral.requires(holyGarment));
    }

    @Test
    public void testAdmiral_Requires_AllAroundShoes() {
        assertTrue(admiral.requires(allAroundShoes));
    }

    @Test
    public void testAdmiral_Requires_BagOfHolding() {
        assertFalse(admiral.requires(bagOfHolding));
    }

    @Test
    public void testAdmiral_Requires_DirksOfCicero_filterSets() {
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
    public void testAdmiral_Requires_AllAroundShoes_filterSets() {
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

    @Test
    public void testPilot_Requires_DemonEdge() {
        assertTrue(pilot.requires(demonEdge));
    }

    @Test
    public void testAdmiral_Requires_DemonEdge() {
        assertTrue(admiral.requires(demonEdge));
    }

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
