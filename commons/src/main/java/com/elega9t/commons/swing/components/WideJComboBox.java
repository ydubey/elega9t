/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class WideJComboBox extends JComboBox{

    public WideJComboBox() {
    }

    public WideJComboBox(final Object items[]){
        super(items);
    }

    public WideJComboBox(Vector items) {
        super(items);
    }

    public WideJComboBox(ComboBoxModel aModel) {
        super(aModel);
    }

    private boolean layingOut = false;

    public void doLayout(){
        try{
            layingOut = true;
            super.doLayout();
        }finally{
            layingOut = false;
        }
    }

    public Dimension getSize(){
        Dimension dim = super.getSize();
        if(!layingOut)
            dim.width = Math.max(dim.width, getPreferredSize().width);
        return dim;
    }

}