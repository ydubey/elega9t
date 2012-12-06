package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

import java.math.BigDecimal;

public final class StringToBigDecimalTwoWayTransformer implements TwoWayTransformer<String, BigDecimal> {

    private static StringToBigDecimalTwoWayTransformer instance = new StringToBigDecimalTwoWayTransformer();

    private StringToBigDecimalTwoWayTransformer() {
    }

    public static StringToBigDecimalTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { BigDecimal.class };
    }

    public BigDecimal transform(String instance) {
        return instance == null ? null : new BigDecimal(instance);
    }

    public String transformFrom(BigDecimal instance) {
        return instance == null ? null : instance.toString();
    }

}
