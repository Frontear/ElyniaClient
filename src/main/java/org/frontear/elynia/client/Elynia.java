package org.frontear.elynia.client;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.frontear.elynia.ElyniaClient;
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

    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event) {
        event.displayname = new ChatComponentText(ElyniaClient.CLIENT_NAME + " " + "User").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD)).getFormattedText();
        // todo: allow the user to set this on startup through a prompt, if they'd like.
    }

    public void Shutdown() {
        MinecraftForge.EVENT_BUS.unregister(this);
        modManager.Close();
        commandManager.Close();
    }
}
