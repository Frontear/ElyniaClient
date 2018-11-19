package org.frontear.elynia;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ElyniaClient.MODID)
public class ElyniaClient
{
    static final String MODID = "elynia";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Mod State: " + event.getModState());
    }
}
