package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

public final class StringToIntegerTwoWayTransformer implements TwoWayTransformer<String, Integer> {

    private static StringToIntegerTwoWayTransformer instance = new StringToIntegerTwoWayTransformer();

    private StringToIntegerTwoWayTransformer() {
    }

    public static StringToIntegerTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { int.class, Integer.class };
    }

    public Integer transform(String instance) {
        return instance == null ? null : Integer.parseInt(instance);
    }

    public String transformFrom(Integer instance) {
        return instance == null ? null : instance.toString();
    }

}
