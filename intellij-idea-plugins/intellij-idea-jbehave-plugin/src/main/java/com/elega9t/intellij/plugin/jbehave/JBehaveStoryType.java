/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class JBehaveStoryType extends LanguageFileType {

    public static final JBehaveStoryType INSTANCE = new JBehaveStoryType();

    protected JBehaveStoryType() {
        super(JBehaveLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "jBehave";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "jBehave Language Plugin";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "feature";
    }

    @Override
    public Icon getIcon() {
        return JBehaveIcons.FEATURE_ICON;
    }

}
