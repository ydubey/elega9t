package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public final class StringToListTwoWayTransformer implements TwoWayTransformer<String, List> {

    private static StringToListTwoWayTransformer instance = new StringToListTwoWayTransformer();

    private StringToListTwoWayTransformer() {
    }

    public static StringToListTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { List.class };
    }

    public List transform(String instance) {
        return instance == null ? null : asList(instance.split(",\\s*"));
    }

    public String transformFrom(List instance) {
        if(instance == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = instance.iterator();
        while(iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if(iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

}
