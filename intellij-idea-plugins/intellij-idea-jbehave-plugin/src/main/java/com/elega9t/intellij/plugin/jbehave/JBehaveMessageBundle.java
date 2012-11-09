/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave;

import com.intellij.CommonBundle;

import java.util.ResourceBundle;

public class JBehaveMessageBundle {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("com.elega9t.intellij.plugin.jbehave.jBehaveMessageBundle");

    public static String message(String key, String... params) {
        return CommonBundle.message(RESOURCE_BUNDLE, key, params);
    }

}
