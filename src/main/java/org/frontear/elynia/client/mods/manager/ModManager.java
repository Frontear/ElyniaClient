package org.frontear.elynia.client.mods.manager;

import net.minecraftforge.common.MinecraftForge;
import org.frontear.elynia.client.mods.*;
import org.frontear.elynia.client.mods.base.ModBase;

import java.util.ArrayList;

public class ModManager {
    private ArrayList<ModBase> mods = new ArrayList<ModBase>();
    public ModManager() {
        mods.add(new Brightness());
        mods.add(new ClickGui());
        mods.add(new Console());
        mods.add(new NoFov());
        mods.add(new Rainbow());
        mods.add(new Sprint());
    }

    public <T extends ModBase> T GetMod(Class<T> modClass) {
        for (ModBase mod : mods) {
            if (mod.getClass() == modClass) {
                return (T) mod; // this shouldn't fail
            }
        }

        return null;
    }

    public ArrayList<ModBase> GetMods() {
        return mods;
    }

    public ArrayList<ModBase> GetVisibleMods() {
        ArrayList<ModBase> visibleMods = new ArrayList<ModBase>();
        for (ModBase mod : mods) {
            if (!mod.info.hide()) {
                visibleMods.add(mod);
            }
        }

        return visibleMods;
    }

    public ArrayList<ModBase> GetEnabledMods() {
        ArrayList<ModBase> enabledMods = new ArrayList<ModBase>();
        for (ModBase mod : mods) {
            if (mod.state && !mod.info.hide())
                enabledMods.add(mod);
        }

        return enabledMods;
    }

    public void Close() {
        for (ModBase mod : mods) {
            MinecraftForge.EVENT_BUS.unregister(mod);
            mod.onToggle(false); // force all minecraft values to be reset
        }

        mods.clear();
    }
}
