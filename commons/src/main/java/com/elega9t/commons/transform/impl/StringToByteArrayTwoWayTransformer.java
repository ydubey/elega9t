package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

import java.util.Iterator;

import static java.util.Arrays.asList;

public final class StringToByteArrayTwoWayTransformer implements TwoWayTransformer<String, byte[]> {

    private static StringToByteArrayTwoWayTransformer instance = new StringToByteArrayTwoWayTransformer();

    private StringToByteArrayTwoWayTransformer() {
    }

    public static StringToByteArrayTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { byte[].class };
    }

    public byte[] transform(String instance) {
        return instance == null ? null : instance.getBytes();
    }

    public String transformFrom(byte[] instance) {
        if(instance == null) {
            return null;
        }
        return new String(instance);
    }

}
