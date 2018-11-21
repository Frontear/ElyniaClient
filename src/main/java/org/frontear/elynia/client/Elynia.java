package org.frontear.elynia.client;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.frontear.elynia.client.commands.manager.CommandManager;
import org.frontear.elynia.client.gui.NameSelectionMenu;
import org.frontear.elynia.client.gui.manager.GuiManager;
import org.frontear.elynia.client.mods.manager.ModManager;

public class Elynia {
    public ModManager modManager;
    private GuiManager guiManager;
    public CommandManager commandManager;

    private NameSelectionMenu nameSelectionMenu;
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
    public void onDiplayGuiScreen(GuiOpenEvent event) {
        if (nameSelectionMenu == null && event.gui instanceof GuiMainMenu) {
            nameSelectionMenu = new NameSelectionMenu(event.gui);
            event.gui = nameSelectionMenu;
        }
    }

    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event) {
        String the_newName = event.username;
        if (nameSelectionMenu != null && !nameSelectionMenu.name.isEmpty()) {
            the_newName = nameSelectionMenu.name;
        }

        event.displayname = new ChatComponentText(the_newName).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD)).getFormattedText();
        // todo: allow user to specify custom colors and all that goodness
        // todo todo: save what the user specifies in a file, so that it doesn't need to be asked every time on startup
    }

    public void Shutdown() {
        MinecraftForge.EVENT_BUS.unregister(this);
        modManager.Close();
        commandManager.Close();
    }
}
