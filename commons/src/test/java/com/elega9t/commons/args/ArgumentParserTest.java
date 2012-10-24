/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.args;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArgumentParserTest {

    @Test
    public void canParseOptionWithNoValue() throws Exception {
        String arg = "-option";

        ArgumentParser test = new ArgumentParser(new ByteArrayInputStream(arg.getBytes()));

        final List<Parameter> parameters = test.parse();

        assertEquals(1, parameters.size());
        assertEquals("option", parameters.get(0).getName());
        assertNull(parameters.get(0).getValue());
    }

    @Test
    public void canParseOptionWithValue() throws Exception {
        String arg = "-option xyz";

        ArgumentParser test = new ArgumentParser(new ByteArrayInputStream(arg.getBytes()));

        final List<Parameter> parameters = test.parse();

        assertEquals(1, parameters.size());
        assertEquals("option", parameters.get(0).getName());
        assertEquals("xyz", parameters.get(0).getValue());
    }

    @Test
    public void canParseMultipleOptionsWithValue() throws Exception {
        String arg = "-option xyz -anotherOp abc";

        ArgumentParser test = new ArgumentParser(new ByteArrayInputStream(arg.getBytes()));

        final List<Parameter> parameters = test.parse();

        assertEquals(2, parameters.size());
        assertEquals("option", parameters.get(0).getName());
        assertEquals("xyz", parameters.get(0).getValue());
        assertEquals("anotherOp", parameters.get(1).getName());
        assertEquals("abc", parameters.get(1).getValue());
    }

    @Test
    public void canParseMultipleMixedOptionsWithAndWithoutValue() throws Exception {
        String arg = "-option xyz -anotherOp abc -noValueOp -valueOp xxx";

        ArgumentParser test = new ArgumentParser(new ByteArrayInputStream(arg.getBytes()));

        final List<Parameter> parameters = test.parse();

        assertEquals(4, parameters.size());
        assertEquals("option", parameters.get(0).getName());
        assertEquals("xyz", parameters.get(0).getValue());
        assertEquals("anotherOp", parameters.get(1).getName());
        assertEquals("abc", parameters.get(1).getValue());
        assertEquals("noValueOp", parameters.get(2).getName());
        assertNull(parameters.get(2).getValue());
        assertEquals("valueOp", parameters.get(3).getName());
        assertEquals("xxx", parameters.get(3).getValue());
    }

}
