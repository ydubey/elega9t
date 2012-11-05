/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit.test;

import com.elega9t.commons.junit.Contract;
import com.elega9t.commons.junit.MockTarget;
import com.elega9t.commons.junit.Subject;
import com.elega9t.commons.junit.WrapperTestRunner;
import org.junit.runner.RunWith;

import java.sql.Connection;

@RunWith(WrapperTestRunner.class)
@Contract({Connection.class})
public class TestWrapperTest {

    @MockTarget
    private Connection mock;

    @Subject
    private TestWrapper test;

}
