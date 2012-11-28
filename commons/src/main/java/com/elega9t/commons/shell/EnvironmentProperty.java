package com.elega9t.commons.shell;

import com.elega9t.commons.shell.intrprtr.Interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public enum EnvironmentProperty {

    INTERPRETER() {
        @Override
        protected String getValue(Shell shell) {
            Interpreter interpreter = shell.getInterpreter();
            if(interpreter == null) {
                return shell.getEnvironment().getValue(INTERPRETER.name);
            } else {
                return interpreter.getName();
            }
        }
    },

    PWD() {
        @Override
        protected String getValue(Shell shell) {
            try {
                return new File(".").getCanonicalPath();
            } catch (IOException e) {
                return new File(".").getAbsolutePath();
            }
        }
    },

    PWD_NAME() {
        @Override
        protected String getValue(Shell shell) {
            return new File(PWD.getValue(shell)).getName();
        }
    },

    EXIT_VAL("?") {
        @Override
        protected String getValue(Shell shell) {
            return shell.getExitVal() + "";
        }
    },

    DATE() {
        @Override
        protected String getValue(Shell shell) {
            return new Date().toString();
        }
    },

    TIME() {
        @Override
        protected String getValue(Shell shell) {
            return System.currentTimeMillis() + "";
        }
    },

    PROMPT(false) {
        @Override
        protected String getValue(Shell shell) {
            return "[$INTERPRETER $PWD_NAME]$";
        }
    };

    private final String name;

    private final boolean regularUpdateRequired;

    private EnvironmentProperty() {
        this.name = name();
        this.regularUpdateRequired = true;
    }

    private EnvironmentProperty(boolean regularUpdateRequired) {
        this.name = name();
        this.regularUpdateRequired = regularUpdateRequired;
    }

    private EnvironmentProperty(String name) {
        this.name = name;
        this.regularUpdateRequired = true;
    }

    private EnvironmentProperty(String name, boolean regularUpdateRequired) {
        this.name = name;
        this.regularUpdateRequired = regularUpdateRequired;
    }

    public String getName() {
        return name;
    }

    public static void init(Shell shell) {
        for (EnvironmentProperty environmentProperty : values()) {
            shell.getEnvironment().setProperty(environmentProperty.getName(), environmentProperty.getValue(shell));
        }
    }

    public static void update(Shell shell) {
        for (EnvironmentProperty environmentProperty : values()) {
            if(environmentProperty.regularUpdateRequired) {
                shell.getEnvironment().setProperty(environmentProperty.getName(), environmentProperty.getValue(shell));
            }
        }
    }

    protected abstract String getValue(Shell shell);

}
