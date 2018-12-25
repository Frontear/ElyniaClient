package org.frontear.elynia.basic;

import java.lang.annotation.Annotation;

public abstract class Data<T extends Annotation> {
    protected T info;

    public Data(Object instance, Class<T> infoClass) { info = instance.getClass().getAnnotation(infoClass); }
}
