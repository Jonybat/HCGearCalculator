package com.hc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hc.gear.AbstractEquipment;
import com.hc.gear.GenericEquipment;
import com.hc.hero.AbstractHero;
import com.hc.hero.AbstractHeroNameComparator;
import com.hc.hero.GenericHero;

public class HCGearCalculator {

    private static HCGearCalculator instance;

    private Map<String, AbstractEquipment> gear;
    private Map<String, AbstractHero> heroes;

    private HCGearCalculator(Map<String, AbstractEquipment> gear,
            Map<String, AbstractHero> heroes) {

        if (gear == null) {
            gear = new HashMap<>();
        }
        if (heroes == null) {
            heroes = new HashMap<>();
        }
        this.gear = gear;
        this.heroes = heroes;

        GenericHero.setGear(gear);
        GenericEquipment.setGear(gear);
    }

    public static HCGearCalculator getInstance() {
        return instance;
    }

    /**
     * Instantiates the {@code HCGearCalculator} singleton if needed.
     *
     * @param gear
     * @param heroes
     */
    public static void setup(Map<String, AbstractEquipment> gear,
            Map<String, AbstractHero> heroes) {

        if (instance == null) {
            synchronized (HCGearCalculator.class) {
                if (instance == null) {
                    instance = new HCGearCalculator(gear, heroes);
                }
            }
        }
    }

    /**
     * Returns list of equipment that requires the {@code material} to be
     * crafted.
     *
     * @param material
     *            the material name
     * @return list of equipment that requires the {@code material}
     */
    public List<AbstractEquipment> getEquipmentThatRequires(
            String material) {

        return getEquipmentThatRequires(gear.get(material));
    }

    /**
     * Returns list of equipment that requires the {@code material} to be
     * crafted.
     *
     * @param material
     * @return list of equipment that requires the {@code material}
     */
    public List<AbstractEquipment> getEquipmentThatRequires(
            AbstractEquipment material) {

        if (material == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(material.requiredBy());
    }

    /**
     * Returns list of heroes that require the {@code equipment} at any point.
     *
     * @param equipment
     *            the equipment name
     * @return list of heroes that require the {@code equipment} at any point
     */
    public List<AbstractHero> getHeroesThatRequire(String equipment) {
        return getHeroesThatRequire(gear.get(equipment));
    }

    /**
     * Returns list of heroes that require the {@code equipment} at any point -
     * or any other item that requires the {@code equipment} to be crafted.
     *
     * @param equipment
     * @return list of heroes that require the {@code equipment} at any point -
     *         or any other item that requires the {@code equipment} to be
     *         crafted
     */
    public List<AbstractHero> getHeroesThatRequire(
            AbstractEquipment equipment) {

        return getHeroesThatRequire(equipment, true);
    }

    /**
     * Returns list of heroes that require the {@code equipment} at any point.<br />
     * If the {@code or any other item that requires the {@code equipment} to
     * be crafted.
     *
     * @param equipment
     * @return list of heroes that require the {@code equipment} at any point -
     *         or any other item that requires the {@code equipment} to be
     *         crafted
     */
    /**
     * * Returns list of heroes that require the {@code equipment} at any point.<br />
     * If the {@code checkHigherItems} is true, it also returns the heroes that
     * need other items that require the {@code equipment}.
     *
     * @param equipment
     * @param checkHigherItems
     * @return Returns list of heroes that require the {@code equipment} at any
     *         point
     */
    public List<AbstractHero> getHeroesThatRequire(
            AbstractEquipment equipment, boolean checkHigherItems) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes
                .values()
                .stream()
                .filter(hero -> hero.requires(equipment,
                        checkHigherItems)).collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }
}
