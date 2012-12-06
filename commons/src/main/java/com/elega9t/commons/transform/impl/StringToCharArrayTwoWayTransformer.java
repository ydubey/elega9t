/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

public final class StringToCharArrayTwoWayTransformer implements TwoWayTransformer<String, char[]> {

    private static StringToCharArrayTwoWayTransformer instance = new StringToCharArrayTwoWayTransformer();

    private StringToCharArrayTwoWayTransformer() {
    }

    public static StringToCharArrayTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { char[].class };
    }

    public char[] transform(String instance) {
        return instance == null ? null : instance.toCharArray();
    }

    public String transformFrom(char[] instance) {
        if(instance == null) {
            return null;
        }
        return new String(instance);
    }

}
