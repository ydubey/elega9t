package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

public final class StringToLongTwoWayTransformer implements TwoWayTransformer<String, Long> {

    private static StringToLongTwoWayTransformer instance = new StringToLongTwoWayTransformer();

    private StringToLongTwoWayTransformer() {
    }

    public static StringToLongTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { long.class, Long.class };
    }

    public Long transform(String instance) {
        return instance == null ? null : Long.parseLong(instance);
    }

    public String transformFrom(Long instance) {
        return instance == null ? null : instance.toString();
    }

}
