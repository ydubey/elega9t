/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.junit.value;

public class RandomFloatValueProvider extends RandomValueProvider {

    protected RandomFloatValueProvider() {
        super(float.class, Float.class);
    }

    @Override
    public Object create() {
        return RANDOM.nextFloat();
    }

}
