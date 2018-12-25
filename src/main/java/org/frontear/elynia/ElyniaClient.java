package org.frontear.elynia;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.config.Configuration;
import org.frontear.elynia.helper.Logging;
import org.frontear.elynia.helper.Timer;
import org.lwjgl.opengl.Display;

import java.io.File;

@Mod(modid = "elynia") // update with mcmod.info
public class ElyniaClient {
    public static final String CLIENT_NAME = "Elynia", CLIENT_VERSION = "1.2";
    public static Timer UPTIME;
    public static final File DIR = new File(Minecraft.getMinecraft().mcDataDir, CLIENT_NAME.toLowerCase());
    public static Logging logger;
    private Configuration config;

    @EventHandler public void pre(FMLPreInitializationEvent event) {
        boolean dir_created = DIR.mkdir();
        logger = new Logging(CLIENT_NAME);

        if (dir_created)
            logger.info(CLIENT_NAME + " directory created");
    }

    @EventHandler public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Elynia());
        logger.info(CLIENT_NAME + " successfully initialized and registered");
        (config = new Configuration()).ReadConfig();
        logger.info("Configuration initialized and executed!");
    }

    @EventHandler public void post(FMLPostInitializationEvent event) {
        Display.setTitle(CLIENT_NAME + " " + CLIENT_VERSION);
        logger.info("Display title set!");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                config.SyncConfig();
            }
        }));
        logger.info("Added the shutdown hook!");
        UPTIME = new Timer();
        logger.info(CLIENT_NAME + " client successfully started");
    }
}
