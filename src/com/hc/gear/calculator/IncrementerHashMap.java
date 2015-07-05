package com.hc.gear.calculator;

import java.util.HashMap;
import java.util.Iterator;

import com.hc.gear.AbstractEquipment;

public class IncrementerHashMap extends
        HashMap<AbstractEquipment, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -5496145504350325114L;

    /**
     * Adds {@code quantity} to the {@code equipment} in this map.<br />
     *
     * @throws IllegalArgumentException
     *             if the {@code quantity} is negative or null
     */
    @Override
    public Integer put(AbstractEquipment equipment, Integer quantity) {
        if (quantity == null || quantity < 1) {
            throw new IllegalArgumentException(
                    "Only positive values are accepted");
        }
        Integer previousValue = get(equipment);
        int newValue = quantity
                + (previousValue == null ? 0 : previousValue);
        return super.put(equipment, newValue);
    }

    /**
     *
     * Removes {@code quantity} from the existing {@code equipment} in this map.
     *
     * @throws IllegalArgumentException
     *             if {@code equipment} is null or not an
     *             {@link AbstractEquipment} <br />
     *             if {@code quantity} is null or not an
     *             {@link AbstractEquipment}<br />
     *             if {@code quantity} is negative or if there is an attempt to
     *             remove more {@code equipment} than it has
     *
     */
    @Override
    public boolean remove(Object equipment, Object quantity) {
        if (!(equipment instanceof AbstractEquipment)
                || !(quantity instanceof Integer)) {

            String equipmentClass = equipment == null ? "null"
                    : equipment.getClass().getCanonicalName();
            String quantityClass = quantity == null ? "null" : quantity
                    .getClass().getCanonicalName();
            String msg = String
                    .format("Equipment must be an %s and quantity must be an %s. Equipment is %s and quantity is %s",
                            AbstractEquipment.class.getCanonicalName(),
                            Integer.class.getCanonicalName(),
                            equipmentClass, quantityClass);
            throw new IllegalArgumentException(msg);
        }

        AbstractEquipment equip = (AbstractEquipment) equipment;
        int removedQuantity = (Integer) quantity;

        if (removedQuantity < 0) {
            String msg = String
                    .format("Attempting to remove %d items: Can't remove negative quantities",
                            quantity);
            throw new IllegalArgumentException(msg);
        }

        int oldQuantity = get(equip);
        if (oldQuantity < removedQuantity) {
            String msg = String.format(
                    "Can't remove %d %s: only %d exist",
                    removedQuantity, equip.getClass().getSimpleName(),
                    oldQuantity);
            throw new IllegalArgumentException(msg);
        }
        int newQuantity = oldQuantity - removedQuantity;

        if (newQuantity == 0) {
            super.remove(equip);
        } else {
            super.put(equip, newQuantity);
        }
        return true;
    }

    /**
     * Returns how many of the {@code equipment} this map has.<br />
     * Having zero of the {@code equipment} results in this map's
     * {@link #keySet()} not containing the {@code equipment}
     */
    @Override
    public Integer get(Object equipment) {
        Integer quantity = super.get(equipment);
        if (quantity == null) {
            return 0;
        }
        return quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        Iterator<Entry<AbstractEquipment, Integer>> it = entrySet()
                .iterator();
        while (it.hasNext()) {
            Entry<AbstractEquipment, Integer> entry = it.next();
            String equipmentName = entry.getKey().name();
            int quantity = entry.getValue();
            sb.append(equipmentName).append("=").append(quantity);
            if (it.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
