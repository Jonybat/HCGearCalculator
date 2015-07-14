package com.hc.hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Class listing every set's name a hero has
 */
public class GearSetNameConstants {

    public final static String SET_PURPLE = "Purple";
    public final static String SET_PURPLE_1 = "Purple+1";
    public final static String SET_PURPLE_2 = "Purple+2";
    public final static String SET_PURPLE_3 = "Purple+3";
    public final static String SET_PURPLE_4 = "Purple+4";
    public final static String SET_ORANGE = "Orange";
    public final static String SET_ORANGE_1 = "Orange+1";
    public final static String SET_ORANGE_2 = "Orange+2";

    public final static String LOWEST_SET = SET_PURPLE;
    public final static String HIGHEST_SET = SET_ORANGE_2;

    // weights used to know if a set is higher or lower than another
    private static List<String> existingSets = new ArrayList<>();

    static {
        existingSets.add(SET_PURPLE);
        existingSets.add(SET_PURPLE_1);
        existingSets.add(SET_PURPLE_2);
        existingSets.add(SET_PURPLE_3);
        existingSets.add(SET_PURPLE_4);
        existingSets.add(SET_ORANGE);
        existingSets.add(SET_ORANGE_1);
        existingSets.add(SET_ORANGE_2);

        existingSets.sort(new GearSetNameComparator());
    }

    /**
     * Returns List of existing {@linkplain GearSet} names
     *
     * @return List of existing {@linkplain GearSet} names
     */
    public static List<String> existingSets() {
        return Collections.unmodifiableList(existingSets);
    }

    /**
     * Returns sets between the {@code set1Name} and the {@code set2Name}.<br />
     *
     * @param set1Name
     * @param set2Name
     * @return sets between the {@code set1Name} and the {@code set2Name}
     * @throws IndexOutOfBoundsException
     *             if either {@code set1Name} or {@code set2Name} is an unknown
     *             set, or if the {@code set1Name} is a stronger set than the
     *             {@code set2Name}
     */
    public static List<String> getSetsBetween(String set1Name,
            String set2Name) {

        int set1Index = existingSets.indexOf(set1Name);
        int set2Index = existingSets.indexOf(set2Name);

        return Collections.unmodifiableList(existingSets.subList(
                set1Index, set2Index + 1));
    }
}
