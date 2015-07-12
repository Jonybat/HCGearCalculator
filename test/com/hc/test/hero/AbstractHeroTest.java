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

import com.hc.hero.AbstractHero;
import com.hc.test.gear.AbstractEquipmentTest;

/**
 * Common base for all unit tests using heroes, providing easy to use equipment
 * handlers
 *
 */
public class AbstractHeroTest extends AbstractEquipmentTest {

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

        admiral = getHeroes().get(ADMIRAL);
        ancientProtector = getHeroes().get(ANCIENT_PROTECTOR);
        arcaneSapper = getHeroes().get(ARCANE_SAPPER);
        cleric = getHeroes().get(CLERIC);
        cloudWalker = getHeroes().get(CLOUD_WALKER);
        depthsVoice = getHeroes().get(DEPTHS_VOICE);
        drunkenMaster = getHeroes().get(DRUNKEN_MASTER);
        emberBlade = getHeroes().get(EMBER_BLADE);
        ferryman = getHeroes().get(FERRYMAN);
        hiddenNeedle = getHeroes().get(HIDDEN_NEEDLE);
        lunarGuardian = getHeroes().get(LUNAR_GUARDIAN);
        mountain = getHeroes().get(MOUNTAIN);
        ninjaAssassin = getHeroes().get(NINJA_ASSASSIN);
        pilot = getHeroes().get(PILOT);
        rifleman = getHeroes().get(RIFLEMAN);
        savageOne = getHeroes().get(SAVAGE_ONE);
        shadowleaf = getHeroes().get(SHADOWLEAF);
        sniper = getHeroes().get(SNIPER);
        tuskedStorm = getHeroes().get(TUSKED_STORM);
        vanguardWarrior = getHeroes().get(VANGUARD_WARRIOR);
        vengeanceSpirit = getHeroes().get(VENGEANCE_SPIRIT);
        wanderingSpearman = getHeroes().get(WANDERING_SPEARMAN);
    }

}
