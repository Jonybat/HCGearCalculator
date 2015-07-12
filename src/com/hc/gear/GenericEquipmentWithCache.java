package com.hc.gear;

import java.util.HashMap;
import java.util.Map;

public class GenericEquipmentWithCache extends GenericEquipment {

    private Map<AbstractEquipment, Boolean> cache = new HashMap<>();

    public GenericEquipmentWithCache(String name, String color,
            Map<String, Integer> materials, String type) {

        super(name, color, materials, type);
    }

    /**
     *
     * Returns true if this equipment or any of its materials requires the
     * {@code material} to be crafted. <br />
     * The result is cached.
     */
    @Override
    public boolean requires(AbstractEquipment material) {
        Boolean cached = cache.get(material);
        if (cached != null) {
            return cached.booleanValue();
        }

        boolean requires = super.requires(material);
        cache.put(material, Boolean.valueOf(requires));
        return requires;
    }
}
