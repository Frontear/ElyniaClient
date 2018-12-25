package org.frontear.elynia.client.mods.manager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.frontear.elynia.basic.Manager;
import org.frontear.elynia.client.mods.base.ModBase;

import java.io.File;
import java.util.ArrayList;

public class ModManager extends Manager<ModBase> {
    public ModManager() {
        addReflectively("org.frontear.elynia.client.mods", ModBase.class);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ArrayList<ModBase> GetVisibleMods() {
        ArrayList<ModBase> visibleMods = new ArrayList<ModBase>();
        for (ModBase mod : collection) {
            if (!mod.data.hide) {
                visibleMods.add(mod);
            }
        }

        return visibleMods;
    }

    public ArrayList<ModBase> GetEnabledMods() {
        ArrayList<ModBase> enabledMods = new ArrayList<ModBase>();
        for (ModBase mod : collection) {
            if (mod.data.state && !mod.data.hide)
                enabledMods.add(mod);
        }

        return enabledMods;
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        for (ModBase mod : collection) {
            // it's really annoying that event doesn't have a 'getKeyCode' method or such that can make this easier. I wouldn't even need a KeyBinding if that were true.
            if (mod.binding.isKeyDown()) {
                mod.Toggle();
            }
        }
    }

    @Override
    public File getFile(File origin) { return new File(origin, "mods.json"); }
}
