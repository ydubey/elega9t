/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToListTwoWayTransformerTest {
    
    private StringToListTwoWayTransformer test = StringToListTwoWayTransformer.getInstance();
    
    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasJustList() throws Exception {
        assertArrayEquals(new Class[] { List.class }, test.getToClasses());
    }

    @Test
    public void canTransformToList() throws Exception {
        assertArrayEquals(new String[] {"a", "c", "re"} , test.transform("a,c,re").toArray());
    }

    @Test
    public void canTransformFromList() throws Exception {
        assertEquals("are, cat, yen", test.transformFrom(Arrays.asList("are", "cat", "yen")));
    }

}
