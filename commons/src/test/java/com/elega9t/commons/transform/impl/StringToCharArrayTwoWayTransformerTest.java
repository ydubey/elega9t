/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.transform.impl;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToCharArrayTwoWayTransformerTest {
    
    private StringToCharArrayTwoWayTransformer test = StringToCharArrayTwoWayTransformer.getInstance();
    
    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasJustByteArray() throws Exception {
        assertArrayEquals(new Class[] { char[].class }, test.getToClasses());
    }

    @Test
    public void canTransformToByteArray() throws Exception {
        assertArrayEquals("doofus, homm, re5".toCharArray() , test.transform("doofus, homm, re5"));
    }

    @Test
    public void canTransformFromByteArray() throws Exception {
        assertEquals("alexander, porus, nepolean", test.transformFrom("alexander, porus, nepolean".toCharArray()));
    }

}
