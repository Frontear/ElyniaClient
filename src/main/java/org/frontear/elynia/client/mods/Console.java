package org.frontear.elynia.client.mods;

import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "Console", key = Keyboard.KEY_GRAVE, hide = true)
public class Console extends ModBase {
    @Override public void onToggle(boolean state) {
        mc.displayGuiScreen(new org.frontear.elynia.client.gui.Console());
    }
}
