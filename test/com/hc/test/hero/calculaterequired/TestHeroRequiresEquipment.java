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
    public void testAdmiral_Equips_DirksOfCicero() {
        assertTrue(admiral.equips(dirksOfCicero));
    }

    /**
     * Tests that Admiral needs to equip Holy Garment at some point
     */
    @Test
    public void testAdmiral_Equips_HolyGarment() {
        assertTrue(admiral.equips(holyGarment));
    }

    /**
     * Tests that Admiral needs to equip All Around Shoes at some point
     */
    @Test
    public void testAdmiral_Equips_AllAroundShoes() {
        assertTrue(admiral.equips(allAroundShoes));
    }

    /**
     * Tests that Admiral does not require Bag of Holding at any point
     */
    @Test
    public void testAdmiral_Requires_BagOfHolding() {
        assertFalse(admiral.requires(bagOfHolding));
    }

    /**
     * Tests that Admiral equips Dirks of Cicero in specific set intervals
     */
    @Test
    public void testAdmiral_Equips_DirksOfCicero_filterSets_several() {
        assertTrue(admiral.equips(dirksOfCicero, SET_PURPLE, null));
        assertTrue(admiral.equips(dirksOfCicero, null, SET_PURPLE_1));
        assertTrue(admiral.equips(dirksOfCicero, null, SET_PURPLE));
        assertTrue(admiral.equips(dirksOfCicero, SET_PURPLE,
                SET_PURPLE_1));
        assertTrue(admiral.equips(dirksOfCicero, SET_PURPLE_1,
                SET_PURPLE));
        assertFalse(admiral.equips(dirksOfCicero, SET_ORANGE,
                SET_ORANGE));
        assertFalse(admiral.equips(dirksOfCicero, SET_ORANGE, null));
    }

    /**
     * Tests that Admiral equips All Around Shoes in specific set intervals
     */
    @Test
    public void testAdmiral_Equips_AllAroundShoes_filterSets_several() {
        assertTrue(admiral.equips(allAroundShoes, SET_ORANGE, null));
        assertTrue(admiral.equips(allAroundShoes, null, SET_ORANGE));
        assertFalse(admiral.equips(allAroundShoes, SET_PURPLE_3,
                SET_PURPLE_3));
        assertTrue(admiral.equips(allAroundShoes, SET_PURPLE_4,
                SET_PURPLE_4));
        assertTrue(admiral.equips(allAroundShoes, SET_ORANGE,
                SET_ORANGE));
        assertFalse(admiral.equips(allAroundShoes, null, SET_PURPLE_2));
        assertFalse(admiral.equips(allAroundShoes, SET_PURPLE_2,
                SET_PURPLE));
    }

    /**
     * Tests that Pilot needs to equip Demon Edge at some point
     */
    @Test
    public void testPilot_Equips_DemonEdge() {
        assertTrue(pilot.equips(demonEdge));
    }

    /**
     * Tests that Admiral does equip Demon Edge at any point, but requires it to
     * craft something else
     */
    @Test
    public void testAdmiral_Requires_DemonEdge() {
        assertFalse(admiral.equips(demonEdge));
        assertTrue(admiral.requires(demonEdge));
    }

    /**
     * Tests which heroes need to equip Demon Edge at some point
     */
    @Test
    public void testGetHeroesThat_Equip_DemonEdge() {

        List<AbstractHero> heroes = HCGearCalculator.getInstance()
                .getHeroesThatEquip(demonEdge);

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

    /**
     * Tests which heroes require Yew Bolt Thrower between sets Purple+2 and
     * Orange
     */
    @Test
    public void testGetHeroesThat_Require_YewBoltThrower_FilterSets_P2_Orange() {
        List<AbstractHero> heroes = HCGearCalculator.getInstance()
                .getHeroesThatRequire(yewBoltThrower, SET_PURPLE,
                        SET_ORANGE);

        AbstractHero[] expectedHeroesArray = new AbstractHero[] {
                admiral, ancientProtector, arcaneSapper, commando,
                deathgore, depthsVoice, drunkenMaster, emberBlade,
                ferryman, hiddenNeedle, ironHoof, lightningElemental,
                lunarGuardian, mountain, ninjaAssassin, pilot,
                poisonedOne, professionalKiller, psychicSword,
                rifleman, savageOne, shadowleaf, sniper, tuskedStorm,
                vanguardWarrior, vengeanceSpirit, wanderingSpearman,
                warriorMonk, windMaster };
        List<AbstractHero> expectedHeroes = Arrays
                .asList(expectedHeroesArray);

        assertTrue(heroes.containsAll(expectedHeroes));
        assertEquals(expectedHeroes.size(), heroes.size());
    }

}
