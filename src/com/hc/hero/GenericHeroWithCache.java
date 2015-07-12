package com.hc.hero;

import java.util.HashMap;
import java.util.Map;

import com.hc.gear.AbstractEquipment;

public class GenericHeroWithCache extends GenericHero {

    private Map<EquipmentFilterKey, Boolean> cache = new HashMap<>();

    public GenericHeroWithCache(String name, int stars) {
        super(name, stars);
    }

    @Override
    public boolean requires(AbstractEquipment equipment,
            String set1Name, String set2Name) {

        EquipmentFilterKey key = getKey(equipment, set1Name, set2Name);
        Boolean cached = cache.get(key);
        if (cached != null) {
            return cached.booleanValue();
        }

        boolean requires = super
                .requires(equipment, set1Name, set2Name);
        cache.put(key, Boolean.valueOf(requires));
        return requires;
    }

    private EquipmentFilterKey getKey(AbstractEquipment equipment,
            String set1Name, String set2Name) {

        EquipmentFilterKey key = new EquipmentFilterKey();
        key.equipment = equipment;
        key.set1Name = set1Name;
        key.set2Name = set2Name;
        return key;
    }

    private class EquipmentFilterKey {
        private AbstractEquipment equipment;
        private String set1Name;
        private String set2Name;

        @Override
        public int hashCode() {
            int set1NameHashcode = set1Name == null ? 0 : set1Name
                    .hashCode();
            int set2NameHashcode = set2Name == null ? 0 : set2Name
                    .hashCode();

            return equipment.hashCode() + 31 * set1NameHashcode + 2
                    * 31 * set2NameHashcode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof EquipmentFilterKey)) {
                return false;
            }
            return hashCode() == obj.hashCode();
        }
    }

}
