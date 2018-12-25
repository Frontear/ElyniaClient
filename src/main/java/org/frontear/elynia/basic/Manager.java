package org.frontear.elynia.basic;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.config.base.IConfigurable;
import org.frontear.elynia.config.base.IConfigure;
import org.frontear.elynia.helper.Reflector;

import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Manager<E extends IConfigurable> implements IConfigure {
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
        try {
            for (Class<? extends E> classFound : new Reflector().FindClasses(packageName, parentClass)) {
                collection.add(classFound.newInstance());
            }
            ElyniaClient.logger.info(this.getClass().getSimpleName() + " added modules reflectively!");
        }
        catch (Exception e) {
            ElyniaClient.logger.error(this.getClass().getSimpleName() + " failed to add modules!" + " " + e.getMessage());
        }
    }

    @Override public void write(PrintWriter writer, Gson gson) {
        for (E element : collection) {
            if (!element.isConfigurable()) continue;
            writer.println(element.get(gson));
        }

        writer.close();
    }

    @Override public void read(JsonReader reader, Gson gson) throws Exception {
        for (E element : collection) {
            if (!element.isConfigurable()) continue;
            element.set(gson, reader);
        }

        reader.close();
    }
}
