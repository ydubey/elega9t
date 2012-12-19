/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing.syntax;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

public class SqlEditorKit extends StyledEditorKit {

    private static final long serialVersionUID = 2969192649596107757L;

    private ViewFactory sqlViewFactory;

    public SqlEditorKit() {
        sqlViewFactory = new SqlViewFactory();
    }

    @Override
    public ViewFactory getViewFactory() {
        return sqlViewFactory;
    }

    @Override
    public String getContentType() {
        return "application/sql";
    }

}