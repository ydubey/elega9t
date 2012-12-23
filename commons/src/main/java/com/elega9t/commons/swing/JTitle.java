/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import java.awt.*;

public class JTitle extends JPanel{

    public JTitle(Component comp) {
        setLayout(new GridBagLayout());
        add(comp,
                new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER
                        , GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(new JSeparator(),
                new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST
                        , GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
    }

}