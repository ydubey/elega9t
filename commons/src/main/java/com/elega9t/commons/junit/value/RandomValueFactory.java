/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit.value;

import com.sun.jmx.snmp.agent.SnmpStandardObjectServer;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class RandomValueFactory {

    private static final RandomValueFactory INSTANCE = new RandomValueFactory();

    private Map<Class, RandomTestValueProvider> register = new HashMap<Class, RandomTestValueProvider>();

    private RandomValueFactory() {
        register(new ByteRandomTestValueProvider());
        register(new BooleanRandomTestValueProvider());
        register(new CharacterRandomTestValueProvider());
        register(new IntegerRandomTestValueProvider());
        register(new LongRandomTestValueProvider());
        register(new FloatRandomTestValueProvider());
        register(new DoubleRandomTestValueProvider());
        register(new StringRandomTestValueProvider());
    }

    public static RandomValueFactory getInstance() {
        return INSTANCE;
    }

    public void register(RandomTestValueProvider testValueProvider) {
        for (Class<?> clazz : testValueProvider.valueProvidedFor()) {
            register(clazz, testValueProvider);
        }
    }

    public void register(Class<?> clazz, RandomTestValueProvider testValueProvider) {
        register.put(clazz, testValueProvider);
    }

    public Object create(Class<?> clazz) {
        if(clazz.isArray()) {
            Class<?> arrayType = clazz.getComponentType();
            int randomSize = RandomTestValueProvider.RANDOM.nextInt(5) + 1;
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
