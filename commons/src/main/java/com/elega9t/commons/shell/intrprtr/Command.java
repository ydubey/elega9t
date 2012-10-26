package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.util.ReflectionUtilities;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
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

    public Map<Argument, Field> getArguments() {
        Map<Argument, Field> arguments = new HashMap<Argument, Field>();
        Map<Parameter, Field> parameterFields = ReflectionUtilities.getDeclaredFieldsWithAnnotation(Parameter.class, getClass());
        for (final Parameter parameter : parameterFields.keySet()) {
            Field field = parameterFields.get(parameter);
            Argument argument = null;
            if (field.getType() == String.class) {
                argument = new StringNamedArgument(parameter.name(), parameter.required());
            } else if (field.getType() == Boolean.class) {
                argument = new BooleanNamedArgument(parameter.name(), parameter.required());
            } else {
                throw new IllegalStateException("Argument of type " + field.getType().getName() + " not supported.");
            }
            arguments.put(argument, field);
        }
        Map<UnnamedParameter, Field> unnamedParameterFields = ReflectionUtilities.getDeclaredFieldsWithAnnotation(UnnamedParameter.class, getClass());
        for (final UnnamedParameter parameter : unnamedParameterFields.keySet()) {
            Field field = unnamedParameterFields.get(parameter);
            Argument argument = null;
            if (field.getType() == String.class) {
                argument = new StringArgument();
            } else {
                throw new IllegalStateException("Argument of type " + field.getType().getName() + " not supported.");
            }
            arguments.put(argument, field);
        }
        return arguments;
    }

}
