package org.frontear.elynia.client.gui.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.client.gui.color.Colors;

public class GuiManager {
    private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;

    public void DrawHeader() {
        GlStateManager.scale(2, 2, 1);
        fontRenderer.drawStringWithShadow(ElyniaClient.CLIENT_NAME, 2, 2, Colors.Rainbow().getRGB());
        GlStateManager.scale(1 / 2f, 1 / 2f, 1);
    }
}
