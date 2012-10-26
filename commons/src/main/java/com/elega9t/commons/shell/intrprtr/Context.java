package com.elega9t.commons.shell.intrprtr;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Context<T> {

    private Map<String, T> properties = new HashMap<String, T>();

    public Context() {
    }

    public void setProperty(String name, T value) {
        properties.put(name, value);
    }

    public T getValue(String name) {
        return properties.get(name);
    }

    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

}
