package org.frontear.elynia.client;

import org.frontear.elynia.client.mods.manager.ModManager;

public class Elynia {
    private ModManager modManager;
    public Elynia() {
        modManager = new ModManager(); // instantiate modManager
    }

    public void Shutdown() {
        modManager.Close(); // is this necessary?
    }
}
