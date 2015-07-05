package com.hc.gear;

import java.util.Comparator;

/**
 * Compares {@link AbstractEquipment} based on their
 * {@link AbstractEquipment#name()} only
 */
public class AbstractEquipmentNameComparator implements
        Comparator<AbstractEquipment> {

    @Override
    public int compare(AbstractEquipment eq1, AbstractEquipment eq2) {
        return eq1.name().compareTo(eq2.name());
    }

}
