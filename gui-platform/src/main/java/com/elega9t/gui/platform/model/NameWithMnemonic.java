/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.model;

public class NameWithMnemonic {

    private String name;
    private Character mnemonic;

    public NameWithMnemonic(String nameWithMnemonic) {
        name = nameWithMnemonic.replace("_", "");
        if(nameWithMnemonic.contains("_")) {
            mnemonic = nameWithMnemonic.charAt(nameWithMnemonic.indexOf("_") + 1);
            if(Character.isSpaceChar(mnemonic)) {
                mnemonic = null;
            }
        }
    }

    public String getName() {
        return name;
    }

    public char getMnemonic() {
        return mnemonic;
    }

}
