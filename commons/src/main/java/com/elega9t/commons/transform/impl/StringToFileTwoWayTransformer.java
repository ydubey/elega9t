/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

import java.io.File;

public final class StringToFileTwoWayTransformer implements TwoWayTransformer<String, File> {

    private static StringToFileTwoWayTransformer instance = new StringToFileTwoWayTransformer();

    private StringToFileTwoWayTransformer() {
    }

    public static StringToFileTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { File.class };
    }

    public File transform(String instance) {
        return instance == null ? null : new File(instance);
    }

    public String transformFrom(File instance) {
        return instance == null ? null : instance.getPath();
    }

}
