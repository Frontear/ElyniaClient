package org.frontear.elynia.client.mods.manager;

import org.frontear.elynia.client.mods.Sprint;
import org.frontear.elynia.client.mods.base.ModBase;

import java.util.ArrayList;

public class ModManager {
    private ArrayList<ModBase> mods = new ArrayList<ModBase>();
    public ModManager() {
        mods.add(new Sprint());
    }

    public void Close() {
        mods.clear();
    }
}
