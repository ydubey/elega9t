/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit.test;

import com.elega9t.commons.junit.MockTarget;
import com.elega9t.commons.junit.TestSubject;
import com.elega9t.commons.junit.WrapperTestRunner;
import com.elega9t.commons.junit.WrapperTestRunnerV2;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.sql.Connection;

@RunWith(WrapperTestRunnerV2.class)
public class TestWrapperTest {

    @MockTarget(Connection.class)
    private Connection mockConnection;

    @TestSubject
    private TestWrapper test;

    @Before
    public void setUp() throws Exception {
        test = new TestWrapper(mockConnection);
    }

}
