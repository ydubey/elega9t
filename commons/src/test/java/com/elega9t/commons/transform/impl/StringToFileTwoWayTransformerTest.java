/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringToFileTwoWayTransformerTest {
    
    private static final File TEST_FILE = new File("etc/help");

    private StringToFileTwoWayTransformer test = StringToFileTwoWayTransformer.getInstance();

    @Test
    public void fromClassIsJustString() throws Exception {
        assertArrayEquals(new Class[] { String.class }, test.getFromClasses());
    }

    @Test
    public void toClassHasBothPrimitiveAndWrapperClasses() throws Exception {
        assertArrayEquals(new Class[] { File.class }, test.getToClasses());
    }

    @Test
    public void canTransformToFile() throws Exception {
        assertEquals(TEST_FILE, test.transform(TEST_FILE.getPath()));
    }

    @Test
    public void canTransformFromFile() throws Exception {
        assertEquals(TEST_FILE.getPath(), test.transformFrom(TEST_FILE));
    }

}
