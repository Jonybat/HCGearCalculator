package com.hc.hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hc.hero.parse.HeroXMLParser;

/**
 * Instance of hero parsed by {@link HeroXMLParser}
 */
public class AbstractHero {
    private String name;
    private int stars;

    private List<GearSet> sets = new ArrayList<>();

    public AbstractHero(String name, int stars) {

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
    public String name() {
        return name;
    }

    /**
     *
     * @return number of stars the hero first has when acquired
     */
    public int stars() {
        return stars;
    }

    /**
     *
     * @return sets of this hero
     */
    public List<GearSet> sets() {
        return sets;
    }

    /**
     * Returns optional set with the {@code name} name.
     *
     * @param name
     * @return optional set with the {@code name} name
     */
    public Optional<GearSet> getSet(String name) {
        for (GearSet set : sets) {
            if (set.name().equals(name)) {
                return Optional.of(set);
            }
        }
        return Optional.empty();
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

}
