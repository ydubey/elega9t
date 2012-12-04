package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.shell.Shell;

import java.io.BufferedReader;
import java.io.PrintStream;

public interface Command {

    public String getName();

    public abstract int execute(Shell shell, BufferedReader in, PrintStream out) throws Exception;

}
