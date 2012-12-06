/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToBooleanTwoWayTransformerTest {
    
    private StringToBooleanTwoWayTransformer test = StringToBooleanTwoWayTransformer.getInstance();
    
    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasBothPrimitiveAndWrapperClasses() throws Exception {
        assertArrayEquals(new Class[] { boolean.class, Boolean.class }, test.getToClasses());
    }

    @Test
    public void canTransformToBoolean() throws Exception {
        assertEquals(true, test.transform("true"));
        assertEquals(false, test.transform("false"));
    }

    @Test
    public void canTransformFromInteger() throws Exception {
        assertEquals("true", test.transformFrom(true));
        assertEquals("false", test.transformFrom(false));
    }

}
