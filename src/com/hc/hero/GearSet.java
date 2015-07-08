package com.hc.hero;

import java.util.ArrayList;
import java.util.List;

import com.hc.gear.AbstractEquipment;

/**
 *
 * Instance of a set assigned to a {@linkplain Hero}
 */
public class GearSet {

    public final static int MAX_NUMBER_EQUIPMENT_PER_SET = 6;

    private String name;
    private List<AbstractEquipment> set = new ArrayList<>(
            MAX_NUMBER_EQUIPMENT_PER_SET);

    public GearSet(String name) {
        if (name == null
                || !GearSetNameConstants.existingSets().contains(name)) {
            throw new IllegalArgumentException(String.format(
                    "Unknown set: %s", name));
        }
        this.name = name;
    }

    public void add(AbstractEquipment equipment) {
        if (equipment == null) {
            throw new NullPointerException(
                    "Can't add null entry to a set");
        }
        if (set.size() == MAX_NUMBER_EQUIPMENT_PER_SET) {
            throw new IllegalStateException(
                    "Can't add more equipments: Set is full");
        }
        set.add(equipment);
    }

    /**
     * Returns true if this set contains the {@code equipment}. <br />
     *
     * @param equipment
     * @return true if this set contains the {@code equipment}
     */
    public boolean contains(AbstractEquipment equipment) {
        if (equipment == null) {
            return false;
        }

        return set.contains(equipment);
    }

    /**
     *
     * @return name of this set
     */
    public String name() {
        return name;
    }

}
