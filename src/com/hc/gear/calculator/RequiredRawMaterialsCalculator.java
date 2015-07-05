package com.hc.gear.calculator;

import java.util.Map;
import java.util.Set;

import com.hc.gear.AbstractEquipment;

public class RequiredRawMaterialsCalculator {

    public Map<AbstractEquipment, Integer> getMissingRawMaterials(
            AbstractEquipment equipment) {

        IncrementerHashMap map = new IncrementerHashMap();
        map.put(equipment, 1);
        return getMissingRawMaterials(map, null);
    }

    public IncrementerHashMap getMissingRawMaterials(
            AbstractEquipment equipment, IncrementerHashMap inventory) {

        IncrementerHashMap gear = new IncrementerHashMap();
        gear.put(equipment, 1);
        return getMissingRawMaterials(gear, inventory);
    }

    public IncrementerHashMap getMissingRawMaterials(
            IncrementerHashMap gear, IncrementerHashMap inventory) {

        if (inventory == null) {
            inventory = new IncrementerHashMap();
        }
        if (gear == null) {
            return new IncrementerHashMap();
        }

        IncrementerHashMap requiredMaterials = new IncrementerHashMap();
        Set<Map.Entry<AbstractEquipment, Integer>> entries = gear
                .entrySet();

        for (Map.Entry<AbstractEquipment, Integer> entry : entries) {
            add(entry.getKey(), entry.getValue(), inventory,
                    requiredMaterials);
        }

        return requiredMaterials;
    }

    private void add(AbstractEquipment equipment,
            Integer quantityRequired, IncrementerHashMap inventory,
            IncrementerHashMap requiredMaterials) {

        quantityRequired = removeFromInventoryIfAble(equipment,
                quantityRequired, inventory);

        if (quantityRequired == 0) {
            return;
        }

        if (equipment.isRaw()) {
            requiredMaterials.put(equipment, quantityRequired);
        } else {
            Set<Map.Entry<AbstractEquipment, Integer>> materials = equipment
                    .materials().entrySet();

            for (Map.Entry<AbstractEquipment, Integer> entry : materials) {
                add(entry.getKey(), entry.getValue(), inventory,
                        requiredMaterials);
            }
        }
    }

    /**
     * Returns quantity required after using those in the inventory
     *
     * @param equipment
     * @param quantityRequired
     * @param inventory
     * @return
     */
    private Integer removeFromInventoryIfAble(
            AbstractEquipment equipment, Integer quantityRequired,
            IncrementerHashMap inventory) {

        int itemsInInventory = inventory.get(equipment);
        if (itemsInInventory < quantityRequired) {

            int newQuantityRequired = quantityRequired
                    - itemsInInventory;
            int quantityRemovedFromInventory = quantityRequired
                    - newQuantityRequired;
            inventory.remove(equipment, quantityRemovedFromInventory);
            return newQuantityRequired;
        }

        inventory.remove(equipment, quantityRequired);
        return 0;
    }
}
