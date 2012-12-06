package com.elega9t.commons.transform;

import com.elega9t.commons.transform.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class TransformFactory {

    private static Map<Class, Map<Class, Transformer>> availableTransformers = new HashMap<Class, Map<Class, Transformer>>();

    static {
        registerTransformer(StringToBooleanTwoWayTransformer.getInstance());
        registerTransformer(StringToIntegerTwoWayTransformer.getInstance());
        registerTransformer(StringToLongTwoWayTransformer.getInstance());
        registerTransformer(StringToFloatTwoWayTransformer.getInstance());
        registerTransformer(StringToDoubleTwoWayTransformer.getInstance());
        registerTransformer(StringToBigDecimalTwoWayTransformer.getInstance());
        registerTransformer(StringToListTwoWayTransformer.getInstance());
        registerTransformer(StringToStringArrayTwoWayTransformer.getInstance());
        registerTransformer(StringToByteArrayTwoWayTransformer.getInstance());
        registerTransformer(StringToFileTwoWayTransformer.getInstance());
    }

    private TransformFactory() {
    }

    public static synchronized void registerTransformer(Transformer transformer) {
        Class[] fromClasses = transformer.getFromClasses();
        for (Class fromClass : fromClasses) {
            Map<Class, Transformer> fromClassTransformers = availableTransformers.get(fromClass);
            if(fromClassTransformers == null) {
                fromClassTransformers = new HashMap<Class, Transformer>();
                availableTransformers.put(fromClass, fromClassTransformers);
            }
            Class[] toClasses = transformer.getToClasses();
            for (Class toClass : toClasses) {
                fromClassTransformers.put(toClass, transformer);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void registerTransformer(TwoWayTransformer transformer) {
        registerTransformer((Transformer) transformer);
        registerTransformer(new ReverseTransformer(transformer));
    }

    @SuppressWarnings("unchecked")
    public static <S, T> T transform(Class<S> fromClass, Class<T> toClass, S instance) {
        if(fromClass == toClass) {
            return (T) instance;
        }

        Map<Class, Transformer> fromClassTransformers = availableTransformers.get(fromClass);
        if(fromClassTransformers == null) {
            throw new IllegalStateException("Transformation from class [" + fromClass.getName() + "] not possible.");
        }

        Transformer transformer = fromClassTransformers.get(toClass);
        if(transformer == null) {
            throw new IllegalStateException("Transformation from class [" + fromClass.getName() + "] to [" + toClass + "] not possible.");
        }

        return (T) transformer.transform(instance);
    }

    @SuppressWarnings("unchecked")
    public static <T> T transformString(Class<T> toClass, String instance) {
        return transform(String.class, toClass, instance);
    }

}
