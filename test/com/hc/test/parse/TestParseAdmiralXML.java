package com.hc.test.parse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.hc.hero.GearSet;
import com.hc.hero.GearSetNameConstants;
import com.hc.test.hero.AbstractHeroTest;

public class TestParseAdmiralXML extends AbstractHeroTest {

    /**
     * Tests Admiral was correctly parsed from the xml
     */
    @Test
    public void testParseAdmiral() {
        assertEquals(1, admiral.stars());
        testParseAdmiralPurpleSet();
        testParseAdmiralPurple1Set();
        testParseAdmiralPurple2Set();
        testParseAdmiralPurple3Set();
        testParseAdmiralPurple4Set();
        testParseAdmiralOrangeSet();
    }

    private void testParseAdmiralPurpleSet() {

        Optional<GearSet> oSet = admiral
                .getSet(GearSetNameConstants.SET_PURPLE);

        assertTrue(oSet.isPresent());
        GearSet set = oSet.get();

        assertTrue(set.contains(ballLightning));
        assertTrue(set.contains(dirksOfCicero));
        assertTrue(set.contains(vastroTooth));
        assertTrue(set.contains(sparkOfTyr));
        assertTrue(set.contains(fireseed));
        assertTrue(set.contains(hazadarBoots));
    }

    private void testParseAdmiralPurple1Set() {
        Optional<GearSet> oSet = admiral
                .getSet(GearSetNameConstants.SET_PURPLE_1);

        assertTrue(oSet.isPresent());
        GearSet set = oSet.get();

        assertTrue(set.contains(moonglow));
        assertTrue(set.contains(pipe));
        assertTrue(set.contains(moonOfLulithan));
        assertTrue(set.contains(sanadorianWardenHelm));
        assertTrue(set.contains(deathKite));
        assertTrue(set.contains(hazadarBoots));
    }

    private void testParseAdmiralPurple2Set() {
        Optional<GearSet> oSet = admiral
                .getSet(GearSetNameConstants.SET_PURPLE_2);

        assertTrue(oSet.isPresent());
        GearSet set = oSet.get();

        assertTrue(set.contains(yewBoltThrower));
        assertTrue(set.contains(dragonGlass));
        assertTrue(set.contains(paleGazer));
        assertTrue(set.contains(krelnMasher));
        assertTrue(set.contains(chestPlate));
        assertTrue(set.contains(hazadarBoots));
    }

    private void testParseAdmiralPurple3Set() {

        Optional<GearSet> oSet = admiral
                .getSet(GearSetNameConstants.SET_PURPLE_3);

        assertTrue(oSet.isPresent());
        GearSet set = oSet.get();

        assertTrue(set.contains(krelnHorn));
        assertTrue(set.contains(gullakAertahn));
        assertTrue(set.contains(kelossianCrusher));
        assertTrue(set.contains(vastroTooth));
        assertTrue(set.contains(deathKite));
        assertTrue(set.contains(hazadarBoots));
    }

    private void testParseAdmiralPurple4Set() {
        Optional<GearSet> oSet = admiral
                .getSet(GearSetNameConstants.SET_PURPLE_4);

        assertTrue(oSet.isPresent());
        GearSet set = oSet.get();

        assertTrue(set.contains(crusadeShield));
        assertTrue(set.contains(titansAxe));
        assertTrue(set.contains(moonglow));
        assertTrue(set.contains(heavensHalberd));
        assertTrue(set.contains(ballLightning));
        assertTrue(set.contains(allAroundShoes));
    }

    private void testParseAdmiralOrangeSet() {
        Optional<GearSet> oSet = admiral
                .getSet(GearSetNameConstants.SET_ORANGE);

        assertTrue(oSet.isPresent());
        GearSet set = oSet.get();

        assertTrue(set.contains(immortalGuardian));
        assertTrue(set.contains(dragonsScale));
        assertTrue(set.contains(emeraldPendant));
        assertTrue(set.contains(holyGarment));
        assertTrue(set.contains(allAroundShoes));
    }

}
