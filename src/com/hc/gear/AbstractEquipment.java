package com.hc.gear;

import java.util.Map;

public interface AbstractEquipment {
    /**
     * @return name of the equipment
     */
    String name();

    /**
     *
     * @return type of the equipment, or null if it is a regular item
     */
    String type();

    /**
     * @return color of the equipment
     */
    String color();

    /**
     *
     * Returns map of needed {@code equipment} required to craft this equipment.<br />
     * The map contains only the first level materials this {@code equipment}
     * needs.<br />
     * <br />
     *
     *
     * The map returned uses {@link AbstractEquipment} as key, and the number of
     * that equipment needed to be crafted as value.
     *
     * @return map of needed {@code equipment} required to craft this equipment
     */
    Map<AbstractEquipment, Integer> materials();

    /**
     * Returns true if this equipment or any of its materials requires the
     * {@code material} to be crafted. <br />
     *
     *
     * @param material
     * @return true if this equipment or any of its materials requires the
     *         {@code material} to be crafted
     */
    boolean requires(AbstractEquipment material);

    /**
     *
     * @return true if this equipment cannot be crafted, meaning, it can only be
     *         found or bought as is.
     */
    boolean isRaw();
}
