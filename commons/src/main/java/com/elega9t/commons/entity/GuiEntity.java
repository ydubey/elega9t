/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import javax.swing.*;

public interface GuiEntity extends Entity {

    void setIcon(Icon icon);

    Icon getIcon();

    void setTooltip(String tooltip);

    String getTooltip();

}
