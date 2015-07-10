package com.hc.test.calculaterequired;

import org.junit.Test;

import com.hc.test.AbstractEquipmentTest;

public class TestAbstractEquipmentRequires extends
        AbstractEquipmentTest {

    @Test
    public void testDeceiversSwordRequresSanadorianWardenHelm() {
        deceiversSword.requires(sanadorianWardenHelm);
    }

}
