package org.frontear.elynia.config;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.client.mods.base.ModBase;

import java.io.*;
import java.util.ArrayList;

public class Configuration {
    private Logger logger = LogManager.getLogger();
    private File config = new File(Minecraft.getMinecraft().mcDataDir, ElyniaClient.CLIENT_NAME.toLowerCase() + ".txt");
    private ArrayList<ModBase> mods;
    public Configuration() throws IOException {
        mods = ElyniaClient.INSTANCE.modManager.GetMods();
        boolean createdNow = false;
        if (!config.exists()) {
            createdNow = config.createNewFile();
        }

        if (createdNow) SyncConfig();
    }

    public void ReadConfig() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(config));
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                String[] modInfo = line.split(":");
                for (ModBase mod : mods) {
                    if (modInfo[0].equals(mod.info.name())) {
                        mod.onToggle(mod.state = Boolean.parseBoolean(modInfo[1])); // this is done to update any minecraft values in the onToggle upon startup, like brightness
                    }
                }
            }
            catch (Exception e) {
                logger.error("Error while attempting to read config", e);
            }
        }

        reader.close();
        logger.info("Config read successfully!");
    }

    public void SyncConfig() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(config);
        try {
            for (ModBase mod : mods) {
                writer.println(mod.info.name() + ":" + String.valueOf(mod.state));
            }
        }
        catch (Exception e) {
            logger.error("Error while attempting to synchronize config", e);
        }
        finally {
            writer.close();
            logger.info("Config synchronized successfully!");
        }
    }
}
