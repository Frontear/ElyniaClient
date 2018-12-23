package org.frontear.elynia.helper;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.ArrayList;

public class Reflector {
    public <T> ArrayList<Class<? extends T>> FindClasses(String packageName, Class<T> parentClass) {
        final ArrayList<Class<? extends T>> desiredClasses = new ArrayList<Class<? extends T>>();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(Thread.currentThread().getContextClassLoader()).getTopLevelClasses(packageName)) {
                if (info.load().getSuperclass() == parentClass)
                    desiredClasses.add(info.load().asSubclass(parentClass));
            }
        }
        catch (IOException e) {}

        return desiredClasses;
    }
}
