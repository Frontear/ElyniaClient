package org.frontear.elynia.basic;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.frontear.elynia.config.base.IConfigurable;

import java.lang.annotation.Annotation;

public abstract class Base<A extends Annotation, D extends Data<A>> implements IConfigurable {
    public D data; // stores the information from the annotation A, where it can be manipulated

    @Override
    public String get(Gson gson) { return gson.toJson(data); }

    @Override
    public void set(Gson gson, JsonReader reader) {
        data = gson.fromJson(reader, data.getClass());
    }
}
