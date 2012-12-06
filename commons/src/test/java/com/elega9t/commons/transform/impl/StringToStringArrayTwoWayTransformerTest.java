/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToStringArrayTwoWayTransformerTest {
    
    private StringToStringArrayTwoWayTransformer test = StringToStringArrayTwoWayTransformer.getInstance();
    
    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasJustStringArray() throws Exception {
        assertArrayEquals(new Class[] { String[].class }, test.getToClasses());
    }

    @Test
    public void canTransformToStringArray() throws Exception {
        assertArrayEquals(new String[] {"ding", "dong", "squeek"} , test.transform("ding, dong, squeek"));
    }

    @Test
    public void canTransformFromStringArray() throws Exception {
        assertEquals("sing, song, ohooo", test.transformFrom(new String[] {"sing", "song", "ohooo"}));
    }

}
