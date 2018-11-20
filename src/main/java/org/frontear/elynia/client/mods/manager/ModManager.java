package org.frontear.elynia.client.mods.manager;

import org.frontear.elynia.client.mods.*;
import org.frontear.elynia.client.mods.base.ModBase;

import java.util.ArrayList;

public class ModManager {
    private ArrayList<ModBase> mods = new ArrayList<ModBase>();
    public ModManager() {
        mods.add(new Brightness());
        mods.add(new Console());
        mods.add(new NoFov());
        mods.add(new Rainbow());
        mods.add(new Sprint());
    }

    public ModBase GetMod(Class<? extends ModBase> modClass) {
        for (ModBase mod : mods) {
            if (mod.getClass() == modClass)
                return mod;
        }

        return null;
    }

    public ArrayList<ModBase> GetMods() {
        return mods;
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
        mods.clear();
    }
}
