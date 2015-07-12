package com.hc.test.hero;

import static com.hc.gear.HeroConstants.ADMIRAL;
import static com.hc.gear.HeroConstants.ANCIENT_PROTECTOR;
import static com.hc.gear.HeroConstants.ARCANE_SAPPER;
import static com.hc.gear.HeroConstants.CLERIC;
import static com.hc.gear.HeroConstants.CLOUD_WALKER;
import static com.hc.gear.HeroConstants.DEPTHS_VOICE;
import static com.hc.gear.HeroConstants.DRUNKEN_MASTER;
import static com.hc.gear.HeroConstants.EMBER_BLADE;
import static com.hc.gear.HeroConstants.FERRYMAN;
import static com.hc.gear.HeroConstants.HIDDEN_NEEDLE;
import static com.hc.gear.HeroConstants.LUNAR_GUARDIAN;
import static com.hc.gear.HeroConstants.MOUNTAIN;
import static com.hc.gear.HeroConstants.NINJA_ASSASSIN;
import static com.hc.gear.HeroConstants.PILOT;
import static com.hc.gear.HeroConstants.RIFLEMAN;
import static com.hc.gear.HeroConstants.SAVAGE_ONE;
import static com.hc.gear.HeroConstants.SHADOWLEAF;
import static com.hc.gear.HeroConstants.SNIPER;
import static com.hc.gear.HeroConstants.TUSKED_STORM;
import static com.hc.gear.HeroConstants.VANGUARD_WARRIOR;
import static com.hc.gear.HeroConstants.VENGEANCE_SPIRIT;
import static com.hc.gear.HeroConstants.WANDERING_SPEARMAN;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hc.HCGearCalculator;
import com.hc.hero.AbstractHero;
import com.hc.hero.parse.HeroXMLParser;
import com.hc.test.gear.AbstractEquipmentTest;

/**
 * Common base for all unit tests using heroes, providing easy to use equipment
 * handlers
 *
 */
public class AbstractHeroTest extends AbstractEquipmentTest {

    private HCGearCalculator calculator;

    private Map<String, AbstractHero> heroes;

    protected final AbstractHero admiral;
    protected final AbstractHero ancientProtector;
    protected final AbstractHero arcaneSapper;
    protected final AbstractHero cleric;
    protected final AbstractHero cloudWalker;
    protected final AbstractHero depthsVoice;
    protected final AbstractHero drunkenMaster;
    protected final AbstractHero emberBlade;
    protected final AbstractHero ferryman;
    protected final AbstractHero hiddenNeedle;
    protected final AbstractHero lunarGuardian;
    protected final AbstractHero mountain;
    protected final AbstractHero ninjaAssassin;
    protected final AbstractHero pilot;
    protected final AbstractHero rifleman;
    protected final AbstractHero savageOne;
    protected final AbstractHero shadowleaf;
    protected final AbstractHero sniper;
    protected final AbstractHero tuskedStorm;
    protected final AbstractHero vanguardWarrior;
    protected final AbstractHero vengeanceSpirit;
    protected final AbstractHero wanderingSpearman;

    public AbstractHeroTest() {
        super();

        heroes = getParser().heroes();
        calculator = new HCGearCalculator(getGear(), heroes);

        admiral = heroes.get(ADMIRAL);
        ancientProtector = heroes.get(ANCIENT_PROTECTOR);
        arcaneSapper = heroes.get(ARCANE_SAPPER);
        cleric = heroes.get(CLERIC);
        cloudWalker = heroes.get(CLOUD_WALKER);
        depthsVoice = heroes.get(DEPTHS_VOICE);
        drunkenMaster = heroes.get(DRUNKEN_MASTER);
        emberBlade = heroes.get(EMBER_BLADE);
        ferryman = heroes.get(FERRYMAN);
        hiddenNeedle = heroes.get(HIDDEN_NEEDLE);
        lunarGuardian = heroes.get(LUNAR_GUARDIAN);
        mountain = heroes.get(MOUNTAIN);
        ninjaAssassin = heroes.get(NINJA_ASSASSIN);
        pilot = heroes.get(PILOT);
        rifleman = heroes.get(RIFLEMAN);
        savageOne = heroes.get(SAVAGE_ONE);
        shadowleaf = heroes.get(SHADOWLEAF);
        sniper = heroes.get(SNIPER);
        tuskedStorm = heroes.get(TUSKED_STORM);
        vanguardWarrior = heroes.get(VANGUARD_WARRIOR);
        vengeanceSpirit = heroes.get(VENGEANCE_SPIRIT);
        wanderingSpearman = heroes.get(WANDERING_SPEARMAN);
    }

    private HeroXMLParser getParser() {
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
     * @return unmodifiable copy of the parsed heroes
     */
    protected Map<String, AbstractHero> getHeroes() {
        return Collections.unmodifiableMap(heroes);
    }

    /**
     *
     * @return {@linkplain HCGearCalculator} instance
     */
    protected HCGearCalculator calculator() {
        return calculator;
    }
}
