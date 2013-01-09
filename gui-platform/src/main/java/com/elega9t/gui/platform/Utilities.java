/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform;

import java.text.DecimalFormat;

public class Utilities {

    private static final DecimalFormat FILE_SIZE_FORMAT = new DecimalFormat("#,##0.#");

    public static String readableSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return FILE_SIZE_FORMAT.format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

}
