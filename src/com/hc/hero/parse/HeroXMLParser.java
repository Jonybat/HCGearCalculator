package com.hc.hero.parse;

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
import com.hc.hero.AbstractHero;
import com.hc.hero.GearSet;
import com.hc.hero.GenericHero;
import com.hc.hero.GenericHeroWithCache;

/**
 * Parses xml file describing existing heroes.<br />
 * After parsing, existing gear can be obtained through {@link #heroes()}
 */
public class HeroXMLParser extends DefaultHandler {

    private Map<String, AbstractEquipment> gear;
    private Map<String, AbstractHero> heroes = new HashMap<>();

    private AbstractHero hero;
    private GearSet set;

    private boolean parsingHero;
    private boolean parsingSet;
    private boolean parsingEquipment;

    private Locator locator;

    private Map<String, String> attributes = new HashMap<>();

    /**
     * Parses xml file describing existing heroes.<br />
     * After parsing, existing gear can be obtained through {@link #heroes()}
     *
     * @param filepath
     *            path to xml file describing heroes
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public HeroXMLParser(String filepath,
            Map<String, AbstractEquipment> gear)
            throws ParserConfigurationException, SAXException,
            IOException {

        this.gear = gear;

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(filepath, this);
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

        if (parsingHero && parsingSet && parsingEquipment) {
            String msg = String.format(
                    "Unexpected: fourth-degree child at line %d",
                    locator.getLineNumber());
            throw new IllegalStateException(msg);
        }

        if (parsingHero && !parsingSet) {
            parsingSet = true;
            startParseSet(uri, localName, qName, attributes);
        } else if (parsingSet && !parsingEquipment) {
            parsingEquipment = true;
            startParseEquipment(uri, localName, qName, attributes);
        } else {
            parsingHero = true;
            startParseHero(uri, localName, qName, attributes);
        }
    }

    protected void startParseHero(String uri, String localName,
            String qName, Attributes attributes) {

        try {
            String name = attributes.getValue("name");
            if (heroes.containsKey(name)) {
                throw new IllegalStateException(String.format(
                        "Hero %s already exists", name));
            }
            int stars = Integer.parseInt(attributes.getValue("stars"));
            hero = new GenericHeroWithCache(name, stars);
            heroes.put(name, hero);
        } catch (NumberFormatException e) {
            String msg = String
                    .format("Expected a valid integer in stars attribute in line %d",
                            locator.getLineNumber());
            throw new NumberFormatException(msg);
        } catch (NullPointerException e) {
            String msg = String.format(
                    "Expected name attribute at line %d",
                    locator.getLineNumber());
            throw new NullPointerException(msg);
        } catch (IllegalArgumentException e) {
            String msg = String.format("%s at line %d", e.getMessage(),
                    locator.getLineNumber());
            throw new IllegalArgumentException(msg);
        }
    }

    protected void startParseEquipment(String uri, String localName,
            String qName, Attributes attributes) {

        String name = attributes.getValue("name");
        AbstractEquipment equipment = gear.get(name);
        if (equipment == null) {
            String msg = String.format(
                    "Unknown equipment (%s) found in line %d", name,
                    locator.getLineNumber());
            throw new IllegalStateException(msg);
        }
        set.add(equipment);
    }

    protected void startParseSet(String uri, String localName,
            String qName, Attributes attributes) {

        try {
            String name = attributes.getValue("name");
            set = new GearSet(name);
            ((GenericHero) hero).add(set);
        } catch (IllegalArgumentException e) {
            String msg = String.format("%s in line %d", e.getMessage(),
                    locator.getLineNumber());
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (skip(qName)) {
            return;
        }

        if (parsingEquipment) {
            parsingEquipment = false;
            endParseEquipment(uri, localName, qName);
        } else if (parsingSet) {
            parsingSet = false;
            endParseSet(uri, localName, qName);
        } else {
            parsingHero = false;
            endParseHero(uri, localName, qName);
        }
    }

    protected void endParseSet(String uri, String localName,
            String qName) {

    }

    protected void endParseEquipment(String uri, String localName,
            String qName) {

    }

    protected void endParseHero(String uri, String localName,
            String qName) {

    }

    public Map<String, AbstractHero> heroes() {
        return heroes;
    }

    private boolean skip(String qName) {
        return !"hero".equals(qName) && !"set".equals(qName)
                && !"equipment".equals(qName);
    }
}
