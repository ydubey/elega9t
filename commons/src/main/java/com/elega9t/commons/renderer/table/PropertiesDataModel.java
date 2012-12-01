/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesDataModel implements DataModel<Properties> {

    private static final String[] columnNames = { "Name", "Value" };
    private final List<List<String>> properties;

    public PropertiesDataModel(Properties properties) {
        this.properties = new ArrayList<List<String>>();
        for (Object name : properties.keySet()) {
            List<String> property = new ArrayList<String>(2);
            property.add(String.valueOf(name));
            property.add(String.valueOf(properties.get(name)));
            this.properties.add(property);
        }
    }

    @Override
    public int rowCount() {
        return properties.size();
    }

    @Override
    public int columnCount() {
        return 2;
    }

    @Override
    public String value(int row, int column) {
        return properties.get(row).get(column);
    }

    @Override
    public String columnName(int column) {
        return columnNames[column];
    }

}
