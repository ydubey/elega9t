/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing.syntax;

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class SqlViewFactory implements ViewFactory {

    /**
     * @see javax.swing.text.ViewFactory#create(javax.swing.text.Element)
     */
    public View create(Element element) {
        return new SqlView(element);
    }

}