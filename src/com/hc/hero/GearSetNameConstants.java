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

    private static List<String> existingSets = new ArrayList<>();
    static {
        existingSets.add(SET_PURPLE);
        existingSets.add(SET_PURPLE_1);
        existingSets.add(SET_PURPLE_2);
        existingSets.add(SET_PURPLE_3);
        existingSets.add(SET_PURPLE_4);
        existingSets.add(SET_ORANGE);
    }

    /**
     * Returns the list of existing set names
     *
     * @return the list of existing set names
     */
    public static List<String> existingSets() {
        return Collections.unmodifiableList(existingSets);
    }
}
