/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.fitnesse.calc.fixture;

import com.elega9t.fitnesse.calc.Calculator;
import fit.ColumnFixture;

public class CalculatorFixture extends ColumnFixture {

    private Calculator calculator = new Calculator();

    public String operation;
    public String param;

    public String result() {
        return calculator.calc(operation, param).toString();
    }

}
