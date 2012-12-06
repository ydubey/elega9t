package com.elega9t.commons.transform;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReverseTransformerTest {

    @Mock
    private TwoWayTransformer mockTwoWayTransformer;

    private ReverseTransformer test;

    private static final Class[] toClass = new Class[] { Integer.class };
    private static final Class[] fromClass = new Class[] { String.class };

    @Before
    public void setUp() throws Exception {
        test = new ReverseTransformer(mockTwoWayTransformer);
        when(mockTwoWayTransformer.getToClasses()).thenReturn(toClass);
        when(mockTwoWayTransformer.getFromClasses()).thenReturn(fromClass);
    }

    @Test
    public void getFromClassReturnsToClassOfTwoWayTransformer() throws Exception {
        assertArrayEquals(toClass, test.getFromClasses());

        verify(mockTwoWayTransformer).getToClasses();
    }

    @Test
    public void getToClassReturnsFromClassOfTwoWayTransformer() throws Exception {
        assertArrayEquals(fromClass, test.getToClasses());

        verify(mockTwoWayTransformer).getFromClasses();
    }

    @Test
    public void transformInvokesAndReturnsTransformFromOfTwoWayTransformer() throws Exception {
        Object source = new Object();
        Object target = new Object();
        when(mockTwoWayTransformer.transformFrom(source)).thenReturn(target);

        assertEquals(target, test.transform(source));

        verify(mockTwoWayTransformer).transformFrom(source);
    }

}