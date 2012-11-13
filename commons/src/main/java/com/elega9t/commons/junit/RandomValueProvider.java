/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class RandomValueProvider {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static interface DummyValueFactory {
        public Object create();
    }

    private static final Map<Class<?>, DummyValueFactory> hardDummys = new HashMap<Class<?>, DummyValueFactory>();

    static {
        hardDummys.put(String.class, new DummyValueFactory() {
            @Override
            public Object create() {
                return UUID.randomUUID().toString();
            }
        });
        hardDummys.put(Boolean.class, new DummyValueFactory() {
            @Override
            public Object create() {
                return RANDOM.nextBoolean();
            }
        });
        hardDummys.put(boolean.class, new DummyValueFactory() {
            @Override
            public Object create() {
                return RANDOM.nextBoolean();
            }
        });
        hardDummys.put(Integer.class, new DummyValueFactory() {
            @Override
            public Object create() {
                return RANDOM.nextInt();
            }
        });
        hardDummys.put(int.class, new DummyValueFactory() {
            @Override
            public Object create() {
                return RANDOM.nextInt();
            }
        });
    }

    public static Object get(Class<?> clazz) {
        if(hardDummys.containsKey(clazz)) {
            return hardDummys.get(clazz).create();
        } else {
            return mock(clazz);
        }
    }

}
