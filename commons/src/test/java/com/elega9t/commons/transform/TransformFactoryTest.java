/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.elega9t.commons.transform;

import com.elega9t.commons.transform.impl.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.elega9t.commons.util.ReflectionUtilities.getDeclaredField;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class TransformFactoryTest {

    private static Map<Class, Map<Class, Transformer>> availableTransformers;

    @DataPoints
    public static TwoWayTransformer[] twoWayTransformers = new TwoWayTransformer[] {
            StringToBooleanTwoWayTransformer.getInstance(),
            StringToIntegerTwoWayTransformer.getInstance(),
            StringToLongTwoWayTransformer.getInstance(),
            StringToFloatTwoWayTransformer.getInstance(),
            StringToDoubleTwoWayTransformer.getInstance(),
            StringToBigDecimalTwoWayTransformer.getInstance(),
            StringToListTwoWayTransformer.getInstance(),
            StringToSetTwoWayTransformer.getInstance(),
            StringToCollectionTwoWayTransformer.getInstance(),
            StringToStringArrayTwoWayTransformer.getInstance(),
            StringToByteArrayTwoWayTransformer.getInstance(),
            StringToCharArrayTwoWayTransformer.getInstance(),
            StringToFileTwoWayTransformer.getInstance()
    };
    
    @BeforeClass
    public static void setUp() throws NoSuchFieldException, IllegalAccessException {
        availableTransformers = (Map<Class, Map<Class, Transformer>>) getDeclaredField("availableTransformers", TransformFactory.class);
    }

    @Test
    public void allPreregisteredTransformersAreValidated() throws Exception {
        Set<Class> uniqueTypes = new HashSet<Class>();
        for (TwoWayTransformer twoWayTransformer : twoWayTransformers) {
            uniqueTypes.addAll(Arrays.asList(twoWayTransformer.getFromClasses()));
            uniqueTypes.addAll(Arrays.asList(twoWayTransformer.getToClasses()));
        }
        assertEquals(uniqueTypes.size(), availableTransformers.size());
    }

    @Theory
    public void twoWayTransformerIsPreRegistered(TwoWayTransformer twoWayTransformer) throws Exception {
        transformerPresent(twoWayTransformer);
        transformerPresent(new ReverseTransformer(twoWayTransformer));
    }

    private void transformerPresent(Transformer transformer) throws NoSuchFieldException, IllegalAccessException {
        Class[] fromClasses = transformer.getFromClasses();
        for (Class fromClass : fromClasses) {
            assertTrue(availableTransformers.containsKey(fromClass));

            Map<Class, Transformer> fromTransformers = availableTransformers.get(fromClass);
            Class[] toClasses = transformer.getToClasses();
            for (Class toClass : toClasses) {
                assertTrue(fromTransformers.containsKey(toClass));
                assertEquals(transformer, fromTransformers.get(toClass));
            }
        }
    }

}
