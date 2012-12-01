/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CatCommand extends DefaultEntity implements Command {

    @Parameter(index = 0)
    private String fileName;

    public CatCommand() {
        super("cat");
    }

    @Override
    public int execute(Shell shell) throws IOException {
        InputStream inputStream = new FileInputStream(fileName);
        byte[] buffer = new byte[1024];
        int read;
        do {
            read = inputStream.read(buffer);
            if(read > 0) {
                shell.out(new String(buffer, 0, read));
            }
        } while(read > 0);
        shell.outln("");
        return 0;
    }

}
