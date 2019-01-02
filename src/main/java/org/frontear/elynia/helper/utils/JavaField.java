package org.frontear.elynia.helper.utils;

import com.sun.corba.se.impl.io.TypeMismatchException;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Field;

public class JavaField {
    private Object source;
    private Field field;

    public JavaField(Object source, String name) {
        this.source = source;
        try {
            this.field = this.source.getClass().getDeclaredField(name);
        }
        catch (NoSuchFieldException e) {}

        if (!this.field.isAccessible()) this.field.setAccessible(true);
    }

    public void set(Object value) {
        if (value.getClass() != ClassUtils.primitiveToWrapper(field.getType())) throw new TypeMismatchException("Value type does not correspond to the field type");
        try {
            field.set(source, value);
        }
        catch (IllegalAccessException e) {}
    }
}
