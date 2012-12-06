/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

import java.util.*;

import static java.util.Arrays.asList;

public final class StringToSetTwoWayTransformer implements TwoWayTransformer<String, Set> {

    private static StringToSetTwoWayTransformer instance = new StringToSetTwoWayTransformer();

    private StringToSetTwoWayTransformer() {
    }

    public static StringToSetTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { Set.class };
    }

    public Set transform(String instance) {
        return instance == null ? null : new LinkedHashSet(asList(instance.split(",\\s*")));
    }

    public String transformFrom(Set instance) {
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
