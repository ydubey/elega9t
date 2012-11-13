/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit.value;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class RandomValueFactory {

    private static final RandomValueFactory INSTANCE = new RandomValueFactory();

    private Map<Class, RandomValueProvider> register = new HashMap<Class, RandomValueProvider>();

    private RandomValueFactory() {
        register(new RandomByteValueProvider());
        register(new RandomBooleanValueProvider());
        register(new RandomCharacterValueProvider());
        register(new RandomIntegerValueProvider());
        register(new RandomLongValueProvider());
        register(new RandomFloatValueProvider());
        register(new RandomDoubleValueProvider());
        register(new RandomStringValueProvider());
    }

    public static RandomValueFactory getInstance() {
        return INSTANCE;
    }

    public void register(RandomValueProvider testValueProvider) {
        for (Class<?> clazz : testValueProvider.valueProvidedFor()) {
            register(clazz, testValueProvider);
        }
    }

    public void register(Class<?> clazz, RandomValueProvider valueProvider) {
        register.put(clazz, valueProvider);
    }

    public Object create(Class<?> clazz) {
        if(clazz.isArray()) {
            Class<?> arrayType = clazz.getComponentType();
            int randomSize = RandomValueProvider.RANDOM.nextInt(5) + 1;
            Object value = Array.newInstance(arrayType, randomSize);
            for(int index=0; index<randomSize; index++) {
                Array.set(value, index, create(arrayType));
            }
            return value;
        } else {
            if(register.containsKey(clazz)) {
                return register.get(clazz).create();
            } else {
                return mock(clazz);
            }
        }
    }

    public Object[] create(Class<?>[] classes) {
        Object[] values = new Object[classes.length];
        for (int index = 0; index < classes.length; index++) {
            values[index] = create(classes[index]);
        }
        return values;
    }

}
