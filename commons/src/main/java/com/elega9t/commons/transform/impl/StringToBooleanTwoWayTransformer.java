package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

public final class StringToBooleanTwoWayTransformer implements TwoWayTransformer<String, Boolean> {

    private static StringToBooleanTwoWayTransformer instance = new StringToBooleanTwoWayTransformer();

    private StringToBooleanTwoWayTransformer() {
    }
    
    public static StringToBooleanTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { boolean.class, Boolean.class };
    }

    public Boolean transform(String instance) {
        return instance == null ? null : Boolean.parseBoolean(instance);
    }

    public String transformFrom(Boolean instance) {
        return instance == null ? null : instance.toString();
    }

}
