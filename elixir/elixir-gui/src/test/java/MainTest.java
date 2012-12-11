/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

import com.elega9t.elixir.gui.Launcher;
import org.uispec4j.*;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

//apt-get install xorg xfce4
//-Dawt.toolkit=sun.awt.motif.MToolkit

public class MainTest extends UISpecTestCase {

    private Window test;

    @Override
    public void setUp() throws Exception {
        setAdapter(new MainClassAdapter(Launcher.class, new String[0]));
        test = getMainWindow();
    }

    public void testMainWindowTitleIsSetCorrectly() throws Exception {
        assertEquals("Elixir Database Manager, v#1.01", test.getTitle());
    }

}
