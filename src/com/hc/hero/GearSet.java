package com.hc.hero;

import com.hc.gear.AbstractEquipment;

/**
 *
 * Instance of a set assigned to a {@linkplain Hero}
 */
public class GearSet {

    public final static int MAX_NUMBER_EQUIPMENT_PER_SET = 6;

    private String name;
    private int index;
    private AbstractEquipment[] set = new AbstractEquipment[MAX_NUMBER_EQUIPMENT_PER_SET];

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
        if (index > MAX_NUMBER_EQUIPMENT_PER_SET) {
            throw new IllegalStateException(
                    "Can't add more equipments: Set is full");
        }
        set[index++] = equipment;
    }

    /**
     * Returns {@linkplain AbstractEquipment} at the {@code index} position.
     *
     * @param index
     * @return AbstractEquipment entry at the {@code index} position
     */
    public AbstractEquipment get(int index) {
        return set[index];
    }

    /**
     * Returns true if this set contains the {@code equipment}. <br />
     * The {@code equipment} is compared by it's
     * {@linkplain AbstractEquipment#name() name} against those in the set.
     *
     * @param equipment
     * @return true if this set contains the {@code equipment}
     */
    public boolean contains(AbstractEquipment equipment) {
        if (equipment == null) {
            return false;
        }
        for (int i = 0; i < index; i++) {
            if (equipment.name().equals(set[i].name())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return name of this set
     */
    public String name() {
        return name;
    }

}
