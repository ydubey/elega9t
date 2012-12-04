package com.elega9t.commons.shell;

import com.elega9t.commons.renderer.table.Border;
import com.elega9t.commons.shell.intrprtr.Interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public enum EnvironmentProperty {

    INTERPRETER("elega9t.shell.interpreter") {
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

    PWD("elega9t.pwd") {
        @Override
        protected String getValue(Shell shell) {
            try {
                return new File(".").getCanonicalPath();
            } catch (IOException e) {
                return new File(".").getAbsolutePath();
            }
        }
    },

    PWD_NAME("elega9t.pwd.name") {
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

    DATE("elega9t.date") {
        @Override
        protected String getValue(Shell shell) {
            return new Date().toString();
        }
    },

    TIME("elega9t.time") {
        @Override
        protected String getValue(Shell shell) {
            return System.currentTimeMillis() + "";
        }
    },

    PROMPT("elega9t.prompt", false) {
        @Override
        protected String getValue(Shell shell) {
            return "[$elega9t.shell.interpreter $elega9t.pwd.name]$";
        }
    },

    BORDER("elega9t.border", false) {
        @Override
        protected String getValue(Shell shell) {
            final String property = shell.getEnvironmentProperty(BORDER);
            return property == null || property.trim().length()==0 ? Border.PLAIN.name() : property;
        }
    },

    DEBUG("elega9t.shell.debug", false) {
        @Override
        protected String getValue(Shell shell) {
            final String property = shell.getEnvironmentProperty(DEBUG);
            return property == null || property.trim().length()==0 ? Boolean.FALSE.toString() : property;
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
