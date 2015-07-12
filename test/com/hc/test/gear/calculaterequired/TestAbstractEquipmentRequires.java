package com.hc.test.gear.calculaterequired;

import org.junit.Test;

import com.hc.test.gear.AbstractEquipmentTest;

public class TestAbstractEquipmentRequires extends
        AbstractEquipmentTest {

    @Test
    public void testDeceiversSwordRequresSanadorianWardenHelm() {
        deceiversSword.requires(sanadorianWardenHelm);
    }

}
