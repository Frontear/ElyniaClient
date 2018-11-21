package org.frontear.elynia.helper;

import net.minecraft.client.Minecraft;

public class BackgroundTask {
    public static void Run(Runnable task) { Minecraft.getMinecraft().addScheduledTask(task); }
}
