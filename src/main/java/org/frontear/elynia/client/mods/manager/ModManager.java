package org.frontear.elynia.client.mods.manager;

import org.frontear.elynia.basic.Manager;
import org.frontear.elynia.client.mods.base.ModBase;

import java.util.ArrayList;

public class ModManager extends Manager<ModBase> {
    public ModManager() {
        addReflectively("org.frontear.elynia.client.mods", ModBase.class);
    }

    public ArrayList<ModBase> GetVisibleMods() {
        ArrayList<ModBase> visibleMods = new ArrayList<ModBase>();
        for (ModBase mod : collection) {
            if (!mod.info.hide()) {
                visibleMods.add(mod);
            }
        }

        return visibleMods;
    }

    public ArrayList<ModBase> GetEnabledMods() {
        ArrayList<ModBase> enabledMods = new ArrayList<ModBase>();
        for (ModBase mod : collection) {
            if (mod.state && !mod.info.hide())
                enabledMods.add(mod);
        }

        return enabledMods;
    }
}
