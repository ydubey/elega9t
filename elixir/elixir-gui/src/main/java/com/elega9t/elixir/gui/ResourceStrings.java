/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui;

/**
 *
 * @author yogesh
 */
public interface ResourceStrings {
    
    java.util.ResourceBundle main = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/main");
    java.util.ResourceBundle menu = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/menu");
    
    interface dialog {
        
        java.util.ResourceBundle connectToDatabase = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/dialog/connectToDatabase");
        
    }
    
}
