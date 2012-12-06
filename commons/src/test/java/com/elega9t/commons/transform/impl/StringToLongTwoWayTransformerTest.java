/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToLongTwoWayTransformerTest {
    
    private StringToLongTwoWayTransformer test = StringToLongTwoWayTransformer.getInstance();
    
    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasBothPrimitiveAndWrapperClasses() throws Exception {
        assertArrayEquals(new Class[] { long.class, Long.class }, test.getToClasses());
    }

    @Test
    public void canTransformToLong() throws Exception {
        assertEquals(295L, (long) test.transform("295"));
    }

    @Test
    public void canTransformFromInteger() throws Exception {
        assertEquals("393", test.transformFrom(393L));
    }

}
