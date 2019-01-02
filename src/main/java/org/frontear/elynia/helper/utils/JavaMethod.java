package org.frontear.elynia.helper.utils;

import org.frontear.elynia.ElyniaClient;

import java.lang.reflect.Method;

public class JavaMethod {
    private Object source;
    private Method method;

    public JavaMethod(Object source, String name) {
        this.source = source;
        for (Method declaredMethod : source.getClass().getDeclaredMethods()) {
            if (declaredMethod.getName().equals(name)) {
                this.method = declaredMethod;
                break;
            }
        }

        if (!this.method.isAccessible()) this.method.setAccessible(true);
    }

    public void invoke(Object... params) {
        try {
            method.invoke(source, params);
        }
        catch (Exception e) {
            ElyniaClient.logger.error("Failed to invoke " + source.getClass().getSimpleName() + "'s " + method.getName());
        }
    }
}
