package org.frontear.elynia.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class NameSelectionMenu extends GuiScreen {
    private GuiTextField nameField;
    private GuiScreen previousScreen;
    public String name;

    public NameSelectionMenu(GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void initGui() {
        nameField = new GuiTextField(-1, fontRendererObj, this.width / 2 - 100, this.height / 4, 220, 14);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.scale(2, 2, 1);
        this.drawCenteredString(fontRendererObj, "Name Change", (this.width / 2) / 2, (15) / 2, new Color(255, 255, 255).getRGB());
        GlStateManager.scale(1 / 2f, 1 / 2f, 1);
        this.drawCenteredString(fontRendererObj, "Change your client-side name. Leave blank if you don't want this", this.width / 2, this.height / 8, new Color(255, 255, 255).getRGB());
        nameField.drawTextBox();
        nameField.setFocused(true);
    }

    @Override
    public void updateScreen() {
        nameField.updateCursorCounter();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_RETURN) {
            name = nameField.getText();
            mc.displayGuiScreen(previousScreen);
        }

        nameField.textboxKeyTyped(typedChar, keyCode);
    }
}
