/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.NamedParameter;
import com.elega9t.commons.shell.intrprtr.Parameter;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class GrepCommand extends DefaultEntity implements Command {

    @Parameter(index = 0)
    private String what;

    @Parameter(index = 1, required = false)
    private String from;

    @NamedParameter(name = "i", required = false)
    boolean caseInsensitive;

    public GrepCommand() {
        super("grep");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) throws Exception {
        while(in.ready()) {
            String line = in.readLine();
            Pattern pattern;
            if(caseInsensitive) {
                pattern = Pattern.compile(Pattern.quote(what), Pattern.CASE_INSENSITIVE);
            } else {
                pattern = Pattern.compile(Pattern.quote(what));
            }
            if(pattern.matcher(line).find()) {
                out.println(line);
            }
        }
        return 0;
    }

}
