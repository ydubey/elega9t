/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToBigDecimalTwoWayTransformerTest {
    
    private StringToBigDecimalTwoWayTransformer test = StringToBigDecimalTwoWayTransformer.getInstance();
    
    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasBothPrimitiveAndWrapperClasses() throws Exception {
        assertArrayEquals(new Class[] { BigDecimal.class }, test.getToClasses());
    }

    @Test
    public void canTransformToBigDecimal() throws Exception {
        assertEquals(new BigDecimal("7320.4332"), test.transform("7320.4332"));
    }

    @Test
    public void canTransformFromBigDecimal() throws Exception {
        assertEquals("923.4273", test.transformFrom(new BigDecimal("923.4273")));
    }

}
