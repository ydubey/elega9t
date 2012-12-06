package com.elega9t.commons.transform;

public interface Transformer<S, T> {

    Class[] getFromClasses();

    Class[] getToClasses();

    T transform(S instance);

}
