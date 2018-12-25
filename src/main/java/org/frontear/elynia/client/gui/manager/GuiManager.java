package org.frontear.elynia.client.gui.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.client.gui.color.Colors;
import org.frontear.elynia.client.mods.base.ModBase;

import java.util.ArrayList;

public class GuiManager {
    private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    private ScaledResolution window;
    private float scale = 2;
    private int offset = 2; // offset from the specified coordinates, so it doesn't touch the edges of the screen

    public void Draw() {
        window = new ScaledResolution(Minecraft.getMinecraft()); // this needs to be done in the event the window size changes. Calculations won't change unless it's instantiated again
        DrawHeader();
        DrawUptime();
        DrawMods();
    }

    private void DrawHeader() {
        GlStateManager.scale(scale, scale, 1);
        fontRenderer.drawStringWithShadow(ElyniaClient.CLIENT_NAME, offset, offset, Colors.Rainbow().getRGB());
        GlStateManager.scale(1 / scale, 1 / scale, 1);
    }

    private void DrawUptime() {
        String uptime = "Uptime: " + ElyniaClient.UPTIME.toString();
        fontRenderer.drawStringWithShadow(uptime,(window.getScaledWidth() - fontRenderer.getStringWidth(uptime)) - offset, (window.getScaledHeight() - fontRenderer.FONT_HEIGHT) - offset, Colors.Rainbow().getRGB());
    }

    private void DrawMods() {
        ArrayList<ModBase> enabledMods = Elynia.getElynia().modManager.GetEnabledMods();
        for (int i = enabledMods.size() - 1; i >= 0; i--) {
            ModBase mod = enabledMods.get(i);
            fontRenderer.drawStringWithShadow(mod.data.name, (offset * scale), ((fontRenderer.FONT_HEIGHT + 3) * scale) + (i * 10), Colors.Rainbow().getRGB());
        }
    }
}
