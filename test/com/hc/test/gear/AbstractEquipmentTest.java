package com.hc.test.gear;

import static com.hc.gear.EquipmentConstants.ALL_AROUND_SHOES;
import static com.hc.gear.EquipmentConstants.BAG_OF_HOLDING;
import static com.hc.gear.EquipmentConstants.BALL_LIGHTNING;
import static com.hc.gear.EquipmentConstants.CHEST_PLATE;
import static com.hc.gear.EquipmentConstants.COPPER_CUDGEL;
import static com.hc.gear.EquipmentConstants.CRUSADE_SHIELD;
import static com.hc.gear.EquipmentConstants.DEATH_KITE;
import static com.hc.gear.EquipmentConstants.DECEIVERS_SWORD;
import static com.hc.gear.EquipmentConstants.DEMON_EDGE;
import static com.hc.gear.EquipmentConstants.DIRKS_OF_CICERO;
import static com.hc.gear.EquipmentConstants.DRAGONS_SCALE;
import static com.hc.gear.EquipmentConstants.DRAGON_GLASS;
import static com.hc.gear.EquipmentConstants.EARTH_HAMMER;
import static com.hc.gear.EquipmentConstants.EMERALD_PENDANT;
import static com.hc.gear.EquipmentConstants.EMERALD_RING;
import static com.hc.gear.EquipmentConstants.EULS_SCEPTER_OF_DIVINITY;
import static com.hc.gear.EquipmentConstants.FIRESEED;
import static com.hc.gear.EquipmentConstants.GLOVES_OF_HASTE;
import static com.hc.gear.EquipmentConstants.GULLAK_AERTAHN;
import static com.hc.gear.EquipmentConstants.HAZADAR_BOOTS;
import static com.hc.gear.EquipmentConstants.HEAVENS_HALBERD;
import static com.hc.gear.EquipmentConstants.HELM_OF_MAGNUS_THORNE;
import static com.hc.gear.EquipmentConstants.HOLY_GARMENT;
import static com.hc.gear.EquipmentConstants.IMMORTAL_GUARDIAN;
import static com.hc.gear.EquipmentConstants.KELOSSIAN_CRUSHER;
import static com.hc.gear.EquipmentConstants.KRELN_HORN;
import static com.hc.gear.EquipmentConstants.KRELN_HORN_REEL_FRAGMENT;
import static com.hc.gear.EquipmentConstants.KRELN_MASHER;
import static com.hc.gear.EquipmentConstants.MOONGLOW;
import static com.hc.gear.EquipmentConstants.MOON_OF_LULITHAN;
import static com.hc.gear.EquipmentConstants.PALE_GAZER;
import static com.hc.gear.EquipmentConstants.PIPE;
import static com.hc.gear.EquipmentConstants.RAVENS_BEAK;
import static com.hc.gear.EquipmentConstants.ROUGH_HEWN_AXE_FRAGMENT;
import static com.hc.gear.EquipmentConstants.SANADORIAN_WARDEN_HELM;
import static com.hc.gear.EquipmentConstants.SILVER_LANCE_FRAGMENT;
import static com.hc.gear.EquipmentConstants.SPARK_OF_TYR;
import static com.hc.gear.EquipmentConstants.THUNDER_SWORD;
import static com.hc.gear.EquipmentConstants.TITANS_AXE;
import static com.hc.gear.EquipmentConstants.TITANS_AXE_FRAGMENT;
import static com.hc.gear.EquipmentConstants.TUSK_SIGIL;
import static com.hc.gear.EquipmentConstants.VASTRO_TOOTH;
import static com.hc.gear.EquipmentConstants.YEW_BOLT_THROWER;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hc.HCGearCalculator;
import com.hc.gear.AbstractEquipment;
import com.hc.gear.parse.GearXMLParser;
import com.hc.hero.AbstractHero;
import com.hc.hero.parse.HeroXMLParser;

/**
 * Common base for all unit tests using equipment, providing easy to use
 * equipment handlers
 *
 */
public class AbstractEquipmentTest {

    private Map<String, AbstractEquipment> gear;
    private Map<String, AbstractHero> heroes;

    protected final AbstractEquipment deceiversSword;
    protected final AbstractEquipment glovesOfHaste;
    protected final AbstractEquipment titansAxeFragment;
    protected final AbstractEquipment krelnHornReelFragment;
    protected final AbstractEquipment roughHewnAxeFragment;
    protected final AbstractEquipment tuskSigil;
    protected final AbstractEquipment helmofMagnusThorne;
    protected final AbstractEquipment silverLanceFragment;
    protected final AbstractEquipment sanadorianWardenHelm;
    protected final AbstractEquipment ballLightning;
    protected final AbstractEquipment dirksOfCicero;
    protected final AbstractEquipment vastroTooth;
    protected final AbstractEquipment sparkOfTyr;
    protected final AbstractEquipment fireseed;
    protected final AbstractEquipment hazadarBoots;
    protected final AbstractEquipment moonglow;
    protected final AbstractEquipment pipe;
    protected final AbstractEquipment moonOfLulithan;
    protected final AbstractEquipment deathKite;
    protected final AbstractEquipment yewBoltThrower;
    protected final AbstractEquipment dragonGlass;
    protected final AbstractEquipment paleGazer;
    protected final AbstractEquipment krelnMasher;
    protected final AbstractEquipment chestPlate;
    protected final AbstractEquipment krelnHorn;
    protected final AbstractEquipment gullakAertahn;
    protected final AbstractEquipment kelossianCrusher;
    protected final AbstractEquipment crusadeShield;
    protected final AbstractEquipment ravensBeak;
    protected final AbstractEquipment titansAxe;
    protected final AbstractEquipment thunderSword;
    protected final AbstractEquipment emeraldRing;
    protected final AbstractEquipment heavensHalberd;
    protected final AbstractEquipment allAroundShoes;
    protected final AbstractEquipment immortalGuardian;
    protected final AbstractEquipment dragonsScale;
    protected final AbstractEquipment emeraldPendant;
    protected final AbstractEquipment holyGarment;
    protected final AbstractEquipment bagOfHolding;
    protected final AbstractEquipment demonEdge;
    protected final AbstractEquipment copperCudgel;
    protected final AbstractEquipment eulsScepterOfDivinity;
    protected final AbstractEquipment earthHammer;

    public AbstractEquipmentTest() {
        gear = getGearParser().gear();
        heroes = getHeroesParser().heroes();
        HCGearCalculator.setup(getGear(), heroes);

        deceiversSword = gear.get(DECEIVERS_SWORD);
        emeraldRing = gear.get(EMERALD_RING);
        eulsScepterOfDivinity = gear.get(EULS_SCEPTER_OF_DIVINITY);
        glovesOfHaste = gear.get(GLOVES_OF_HASTE);
        titansAxeFragment = gear.get(TITANS_AXE_FRAGMENT);
        krelnHornReelFragment = gear.get(KRELN_HORN_REEL_FRAGMENT);
        roughHewnAxeFragment = gear.get(ROUGH_HEWN_AXE_FRAGMENT);
        thunderSword = gear.get(THUNDER_SWORD);
        tuskSigil = gear.get(TUSK_SIGIL);
        helmofMagnusThorne = gear.get(HELM_OF_MAGNUS_THORNE);
        silverLanceFragment = gear.get(SILVER_LANCE_FRAGMENT);
        sanadorianWardenHelm = gear.get(SANADORIAN_WARDEN_HELM);
        ballLightning = gear.get(BALL_LIGHTNING);
        dirksOfCicero = gear.get(DIRKS_OF_CICERO);
        vastroTooth = gear.get(VASTRO_TOOTH);
        sparkOfTyr = gear.get(SPARK_OF_TYR);
        fireseed = gear.get(FIRESEED);
        hazadarBoots = gear.get(HAZADAR_BOOTS);
        moonglow = gear.get(MOONGLOW);
        pipe = gear.get(PIPE);
        moonOfLulithan = gear.get(MOON_OF_LULITHAN);
        deathKite = gear.get(DEATH_KITE);
        yewBoltThrower = gear.get(YEW_BOLT_THROWER);
        dragonGlass = gear.get(DRAGON_GLASS);
        paleGazer = gear.get(PALE_GAZER);
        krelnMasher = gear.get(KRELN_MASHER);
        chestPlate = gear.get(CHEST_PLATE);
        krelnHorn = gear.get(KRELN_HORN);
        gullakAertahn = gear.get(GULLAK_AERTAHN);
        kelossianCrusher = gear.get(KELOSSIAN_CRUSHER);
        crusadeShield = gear.get(CRUSADE_SHIELD);
        titansAxe = gear.get(TITANS_AXE);
        heavensHalberd = gear.get(HEAVENS_HALBERD);
        allAroundShoes = gear.get(ALL_AROUND_SHOES);
        immortalGuardian = gear.get(IMMORTAL_GUARDIAN);
        dragonsScale = gear.get(DRAGONS_SCALE);
        emeraldPendant = gear.get(EMERALD_PENDANT);
        holyGarment = gear.get(HOLY_GARMENT);
        bagOfHolding = gear.get(BAG_OF_HOLDING);
        demonEdge = gear.get(DEMON_EDGE);
        copperCudgel = gear.get(COPPER_CUDGEL);
        ravensBeak = gear.get(RAVENS_BEAK);
        earthHammer = gear.get(EARTH_HAMMER);
    }

    private GearXMLParser getGearParser() {
        try {
            return new GearXMLParser("Gear.xml");
        } catch (ParserConfigurationException | SAXException
                | IOException e) {
            // assume the file is there and has correct syntax
            e.printStackTrace();
            return null;
        }
    }

    private HeroXMLParser getHeroesParser() {
        try {
            return new HeroXMLParser("Heroes.xml", getGear());
        } catch (ParserConfigurationException | SAXException
                | IOException e) {
            // assume the file is there and has correct syntax
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @return unmodifiable copy of the parsed gear
     */
    protected Map<String, AbstractEquipment> getGear() {
        return Collections.unmodifiableMap(gear);
    }

    /**
     *
     * @return unmodifiable copy of the parsed heroes
     */
    protected Map<String, AbstractHero> getHeroes() {
        return Collections.unmodifiableMap(heroes);
    }

}
