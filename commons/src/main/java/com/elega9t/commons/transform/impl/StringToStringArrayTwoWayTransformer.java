package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

import java.util.Iterator;

import static java.util.Arrays.asList;

public final class StringToStringArrayTwoWayTransformer implements TwoWayTransformer<String, String[]> {

    private static StringToStringArrayTwoWayTransformer instance = new StringToStringArrayTwoWayTransformer();

    private StringToStringArrayTwoWayTransformer() {
    }

    public static StringToStringArrayTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { String[].class };
    }

    public String[] transform(String instance) {
        return instance == null ? null : instance.split(",\\s*");
    }

    public String transformFrom(String[] instance) {
        if(instance == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = asList(instance).iterator();
        while(iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if(iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

}
