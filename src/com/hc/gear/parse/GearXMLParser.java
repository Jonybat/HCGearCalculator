package com.hc.gear.parse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.hc.gear.AbstractEquipment;

public class GearXMLParser extends DefaultHandler {

    private Map<String, AbstractEquipment> gear = new HashMap<>();

    private Map<String, Integer> children;
    private String color;

    private boolean parsingEquipment;
    private boolean parsingMaterials;

    private Locator locator;
    private int linenumber;

    private Map<String, String> attributes = new HashMap<>();

    public GearXMLParser(String filepath)
            throws ParserConfigurationException, SAXException,
            IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(filepath, this);
        setupGear();
    }

    private void setupGear() {
        for (AbstractEquipment equipment : gear.values()) {
            equipment.setup(gear);
        }
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {

        if (skip(qName)) {
            return;
        }

        if (parsingEquipment && parsingMaterials) {
            String msg = String
                    .format("Unexpected: three degrees of equipment at line %d",
                            locator.getLineNumber());
            throw new IllegalStateException(msg);
        }

        if (parsingEquipment) {
            parsingMaterials = true;
            startParseMaterials(uri, localName, qName, attributes);
        } else {
            parsingEquipment = true;
            startParseEquipment(uri, localName, qName, attributes);
        }
    }

    protected void startParseEquipment(String uri, String localName,
            String qName, Attributes attributes) {

        readAttributes(attributes);
        color = this.attributes.get("color");
        children = new HashMap<>();
        linenumber = locator.getLineNumber();
    }

    protected void startParseMaterials(String uri, String localName,
            String qName, Attributes attributes) {

        String name = attributes.getValue("name");

        int previousQuantity = 0;
        if (children.keySet().contains(name)) {
            previousQuantity = children.get(name);
        }

        int newQuantity = getQuantity(attributes);
        if (newQuantity != 0) {
            children.put(name, previousQuantity + newQuantity);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (skip(qName)) {
            return;
        }

        if (parsingMaterials) {
            parsingMaterials = false;
            endParseMaterials(uri, localName, qName);
        } else {
            parsingEquipment = false;
            endParseEquipment(uri, localName, qName);
        }
    }

    protected void endParseMaterials(String uri, String localName,
            String qName) {

    }

    protected void endParseEquipment(String uri, String localName,
            String qName) {

        String name = attributes.get("name");
        String type = attributes.get("type");

        try {
            AbstractEquipment equipment = new AbstractEquipment(name,
                    color, children, type);
            gear.put(name, equipment);
            equipment.setXmlLineNumber(linenumber);
        } catch (NullPointerException e) {
            String msg = String
                    .format("Invalid equipment at line %d: equipment must have both name and color",
                            linenumber);
            throw new NullPointerException(msg);
        }
    }

    private int getQuantity(Attributes attributes) {
        try {
            String quantityAttr = attributes.getValue("quantity");
            if (quantityAttr == null) {
                return 1;
            }
            int quantity = Integer.parseInt(quantityAttr);
            if (quantity < 0) {
                throw invalidIntegerException();
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw invalidIntegerException();
        }
    }

    private IllegalArgumentException invalidIntegerException() {
        String msg = String
                .format("Expected valid integer greater or equals to zero in 'quantity' attribute",
                        locator.getLineNumber());
        return new IllegalArgumentException(msg);
    }

    public Map<String, AbstractEquipment> gear() {
        return gear;
    }

    private void readAttributes(Attributes attributes) {
        int len = attributes.getLength();
        this.attributes.clear();
        for (int i = 0; i < len; i++) {
            String key = attributes.getQName(i);
            String value = attributes.getValue(i);
            this.attributes.put(key, value);
        }
    }

    private boolean skip(String qName) {
        return !"equipment".equals(qName);
    }
}
