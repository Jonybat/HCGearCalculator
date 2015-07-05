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

    @Override
    public Integer put(AbstractEquipment key, Integer value) {
        if (value == null || value < 1) {
            throw new IllegalArgumentException(
                    "Only positive values are accepted");
        }
        Integer previousValue = get(key);
        int newValue = value
                + (previousValue == null ? 0 : previousValue);
        return super.put(key, newValue);
    }

    @Override
    public boolean remove(Object key, Object value) {
        if (!(key instanceof AbstractEquipment)
                || !(value instanceof Integer)) {
            return false;
        }

        AbstractEquipment equipment = (AbstractEquipment) key;
        int removedQuantity = (Integer) value;

        if (removedQuantity < 0) {
            String msg = String
                    .format("Attempting to remove %d items: Can't remove negative quantities",
                            value);
            throw new IllegalArgumentException(msg);
        }

        int oldQuantity = get(equipment);
        if (oldQuantity < removedQuantity) {
            String msg = String.format(
                    "Can't remove %d %s: only %d exist",
                    removedQuantity, equipment.getClass()
                            .getSimpleName(), oldQuantity);
            throw new IllegalArgumentException(msg);
        }
        int newQuantity = oldQuantity - removedQuantity;

        if (newQuantity == 0) {
            super.remove(equipment);
        } else {
            super.put(equipment, newQuantity);
        }
        return true;
    }

    @Override
    public Integer get(Object key) {
        Integer quantity = super.get(key);
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
