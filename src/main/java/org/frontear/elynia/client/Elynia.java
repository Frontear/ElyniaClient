package org.frontear.elynia.client;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.frontear.elynia.client.commands.manager.CommandManager;
import org.frontear.elynia.client.gui.manager.GuiManager;
import org.frontear.elynia.client.mods.manager.ModManager;

public class Elynia {
    public ModManager modManager;
    private GuiManager guiManager;
    public CommandManager commandManager;
    public Elynia() {
        modManager = new ModManager();
        guiManager = new GuiManager();
        commandManager = new CommandManager(".");
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            guiManager.Draw();
        }
    }

    public void Shutdown() {
        modManager.Close();
        commandManager.Close();
    }
}
