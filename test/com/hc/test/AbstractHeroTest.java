package com.hc.test;

import static com.hc.gear.HeroConstants.ADMIRAL;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hc.hero.AbstractHero;
import com.hc.hero.parse.HeroXMLParser;

/**
 * Common base for all unit tests using heroes, providing easy to use equipment
 * handlers
 *
 */
public class AbstractHeroTest extends AbstractEquipmentTest {

    private Map<String, AbstractHero> heroes;

    protected final AbstractHero admiral;

    public AbstractHeroTest() {
        super();

        heroes = getParser().heroes();
        admiral = heroes.get(ADMIRAL);
    }

    private HeroXMLParser getParser() {
        try {
            return new HeroXMLParser("Heroes.xml", getGear());
        } catch (ParserConfigurationException | SAXException
                | IOException e) {
            // assume the file is there and has correct syntax
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @return unmodifiable copy of the parsed heroes
     */
    protected Map<String, AbstractHero> getHeroes() {
        return Collections.unmodifiableMap(heroes);
    }
}
