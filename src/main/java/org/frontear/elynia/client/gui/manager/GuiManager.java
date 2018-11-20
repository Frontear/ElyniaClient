package org.frontear.elynia.client.gui.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.client.gui.color.Colors;
import org.frontear.elynia.client.mods.base.ModBase;

import java.util.ArrayList;

public class GuiManager {
    private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    private float scale = 2;
    public void Draw() {
        DrawHeader();
        DrawMods();
    }

    private void DrawHeader() {
        GlStateManager.scale(scale, scale, 1);
        fontRenderer.drawStringWithShadow(ElyniaClient.CLIENT_NAME, 2, 2, Colors.Rainbow().getRGB());
        GlStateManager.scale(1 / scale, 1 / scale, 1);
    }

    private void DrawMods() {
        ArrayList<ModBase> enabledMods = ElyniaClient.INSTANCE.modManager.GetEnabledMods();
        for (int i = enabledMods.size() - 1; i >= 0; i--) {
            ModBase mod = enabledMods.get(i);
            fontRenderer.drawStringWithShadow(mod.info.name(), (2 * scale), ((fontRenderer.FONT_HEIGHT + 3) * scale) + (i * 10), Colors.Rainbow().getRGB());
        }
    }
}
