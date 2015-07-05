package com.hc.gear;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.hc.gear.parse.GearXMLParser;

/**
 * Instance of equipment parsed by {@link GearXMLParser}
 */
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

    /**
     * @return name of the equipment
     */
    public String name() {
        return name;
    }

    /**
     *
     * @return type of the equipment, or null if it is a regular item
     */
    public String type() {
        return type;
    }

    /**
     * @return color of the equipment
     */
    public String color() {
        return color;
    }

    /**
     * Sets the internal state of this equipment based on {@code gear}
     *
     * @param equipment
     *            all existing gear.
     */
    public void setup(Map<String, AbstractEquipment> equipment) {
        if (tmpmaterials == null || materials != null) {
            throw new IllegalStateException("Already setup");
        }
        materials = new TreeMap<>(new AbstractEquipmentNameComparator());
        _setup(equipment);
        tmpmaterials = null;
    }

    /**
     * Sets the internal state of this equipment based on {@code gear}
     *
     * @param equipment
     *            all existing gear.
     */
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

    /**
     *
     * Returns Map of needed {@code equipment} required to craft this equipment.<br />
     * The map returned uses {@link AbstractEquipment} as key, and the number of
     * that equipment needed to be crafted as value.
     *
     * @return Map of needed {@code equipment} required to craft this equipment
     */
    public Map<AbstractEquipment, Integer> materials() {
        return Collections.unmodifiableMap(materials);
    }

    /**
     * @return xml line number where this equipment was described
     */
    public int getXmlLineNumber() {
        return xmlLineNumber;
    }

    /**
     * Sets the {@code xmlLineNumber} where this equipment was described
     *
     * @param xmlLineNumber
     */
    public void setXmlLineNumber(int xmlLineNumber) {
        this.xmlLineNumber = xmlLineNumber;
    }

    /**
     *
     * @return true if this equipment cannot be crafted, meaning, it can only be
     *         found or bought as is.
     */
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
