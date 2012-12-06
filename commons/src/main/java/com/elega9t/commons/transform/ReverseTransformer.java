package com.elega9t.commons.transform;

import org.jetbrains.annotations.NotNull;

public final class ReverseTransformer<T, S> implements Transformer<T, S> {

    private final TwoWayTransformer<S, T> twoWayTransformer;

    public ReverseTransformer(@NotNull TwoWayTransformer<S, T> twoWayTransformer) {
        this.twoWayTransformer = twoWayTransformer;
    }

    @SuppressWarnings("unchecked")
    public Class<T>[] getFromClasses() {
        return twoWayTransformer.getToClasses();
    }

    @SuppressWarnings("unchecked")
    public Class<S>[] getToClasses() {
        return twoWayTransformer.getFromClasses();
    }

    public S transform(T instance) {
        return twoWayTransformer.transformFrom(instance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReverseTransformer)) return false;

        ReverseTransformer that = (ReverseTransformer) o;

        return twoWayTransformer.equals(that.twoWayTransformer);

    }

    @Override
    public int hashCode() {
        return twoWayTransformer.hashCode();
    }

}
