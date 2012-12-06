package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

public final class StringToDoubleTwoWayTransformer implements TwoWayTransformer<String, Double> {

    private static StringToDoubleTwoWayTransformer instance = new StringToDoubleTwoWayTransformer();

    private StringToDoubleTwoWayTransformer() {
    }

    public static StringToDoubleTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { double.class, Double.class };
    }

    public Double transform(String instance) {
        return instance == null ? null : Double.parseDouble(instance);
    }

    public String transformFrom(Double instance) {
        return instance == null ? null : instance.toString();
    }

}
