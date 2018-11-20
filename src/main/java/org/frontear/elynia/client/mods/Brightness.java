package org.frontear.elynia.client.mods;

import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "Brightness", key = Keyboard.KEY_B)
public class Brightness extends ModBase {
    private float previousGamma;

    @Override
    protected void onToggle(boolean state) {
        if (state) previousGamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = state ? 100f : previousGamma;
    }
}
