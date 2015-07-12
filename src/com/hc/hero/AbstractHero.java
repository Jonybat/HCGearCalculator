package com.hc.hero;

import java.util.List;
import java.util.Optional;

import com.hc.gear.AbstractEquipment;

public interface AbstractHero {

    /**
     *
     * @return name of the hero
     */
    String name();

    /**
     *
     * @return number of stars the hero first has when acquired
     */
    int stars();

    /**
     * Returns true if this hero ever requires the {@code equipment}.<br />
     *
     * @param equipment
     * @return Returns true if this hero ever requires the {@code equipment}
     */
    boolean requires(AbstractEquipment equipment);

    /**
     * Returns true if this hero ever equips the {@code equipment}.<br />
     *
     * @param equipment
     * @return Returns true if this hero ever requires the {@code equipment}
     */
    boolean equips(AbstractEquipment equipment);

    boolean requires(AbstractEquipment equipment, GearSet gearSet1,
            GearSet gearSet2);

    boolean equips(AbstractEquipment equipment, GearSet gearSet1,
            GearSet gearSet2);

    /**
     * Returns true if this hero requires the {@code equipment} between the
     * {@code set1Name} and the {@code set2Name}.<br />
     * <br />
     * If the {@code set1Name} is null, it is considered to be the lowest
     * possible set.<br />
     * If the {@code set2Name} is null, it is considered to be the highest
     * possible set.<br />
     * If both {@code set1Name} and {@code set2Name} are null, returns true if
     * the hero ever requires the {@code equipment}. <br />
     * <br />
     *
     * @param equipment
     * @param set1Name
     * @param set2Name
     * @return true if this hero requires the {@code equipment} between the
     *         {@code set1Name} and the {@code set2Name}
     */
    boolean requires(AbstractEquipment equipment, String set1Name,
            String set2Name);

    /**
     * Returns true if this hero equips the {@code equipment} between the
     * {@code set1Name} and the {@code set2Name}.<br />
     * <br />
     * If the {@code set1Name} is null, it is considered to be the lowest
     * possible set.<br />
     * If the {@code set2Name} is null, it is considered to be the highest
     * possible set.<br />
     * If both {@code set1Name} and {@code set2Name} are null, returns true if
     * the hero ever requires the {@code equipment}. <br />
     * <br />
     *
     * @param equipment
     * @param set1Name
     * @param set2Name
     * @return true if this hero requires the {@code equipment} between the
     *         {@code set1Name} and the {@code set2Name}
     */
    boolean equips(AbstractEquipment equipment, String set1Name,
            String set2Name);

    /**
     *
     * @return sets of this hero
     */
    List<GearSet> sets();

    /**
     * Returns optional set with the {@code name} name.
     *
     * @param name
     * @return optional set with the {@code name} name
     */
    Optional<GearSet> getSet(String name);

}
