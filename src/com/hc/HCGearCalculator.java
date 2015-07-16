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
import com.hc.hero.GearSet;
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
     * Returns list of heroes that equip the {@code equipment} at any point.
     *
     * @param equipment
     *            the equipment name
     * @return list of heroes that equip the {@code equipment} at any point
     */
    public List<AbstractHero> getHeroesThatEquip(String equipment) {
        return getHeroesThatEquip(gear.get(equipment));
    }

    /**
     * Returns list of heroes that require the {@code equipment} at any point.<br />
     *
     * @param equipment
     * @return Returns list of heroes that require the {@code equipment} at any
     *         point
     *
     * @see AbstractHero#requires(AbstractEquipment)
     */
    public List<AbstractHero> getHeroesThatRequire(
            AbstractEquipment equipment) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes.values().stream()
                .filter(hero -> hero.requires(equipment))
                .collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }

    /**
     * * Returns list of heroes that require the {@code equipment} between the
     * {@code gearSet1} and the {@code gearSet2}.<br />
     *
     * @param equipment
     * @param gearSet1
     * @param gearSet2
     * @return Returns list of heroes that require the {@code equipment} between
     *         the {@code gearSet1} and the {@code gearSet2}.
     *
     * @see AbstractHero#requires(AbstractEquipment, GearSet, GearSet)
     */
    public List<AbstractHero> getHeroesThatRequire(
            AbstractEquipment equipment, GearSet gearSet1,
            GearSet gearSet2) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes
                .values()
                .stream()
                .filter(hero -> hero.requires(equipment, gearSet1,
                        gearSet2)).collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }

    /**
     * * Returns list of heroes that require the {@code equipment} between the
     * {@code set1Name} and the {@code set2Name}.<br />
     *
     * @param equipment
     * @param set1Name
     * @param set2Name
     * @return Returns list of heroes that require the {@code equipment} between
     *         the {@code set1Name} and the {@code set2Name}.
     *
     * @see AbstractHero#requires(AbstractEquipment, String, String)
     */
    public List<AbstractHero> getHeroesThatRequire(
            AbstractEquipment equipment, String set1Name,
            String set2Name) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes
                .values()
                .stream()
                .filter(hero -> hero.requires(equipment, set1Name,
                        set2Name)).collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }

    /**
     * * Returns list of heroes that equip the {@code equipment} at any point.<br />
     *
     * @param equipment
     * @return Returns list of heroes that equip the {@code equipment} at any
     *         point
     */
    public List<AbstractHero> getHeroesThatEquip(
            AbstractEquipment equipment) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes.values().stream()
                .filter(hero -> hero.equips(equipment))
                .collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }

    /**
     * * Returns list of heroes that equip the {@code equipment} between the
     * {@code gearSet1} and the {@code gearSet2}.<br />
     *
     * @param equipment
     * @param gearSet1
     * @param gearSet2
     * @return Returns list of heroes that equip the {@code equipment} between
     *         the {@code gearSet1} and the {@code gearSet2}
     *
     * @see AbstractHero#equips(AbstractEquipment, GearSet, GearSet)
     */
    public List<AbstractHero> getHeroesThatEquip(
            AbstractEquipment equipment, GearSet gearSet1,
            GearSet gearSet2) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes
                .values()
                .stream()
                .filter(hero -> hero.equips(equipment, gearSet1,
                        gearSet2)).collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }

    /**
     * * Returns list of heroes that equip the {@code equipment} between the
     * {@code set1Name} and the {@code set2Name}.<br />
     *
     * @param equipment
     * @param set1Name
     * @param set2Name
     * @return Returns list of heroes that equip the {@code equipment} between
     *         the {@code set1Name} and the {@code set2Name}
     *
     * @see AbstractHero#equips(AbstractEquipment, String, String)
     */
    public List<AbstractHero> getHeroesThatEquip(
            AbstractEquipment equipment, String set1Name,
            String set2Name) {

        if (equipment == null) {
            return new ArrayList<>();
        }

        List<AbstractHero> list = heroes
                .values()
                .stream()
                .filter(hero -> hero.equips(equipment, set1Name,
                        set2Name)).collect(Collectors.toList());
        list.sort(new AbstractHeroNameComparator());
        return list;
    }
}
