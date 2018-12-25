package org.frontear.elynia.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.basic.Manager;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.config.base.IConfigurable;

import java.io.*;
import java.util.ArrayList;

public class Configuration {
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    private Logger logger = LogManager.getLogger();
    private File origin = new File(Minecraft.getMinecraft().mcDataDir, ElyniaClient.CLIENT_NAME.toLowerCase());
    private final ArrayList<Manager<? extends IConfigurable>> collection = new ArrayList<Manager<? extends IConfigurable>>();

    public Configuration() {
        collection.add(Elynia.getElynia().modManager);
        collection.add(Elynia.getElynia().commandManager);

        origin.mkdir(); // if the directory does not exist, create it
    }

    public void ReadConfig() {
        try {
            for (Manager<? extends IConfigurable> manager : collection) {
                manager.read(new JsonReader(new FileReader(manager.getFile(origin))), gson);
            }

            logger.info("Successfully read and applied the configuration.");
        }
        catch (IOException e) {
            logger.error("Unable to read config. Attempting to recreate...");
            SyncConfig();
        }

    }

    public void SyncConfig() {
        try {
            for (Manager<? extends IConfigurable> manager : collection) {
                manager.write(new PrintWriter(manager.getFile(origin)), gson);
            }

            logger.info("Successfully synchronized the config.");
        }
        catch (FileNotFoundException e) {
            logger.error("Unable to sync config.");
        }
    }
}
