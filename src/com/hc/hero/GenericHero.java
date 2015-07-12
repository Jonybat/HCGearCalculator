package com.hc.hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.hc.gear.AbstractEquipment;
import com.hc.hero.parse.HeroXMLParser;

/**
 * Instance of hero parsed by {@link HeroXMLParser}
 */
public class GenericHero implements AbstractHero {
    private String name;
    private int stars;

    private List<GearSet> sets = new ArrayList<>();

    /**
     * Reference to map with existing gear
     */
    private static Map<String, AbstractEquipment> gear = new HashMap<>();

    public GenericHero(String name, int stars) {

        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException();
        }
        if (stars < 1 || stars > 3) {
            throw new IllegalArgumentException(
                    "Hero starting stars must be between 1 and 3");
        }
        this.name = name.trim();
        this.stars = stars;
    }

    /**
     *
     * @return name of the hero
     */
    @Override
    public String name() {
        return name;
    }

    /**
     *
     * @return number of stars the hero first has when acquired
     */
    @Override
    public int stars() {
        return stars;
    }

    @Override
    public boolean requires(AbstractEquipment equipment) {

        return requires(equipment, (String) null, (String) null, true);
    }

    @Override
    public boolean requires(AbstractEquipment equipment,
            boolean checkHigherItems) {

        return requires(equipment, (String) null, (String) null,
                checkHigherItems);
    }

    /**
     * @see {@linkplain #requires(AbstractEquipment, String, String)}
     */
    @Override
    public boolean requires(AbstractEquipment equipment,
            GearSet gearSet1, GearSet gearSet2) {

        String set1Name = gearSet1 == null ? null : gearSet1.name();
        String set2Name = gearSet2 == null ? null : gearSet2.name();
        return requires(equipment, set1Name, set2Name, true);
    }

    /**
     * @see {@linkplain #requires(AbstractEquipment, String, String, boolean)}
     */
    @Override
    public boolean requires(AbstractEquipment equipment,
            GearSet gearSet1, GearSet gearSet2, boolean checkHigherItems) {

        String set1Name = gearSet1 == null ? null : gearSet1.name();
        String set2Name = gearSet2 == null ? null : gearSet2.name();
        return requires(equipment, set1Name, set2Name, checkHigherItems);
    }

    @Override
    public boolean requires(AbstractEquipment equipment,
            String set1Name, String set2Name) {

        return requires(equipment, set1Name, set2Name, true);
    }

    @Override
    public boolean requires(AbstractEquipment equipment,
            String set1Name, String set2Name, boolean checkHigherItems) {

        if (set1Name == null) {
            set1Name = GearSetNameConstants.LOWEST_SET;
        }
        if (set2Name == null) {
            set2Name = GearSetNameConstants.HIGHEST_SET;
        }

        int set1Value = GearSetNameComparator.getValue(set1Name);
        int set2Value = GearSetNameComparator.getValue(set2Name);
        if (set2Value < set1Value) {
            String tmp = set1Name;
            set1Name = set2Name;
            set2Name = tmp;
        }

        List<String> setsBetween = GearSetNameConstants.getSetsBetween(
                set1Name, set2Name);

        return requires(equipment, setsBetween, checkHigherItems);
    }

    /**
     * Returns true if the hero requires the {@code equipment} in any of the
     * {@code sets}.<br />
     * If the {@code checkHigherItems} is true, it also checks whether the hero
     * ever requires any other equipment that requires the {@code equipment} to
     * be crafted.<br />
     * <br />
     * For example, if one hero never requires <b>Demon Edge</b>, but does
     * require <b>All Around Shoes</b>, which requires <b>Demon Edge</b> to be
     * crafted, this method returns true when the {@code checkHigherItems} is
     * true, and false otherwise.
     *
     * @param equipment
     * @param sets
     * @param checkHigherItems
     * @return true if the hero requires the {@code equipment} in any of the
     *         {@code sets}
     */
    private boolean requires(AbstractEquipment equipment,
            List<String> sets, boolean checkHigherItems) {

        long count = sets.stream().map(t -> getSet(t))
                .filter(Optional::isPresent).map(Optional::get)
                .filter(t -> t.contains(equipment)).count();

        if (count != 0) {
            return true;
        }

        if (!checkHigherItems) {
            return false;
        }

        Set<AbstractEquipment> higherEquipments = equipment
                .requiredBy();

        for (AbstractEquipment higherEquipment : higherEquipments) {

            count = sets.stream().map(t -> getSet(t))
                    .filter(Optional::isPresent).map(Optional::get)
                    .filter(t -> t.contains(higherEquipment)).count();

            if (count != 0) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @return sets of this hero
     */
    @Override
    public List<GearSet> sets() {
        return sets;
    }

    /**
     * Returns optional set with the {@code name} name.
     *
     * @param name
     * @return optional set with the {@code name} name
     */
    @Override
    public Optional<GearSet> getSet(String name) {
        return sets.stream().filter(t -> t.name().equals(name))
                .findFirst();
    }

    /**
     * Adds the {@code set} to the existing sets this hero uses
     *
     * @param set
     */
    public void add(GearSet set) {
        if (set == null) {
            throw new NullPointerException("Can't add null set");
        }
        if (containsSet(set)) {
            String msg = String.format(
                    "Can't have duplicate sets. Hero: %s, Set: %s",
                    name, set.name());
            throw new IllegalStateException(msg);
        }
        sets.add(set);
    }

    /**
     *
     * @param set
     * @return true if the hero already contains the {@code set}
     */
    private boolean containsSet(GearSet set) {
        for (GearSet assignedSet : sets) {
            if (set.name().equals(assignedSet.name())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GenericHero)) {
            return false;
        }
        GenericHero that = (GenericHero) obj;
        return name.equals(that.name);
    }

    /**
     *
     * Keeps a reference to the existing gear
     *
     * @param gear
     */
    public static void setGear(Map<String, AbstractEquipment> gear) {
        if (gear == null || gear.entrySet().isEmpty()) {
            return;
        }
        GenericHero.gear = gear;
    }
}
