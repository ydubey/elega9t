package com.elega9t.fitnesse.eg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator test;

    @Before
    public void setUp() throws Exception {
        test = new Calculator();
    }

    @Test
    public void canAdd() throws Exception {
        test.calc("reset", null);
        test.calc("input", "34");
        test.calc("input", "6");
        test.calc("add", null);
        assertEquals("40", test.calc("accumulator", null));
    }

}
