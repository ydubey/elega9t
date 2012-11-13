/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit.value;

import java.util.Random;

public abstract class RandomTestValueProvider {

    static final Random RANDOM = new Random(System.currentTimeMillis());

    private final Class[] classesHandled;

    protected RandomTestValueProvider(Class<?>... classesHandled) {
        this.classesHandled = classesHandled;
    }

    public Class<?>[] valueProvidedFor() {
        return classesHandled;
    }

    public abstract Object create();

}
