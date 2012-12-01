package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.shell.Shell;

public interface Command {

    public String getName();

    public abstract int execute(Shell shell) throws Exception;

}
