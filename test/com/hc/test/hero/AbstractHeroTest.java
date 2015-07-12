package com.hc.test.hero;

import static com.hc.gear.HeroConstants.ADMIRAL;
import static com.hc.gear.HeroConstants.ANCIENT_PROTECTOR;
import static com.hc.gear.HeroConstants.ARCANE_SAPPER;
import static com.hc.gear.HeroConstants.CLERIC;
import static com.hc.gear.HeroConstants.CLOUD_WALKER;
import static com.hc.gear.HeroConstants.COMMANDO;
import static com.hc.gear.HeroConstants.DEATHGORE;
import static com.hc.gear.HeroConstants.DEPTHS_VOICE;
import static com.hc.gear.HeroConstants.DRUNKEN_MASTER;
import static com.hc.gear.HeroConstants.EMBER_BLADE;
import static com.hc.gear.HeroConstants.FERRYMAN;
import static com.hc.gear.HeroConstants.HIDDEN_NEEDLE;
import static com.hc.gear.HeroConstants.IRON_HOOF;
import static com.hc.gear.HeroConstants.LIGHTNING_ELEMENTAL;
import static com.hc.gear.HeroConstants.LUNAR_GUARDIAN;
import static com.hc.gear.HeroConstants.MOUNTAIN;
import static com.hc.gear.HeroConstants.NINJA_ASSASSIN;
import static com.hc.gear.HeroConstants.PILOT;
import static com.hc.gear.HeroConstants.POISONED_ONE;
import static com.hc.gear.HeroConstants.PROFESSIONAL_KILLER;
import static com.hc.gear.HeroConstants.PSYCHIC_SWORD;
import static com.hc.gear.HeroConstants.RIFLEMAN;
import static com.hc.gear.HeroConstants.SAVAGE_ONE;
import static com.hc.gear.HeroConstants.SHADOWLEAF;
import static com.hc.gear.HeroConstants.SNIPER;
import static com.hc.gear.HeroConstants.TUSKED_STORM;
import static com.hc.gear.HeroConstants.VANGUARD_WARRIOR;
import static com.hc.gear.HeroConstants.VENGEANCE_SPIRIT;
import static com.hc.gear.HeroConstants.WANDERING_SPEARMAN;
import static com.hc.gear.HeroConstants.WARRIOR_MONK;
import static com.hc.gear.HeroConstants.WIND_MASTER;

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
    protected final AbstractHero commando;
    protected final AbstractHero deathgore;
    protected final AbstractHero depthsVoice;
    protected final AbstractHero drunkenMaster;
    protected final AbstractHero emberBlade;
    protected final AbstractHero ferryman;
    protected final AbstractHero hiddenNeedle;
    protected final AbstractHero ironHoof;
    protected final AbstractHero lightningElemental;
    protected final AbstractHero lunarGuardian;
    protected final AbstractHero mountain;
    protected final AbstractHero ninjaAssassin;
    protected final AbstractHero pilot;
    protected final AbstractHero poisonedOne;
    protected final AbstractHero professionalKiller;
    protected final AbstractHero psychicSword;
    protected final AbstractHero rifleman;
    protected final AbstractHero savageOne;
    protected final AbstractHero shadowleaf;
    protected final AbstractHero sniper;
    protected final AbstractHero tuskedStorm;
    protected final AbstractHero vanguardWarrior;
    protected final AbstractHero vengeanceSpirit;
    protected final AbstractHero wanderingSpearman;
    protected final AbstractHero warriorMonk;
    protected final AbstractHero windMaster;

    public AbstractHeroTest() {
        super();

        admiral = getHeroes().get(ADMIRAL);
        ancientProtector = getHeroes().get(ANCIENT_PROTECTOR);
        arcaneSapper = getHeroes().get(ARCANE_SAPPER);
        cleric = getHeroes().get(CLERIC);
        cloudWalker = getHeroes().get(CLOUD_WALKER);
        commando = getHeroes().get(COMMANDO);
        deathgore = getHeroes().get(DEATHGORE);
        depthsVoice = getHeroes().get(DEPTHS_VOICE);
        drunkenMaster = getHeroes().get(DRUNKEN_MASTER);
        emberBlade = getHeroes().get(EMBER_BLADE);
        ferryman = getHeroes().get(FERRYMAN);
        hiddenNeedle = getHeroes().get(HIDDEN_NEEDLE);
        ironHoof = getHeroes().get(IRON_HOOF);
        lightningElemental = getHeroes().get(LIGHTNING_ELEMENTAL);
        lunarGuardian = getHeroes().get(LUNAR_GUARDIAN);
        mountain = getHeroes().get(MOUNTAIN);
        ninjaAssassin = getHeroes().get(NINJA_ASSASSIN);
        pilot = getHeroes().get(PILOT);
        poisonedOne = getHeroes().get(POISONED_ONE);
        professionalKiller = getHeroes().get(PROFESSIONAL_KILLER);
        psychicSword = getHeroes().get(PSYCHIC_SWORD);
        rifleman = getHeroes().get(RIFLEMAN);
        savageOne = getHeroes().get(SAVAGE_ONE);
        shadowleaf = getHeroes().get(SHADOWLEAF);
        sniper = getHeroes().get(SNIPER);
        tuskedStorm = getHeroes().get(TUSKED_STORM);
        vanguardWarrior = getHeroes().get(VANGUARD_WARRIOR);
        vengeanceSpirit = getHeroes().get(VENGEANCE_SPIRIT);
        wanderingSpearman = getHeroes().get(WANDERING_SPEARMAN);
        warriorMonk = getHeroes().get(WARRIOR_MONK);
        windMaster = getHeroes().get(WIND_MASTER);
    }

}
