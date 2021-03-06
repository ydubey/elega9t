/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.args;

public class NamedArgument extends Argument {

    private final String name;

    public NamedArgument(String name) {
        this(name, null);
    }

    public NamedArgument(String name, String value) {
        this.name = name;
        setValue(value);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedArgument)) return false;
        if (!super.equals(o)) return false;

        NamedArgument parameter = (NamedArgument) o;

        if (name != null ? !name.equals(parameter.name) : parameter.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
