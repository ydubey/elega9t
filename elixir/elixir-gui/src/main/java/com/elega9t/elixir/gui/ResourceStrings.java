/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui;

public interface ResourceStrings {
    
    java.util.ResourceBundle main = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/main");
    java.util.ResourceBundle menu = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/menu");
    java.util.ResourceBundle buttons = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/buttons");
    java.util.ResourceBundle editor = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/editor");

    interface dialog {
        
        java.util.ResourceBundle connectToDatabase = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/dialog/connectToDatabase");
        java.util.ResourceBundle settings = java.util.ResourceBundle.getBundle("com/elega9t/elixir/gui/i18n/dialog/settings");
        
    }
    
}
