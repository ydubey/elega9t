/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.fitnesse.eg;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {

    private static final Map<String, Integer> operations = new HashMap<String, Integer>();
    static {
        operations.put("reset", 0);
        operations.put("input", 1);
        operations.put("add", 2);
        operations.put("accumulator", 3);
    }

    private Stack<BigDecimal> stack = new Stack<BigDecimal>();

    public String calc(String operation, String param) {
        Integer op = operations.get(operation);
        if(op !=null) {
            switch (op) {
                case 0:
                    return reset();
                case 1:
                    return input(new BigDecimal(param));
                case 2:
                    return add();
                case 3:
                    return accumulator();
                default:
                    throw new IllegalArgumentException("Unsupported operation " + operation);
            }
        } else {
            throw new IllegalArgumentException("Unsupported operation " + operation);
        }
    }

    private String accumulator() {
        return stack.peek().toString();
    }

    private String input(BigDecimal param) {
        stack.push(param);
        return "OK";
    }

    private String reset() {
        stack.clear();
        return "OK";
    }

    private String add() {
        BigDecimal result = stack.pop();
        result = result.add(stack.pop());
        stack.push(result);
        return "OK";
    }

}
