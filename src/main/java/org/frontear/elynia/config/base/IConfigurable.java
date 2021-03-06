package org.frontear.elynia.config.base;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public interface IConfigurable {
    String get(Gson gson); // read the 'data'.
    void set(Gson gson, JsonReader reader) throws Exception; // set the 'data' based on what is in the config
    boolean isConfigurable(); // allows us to determine if the data should even be recognized
}
