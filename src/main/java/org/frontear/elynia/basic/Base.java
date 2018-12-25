package org.frontear.elynia.basic;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.frontear.elynia.config.base.Dynamic;
import org.frontear.elynia.config.base.IConfigurable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class Base<A extends Annotation, D extends Data<A>> implements IConfigurable {
    public D data; // stores the information from the annotation A, where it can be manipulated

    @Override
    public String get(Gson gson) { return gson.toJson(data); }

    @Override
    public void set(Gson gson, JsonReader reader) throws Exception {
        D gsonData = gson.fromJson(reader, data.getClass()); // grab the instance created by gson
        int l; // keep track of the amount of fields
        Field[] g = gsonData.getClass().getFields(), d = data.getClass().getFields();
        if (g.length == (l = d.length)) { // we are working with the same data types
            for (int i = 0; i < l; i++) {
                if (g[i].get(gsonData) == null) continue; // the value that gson created was not actually present in the json file. As a result, we do not modify it in our data.
                if (!d[i].isAnnotationPresent(Dynamic.class)) continue; /* this value should not be changed by the gson. We will NOT throw an error here,
                simply because if we do, all the user's data will be forcibly reset. We can safely ignore it and gson will automatically fix it and Runtime.addShutdownHook */
                d[i].set(data, g[i].get(gsonData)); // set the value within our data to match the value created by gson
            }
        }
        else {
            // the json file has been modified. That is beyond our control, rebuild it to prevent future errors
            throw new Exception();
        }
    }
}
