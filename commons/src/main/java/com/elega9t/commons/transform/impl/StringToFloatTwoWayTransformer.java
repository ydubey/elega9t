package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

public final class StringToFloatTwoWayTransformer implements TwoWayTransformer<String, Float> {

    private static StringToFloatTwoWayTransformer instance = new StringToFloatTwoWayTransformer();

    private StringToFloatTwoWayTransformer() {
    }

    public static StringToFloatTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { float.class, Float.class };
    }

    public Float transform(String instance) {
        return instance == null ? null : Float.parseFloat(instance);
    }

    public String transformFrom(Float instance) {
        return instance == null ? null : instance.toString();
    }

}
