package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.util.ReflectionUtilities;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int execute(Shell shell);

    public Map<String, Field> getArguments() {
        Map<String, Field> arguments = new HashMap<String, Field>();
        Map<NamedParameter, Field> parameterFields = ReflectionUtilities.getDeclaredFieldsWithAnnotation(NamedParameter.class, getClass());
        for (final NamedParameter namedParameter : parameterFields.keySet()) {
            Field field = parameterFields.get(namedParameter);
            arguments.put(namedParameter.name(), field);
        }
        Map<Parameter, Field> unnamedParameterFields = ReflectionUtilities.getDeclaredFieldsWithAnnotation(Parameter.class, getClass());
        for (final Parameter parameter : unnamedParameterFields.keySet()) {
            Field field = unnamedParameterFields.get(parameter);
            arguments.put(parameter.index() + "", field);
        }
        return arguments;
    }

}
