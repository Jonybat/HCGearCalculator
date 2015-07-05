package com.hc.gear;

import java.util.Comparator;

public class AbstractEquipmentComparator implements
        Comparator<AbstractEquipment> {

    @Override
    public int compare(AbstractEquipment eq1, AbstractEquipment eq2) {
        return eq1.name().compareTo(eq2.name());
    }

}
