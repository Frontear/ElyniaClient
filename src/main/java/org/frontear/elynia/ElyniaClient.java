package org.frontear.elynia;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.config.Configuration;
import org.frontear.elynia.helper.Timer;
import org.lwjgl.opengl.Display;

import java.io.FileNotFoundException;
import java.io.IOException;

@Mod(modid = "elynia") // update with mcmod.info
public class ElyniaClient {
    public static final String CLIENT_NAME = "Elynia";
    private static final double CLIENT_VERSION = 1.0;

    public static Timer UPTIME;
    private Configuration config;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Elynia());
        try {
            (config = new Configuration()).ReadConfig();
        }
        catch (IOException e) {}
    }

    @EventHandler
    public void post(FMLPostInitializationEvent event) {
        Display.setTitle(CLIENT_NAME + " " + CLIENT_VERSION);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    config.SyncConfig();
                }
                catch (FileNotFoundException e) {}
            }
        }));
        UPTIME = new Timer();
    }
}
