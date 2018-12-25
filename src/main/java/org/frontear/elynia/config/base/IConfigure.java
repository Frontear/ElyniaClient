package org.frontear.elynia.config.base;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public interface IConfigure {
    File getFile(File origin); // origin is the Elynia folder, which is defined inside of the Configuration.java
    void write(PrintWriter writer, Gson gson);
    void read(JsonReader reader, Gson gson) throws IOException;
}
