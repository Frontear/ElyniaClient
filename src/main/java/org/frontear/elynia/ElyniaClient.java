package org.frontear.elynia;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.helper.BackgroundTask;
import org.lwjgl.opengl.Display;

@Mod(modid = ElyniaClient.MODID)
public class ElyniaClient
{
    static final String MODID = "elynia"; // this value should be updated alongside whatever is in the mcmod.info
    public static final String CLIENT_NAME = "Elynia";
    private static final double CLIENT_VERSION = 1.0;

    private static Elynia INSTANCE;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        BackgroundTask.Run(new Runnable() {
            @Override
            public void run() {
                MinecraftForge.EVENT_BUS.register(INSTANCE = new Elynia());
            }
        });
    }

    @EventHandler
    public void post(FMLPostInitializationEvent event) {
        BackgroundTask.Run(new Runnable() {
            @Override
            public void run() {
                Display.setTitle(CLIENT_NAME + " " + CLIENT_VERSION);
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        INSTANCE.Shutdown();
                        BackgroundTask.Shutdown();
                        System.out.println("Goodbye!");
                    }
                }));
            }
        });
    }
}
