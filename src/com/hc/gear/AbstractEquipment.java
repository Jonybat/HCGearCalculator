package com.hc.gear;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AbstractEquipment {
    private String name;
    private String color;
    private Map<String, Integer> tmpmaterials = new HashMap<>();
    private SortedMap<AbstractEquipment, Integer> materials;
    private String type;

    private int xmlLineNumber;

    public AbstractEquipment(String name, String color,
            Map<String, Integer> materials, String type) {

        if (name == null || name.trim().isEmpty() || color == null
                || color.trim().isEmpty()) {

            throw new NullPointerException();
        }
        if (materials == null) {
            materials = new HashMap<>();
        }

        this.type = type == null ? null : type.trim();
        this.name = name.trim();
        this.color = color.trim();
        tmpmaterials = materials;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String color() {
        return color;
    }

    public void setup(Map<String, AbstractEquipment> equipment) {
        if (tmpmaterials == null || materials != null) {
            throw new IllegalStateException("Already setup");
        }
        materials = new TreeMap<>(new AbstractEquipmentComparator());
        _setup(equipment);
        tmpmaterials = null;
    }

    private void _setup(Map<String, AbstractEquipment> equipment) {

        for (Map.Entry<String, Integer> entry : tmpmaterials.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();

            AbstractEquipment material = equipment.get(name);
            if (material == null) {
                String msg = String
                        .format("Unknown craft material (%s) defined in line %d for equipment %s",
                                name, xmlLineNumber, this.name);
                throw new IllegalStateException(msg);
            }
            materials.put(material, quantity);
        }
    }

    public Map<AbstractEquipment, Integer> materials() {
        return Collections.unmodifiableMap(materials);
    }

    public int getXmlLineNumber() {
        return xmlLineNumber;
    }

    public void setXmlLineNumber(int xmlLineNumber) {
        this.xmlLineNumber = xmlLineNumber;
    }

    public boolean isRaw() {
        return materials.isEmpty();
    }

    @Override
    public String toString() {

        String s = String.format("Name: %s\tColor:%s\nChildren:", name,
                color);
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        for (Map.Entry<AbstractEquipment, Integer> entry : materials
                .entrySet()) {

            String name = entry.getKey().name();
            int quantity = entry.getValue();
            sb.append(" ").append(quantity).append(" ").append(name);
        }
        return sb.toString();
    }
}
