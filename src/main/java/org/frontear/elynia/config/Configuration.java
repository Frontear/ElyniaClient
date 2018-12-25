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

public class Configuration extends Manager<Manager<? extends IConfigurable>> {
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    private Logger logger = LogManager.getLogger();
    private File origin = new File(Minecraft.getMinecraft().mcDataDir, ElyniaClient.CLIENT_NAME.toLowerCase());
    public Configuration() {
        collection.add(Elynia.getElynia().modManager);
        collection.add(Elynia.getElynia().commandManager);

        origin.mkdir(); // if the directory does not exist, create it
    }

    public void ReadConfig() {
        try {
            for (Manager<? extends IConfigurable> manager : collection) {
                JsonReader reader = new JsonReader(new FileReader(manager.getFile(origin)));
                for (IConfigurable configurable : manager.getCollection()) {
                    if (!configurable.isConfigurable()) continue;
                    configurable.set(gson, reader);
                }
                reader.close();
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
                PrintWriter writer = new PrintWriter(manager.getFile(origin));
                for (IConfigurable configurable : manager.getCollection()) {
                    if (!configurable.isConfigurable()) continue;
                    writer.println(configurable.read(gson));
                }
                writer.close();
            }

            logger.info("Successfully synchronized the config.");
        }
        catch (FileNotFoundException e) {
            logger.error("Unable to sync config.");
        }
    }

    @Override
    public File getFile(File origin) { return null; }
}
