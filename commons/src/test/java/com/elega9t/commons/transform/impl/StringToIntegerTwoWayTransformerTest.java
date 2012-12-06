/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.impl.StringToIntegerTwoWayTransformer;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToIntegerTwoWayTransformerTest {
    
    private StringToIntegerTwoWayTransformer test = StringToIntegerTwoWayTransformer.getInstance();
    
    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasBothPrimitiveAndWrapperClasses() throws Exception {
        assertArrayEquals(new Class[] { int.class, Integer.class }, test.getToClasses());
    }

    @Test
    public void canTransformToInteger() throws Exception {
        assertEquals(823, (int) test.transform("823"));
    }

    @Test
    public void canTransformFromInteger() throws Exception {
        assertEquals("930", test.transformFrom(930));
    }

}
