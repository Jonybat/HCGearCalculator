package com.hc.hero;

import java.util.Comparator;

/**
 * Compares {@link AbstractHero} based on their {@link AbstractHero#name()} only
 */
public class AbstractHeroNameComparator implements
        Comparator<AbstractHero> {

    @Override
    public int compare(AbstractHero hero1, AbstractHero hero2) {
        return hero1.name().compareTo(hero2.name());
    }

}
