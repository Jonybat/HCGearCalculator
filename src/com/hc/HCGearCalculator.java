package com.hc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hc.gear.AbstractEquipment;
import com.hc.hero.AbstractHero;
import com.hc.hero.AbstractHeroNameComparator;

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
        return gear.values().stream().filter(t -> t.requires(material))
                .collect(Collectors.toList());
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
     * Returns list of heroes that require the {@code equipment} at any point.
     *
     * @param equipment
     * @return list of heroes that require the {@code equipment} at any point
     */
    public List<AbstractHero> getHeroesThatRequire(
            AbstractEquipment equipment) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes.values().stream()
                .filter(t -> t.requires(equipment))
                .collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }
}
