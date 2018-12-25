package org.frontear.elynia.basic;

import org.frontear.elynia.config.base.IConfigure;
import org.frontear.elynia.helper.Reflector;

import java.util.ArrayList;

public abstract class Manager<E> implements IConfigure {
    protected final ArrayList<E> collection = new ArrayList<E>();
    public ArrayList<E> getCollection() { return collection; }
    public <S extends E> S getSpecific(Class<S> specifiedElement) {
        for (E element : collection) {
            if (element.getClass().equals(specifiedElement))
                return (S) element;
        }

        return null;
    }

    protected final void addReflectively(String packageName, Class<E> parentClass) {
        for (Class<? extends E> classFound : new Reflector().FindClasses(packageName, parentClass)) {
            try {
                collection.add(classFound.newInstance());
            }
            catch (InstantiationException e) {}
            catch (IllegalAccessException e) {}
        }
    }
}
