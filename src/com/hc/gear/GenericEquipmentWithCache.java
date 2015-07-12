package com.hc.gear;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GenericEquipmentWithCache extends GenericEquipment {

    private Map<AbstractEquipment, Boolean> cache = new HashMap<>();
    private Set<AbstractEquipment> requiredBy;

    public GenericEquipmentWithCache(String name, String color,
            Map<String, Integer> materials, String type) {

        super(name, color, materials, type);
    }

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

    @Override
    public Set<AbstractEquipment> requiredBy() {
        if (requiredBy == null) {
            requiredBy = super.requiredBy();
        }
        return requiredBy;
    }
}
