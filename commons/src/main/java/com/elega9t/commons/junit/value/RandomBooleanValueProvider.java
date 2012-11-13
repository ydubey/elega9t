/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit.value;

public class RandomBooleanValueProvider extends RandomValueProvider {

    protected RandomBooleanValueProvider() {
        super(boolean.class, Boolean.class);
    }

    @Override
    public Object create() {
        return RANDOM.nextBoolean();
    }

}
