package com.hc.gear.calculator;

import java.util.Map;
import java.util.Set;

import com.hc.gear.AbstractEquipment;

public class RequiredRawMaterialsCalculator {

    /**
     * Returns missing raw materials needed to craft the {@code equipment}.<br />
     * The map returned uses {@link AbstractEquipment} as key, and the number of
     * that equipment needed as value.
     *
     * @param equipment
     *            equipment to be crafted
     * @return missing raw materials map to craft the {@code equipment}
     */
    public Map<AbstractEquipment, Integer> getMissingRawMaterials(
            AbstractEquipment equipment) {

        IncrementerHashMap map = new IncrementerHashMap();
        map.put(equipment, 1);
        return getMissingRawMaterials(map, null);
    }

    /**
     * Returns missing raw materials needed to craft the {@code equipment},
     * having the {@code inventory} that may contain some of the required
     * materials.<br />
     * Both maps use {@link AbstractEquipment} as key, and the number of that
     * equipment needed or owned as value.
     *
     * @param equipment
     *            equipment to be crafted
     * @param inventory
     *            map of owned equipment
     * @return missing raw materials map to craft the {@code equipment}
     */
    public IncrementerHashMap getMissingRawMaterials(
            AbstractEquipment equipment, IncrementerHashMap inventory) {

        IncrementerHashMap gear = new IncrementerHashMap();
        gear.put(equipment, 1);
        return getMissingRawMaterials(gear, inventory);
    }

    /**
     * Returns missing raw materials needed to craft the {@code gear}, having
     * the {@code inventory} that may contain some of the required materials.<br />
     * All maps use {@link AbstractEquipment} as key, and the number of that
     * equipment needed or owned as value.
     *
     * @param gear
     *            gear to be crafted
     * @param inventory
     *            map of owned equipment
     * @return missing raw materials map to craft the {@code gear}
     */
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
            addRequiredMaterial(entry.getKey(), entry.getValue(),
                    inventory, requiredMaterials);
        }

        return requiredMaterials;
    }

    /**
     * Recursive method to add the number of {@code material} to the
     * {@code requiredMaterials} based on the {@code inventory}, and removes
     * used {@code material} from the inventory if needed.<br />
     * <br />
     * This method is recursive when the {@code material} is not raw and there
     * are not enough of it in the {@code inventory}, so it can check and add
     * the raw materials needed.
     *
     * @param material
     *            required material
     * @param quantity
     *            quantity of the {@code material} required
     * @param inventory
     * @param requiredMaterials
     *            map tracking all requiredMaterials, where the {@code material}
     *            is inserted if the {@code inventory} does not have enough
     *            materials to craft it.
     */
    private void addRequiredMaterial(AbstractEquipment material,
            Integer quantity, IncrementerHashMap inventory,
            IncrementerHashMap requiredMaterials) {

        quantity = removeFromInventoryIfAble(material, quantity,
                inventory);

        if (quantity == 0) {
            return;
        }

        if (material.isRaw()) {
            requiredMaterials.put(material, quantity);
        } else {
            Set<Map.Entry<AbstractEquipment, Integer>> materials = material
                    .materials().entrySet();

            for (Map.Entry<AbstractEquipment, Integer> entry : materials) {
                addRequiredMaterial(entry.getKey(), entry.getValue(),
                        inventory, requiredMaterials);
            }
        }
    }

    /**
     * Returns the new quantity of {@code material} required after using and
     * removing those available in the {@code inventory}.
     *
     * @param material
     *            material needed from the {@code inventory}
     * @param quantity
     *            quantity of the {@code material} needed
     * @param inventory
     * @return the new quantity of {@code material} required after using the
     *         ones available in the {@code inventory}
     */
    private Integer removeFromInventoryIfAble(
            AbstractEquipment material, Integer quantity,
            IncrementerHashMap inventory) {

        int itemsInInventory = inventory.get(material);

        if (itemsInInventory == 0) {
            return quantity;
        }

        if (itemsInInventory < quantity) {

            int newQuantityRequired = quantity - itemsInInventory;
            int quantityRemovedFromInventory = quantity
                    - newQuantityRequired;
            inventory.remove(material, quantityRemovedFromInventory);
            return newQuantityRequired;
        }

        inventory.remove(material, quantity);
        return 0;
    }
}
