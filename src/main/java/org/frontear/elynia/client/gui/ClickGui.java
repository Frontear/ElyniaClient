package org.frontear.elynia.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.client.mods.base.ModBase;

import java.io.IOException;
import java.util.ArrayList;

public class ClickGui extends GuiScreen {
    private ArrayList<ModBase> mods = Elynia.getElynia().modManager.GetVisibleMods();
    @Override
    public void initGui() {
        for (int i = 0; i < mods.size(); i++) {
            ModBase mod = mods.get(i);
            this.buttonList.add(new GuiButton(mod.binding.getKeyCode() * -1, this.width / 2 - 100, (this.height / 4 + 20) + (i * 24), mod.data.name));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for (ModBase mod : mods) {
            if (button.id == mod.binding.getKeyCode() * -1) {
                mod.Toggle();
            }
        }
    }
}
