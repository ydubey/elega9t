/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing.config;

import com.elega9t.commons.entity.DefaultEntityNode;

public class ConfigElement extends DefaultEntityNode {

    private final String[] parentCategory;
    private final ConfigPanel configPanel;

    public ConfigElement(ConfigPanel configPanel, String... parentCategory) {
        super(parentCategory[parentCategory.length - 1]);
        this.configPanel = configPanel;
        this.parentCategory = parentCategory;
    }

    public String[] getParentCategory() {
        return parentCategory;
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

}
