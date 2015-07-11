package com.hc.hero;

import java.util.Comparator;

/**
 *
 * Comparator to sort sets according to their strength
 */
public class GearSetNameComparator implements Comparator<String> {

    public final static int getValue(String setName) {

        if (!GearSetNameConstants.existingSets().contains(setName)) {
            throw new IllegalArgumentException(String.format(
                    "Invalid set name: %s", setName));
        }

        int value = 0;

        if (setName.startsWith(GearSetNameConstants.SET_PURPLE)) {
            value += 40;
        } else if (setName.startsWith(GearSetNameConstants.SET_ORANGE)) {
            value += 50;
        }

        if (setName.matches(".*\\+[0-9]+")) {
            int plusIndex = setName.indexOf('+');
            value += Integer.parseInt(setName.substring(plusIndex + 1));
        }
        return value;
    }

    @Override
    public int compare(String set1Name, String set2Name) {

        int set1Value = getValue(set1Name);
        int set2Value = getValue(set2Name);

        return set1Value - set2Value;
    }

}
