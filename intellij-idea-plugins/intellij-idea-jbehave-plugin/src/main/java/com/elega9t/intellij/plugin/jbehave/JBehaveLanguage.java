/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave;

import com.intellij.lang.Language;

public class JBehaveLanguage extends Language {

    public static final JBehaveLanguage INSTANCE = new JBehaveLanguage();

    protected JBehaveLanguage() {
        super("jBehaveStory");
    }

    @Override
    public String getDisplayName() {
        return "jBehave Story";
    }

}
