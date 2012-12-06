/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.transform.impl;

import com.elega9t.commons.transform.TwoWayTransformer;

import java.util.Collection;
import java.util.Iterator;

import static java.util.Arrays.asList;

public final class StringToCollectionTwoWayTransformer implements TwoWayTransformer<String, Collection> {

    private static StringToCollectionTwoWayTransformer instance = new StringToCollectionTwoWayTransformer();

    private StringToCollectionTwoWayTransformer() {
    }

    public static StringToCollectionTwoWayTransformer getInstance() {
        return instance;
    }

    public Class[] getFromClasses() {
        return new Class[] { String.class };
    }

    public Class[] getToClasses() {
        return new Class[] { Collection.class };
    }

    public Collection transform(String instance) {
        return instance == null ? null : asList(instance.split(",\\s*"));
    }

    public String transformFrom(Collection instance) {
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
