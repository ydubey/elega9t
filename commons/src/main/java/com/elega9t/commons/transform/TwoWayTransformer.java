package com.elega9t.commons.transform;

public interface TwoWayTransformer<S, T> extends Transformer<S, T> {

    S transformFrom(T instance);

}
