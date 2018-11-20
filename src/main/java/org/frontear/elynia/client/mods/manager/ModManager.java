package org.frontear.elynia.client.mods.manager;

import org.frontear.elynia.client.mods.Console;
import org.frontear.elynia.client.mods.Rainbow;
import org.frontear.elynia.client.mods.Sprint;
import org.frontear.elynia.client.mods.base.ModBase;

import java.util.ArrayList;

public class ModManager {
    private ArrayList<ModBase> mods = new ArrayList<ModBase>();
    public ModManager() {
        mods.add(new Console());
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
