package org.frontear.elynia.client.commands;

import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.client.commands.base.CommandBase;
import org.frontear.elynia.client.commands.base.CommandInfo;

@CommandInfo(name = "Help", desc = "lists all commands")
public class Help extends CommandBase {
    @Override
    public boolean DoCommand(String[] args) {
        ClientResponse(ResponseBuilder("--- Help menu ---", new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
        for (CommandBase command : Elynia.getElynia().commandManager.GetCommands()) {
            ClientResponse(ResponseBuilder(command.info.name() + ": " + command.info.desc(), new ChatStyle().setColor(EnumChatFormatting.WHITE)));
        }
        ClientResponse(ResponseBuilder("Tip: Use the <tab> key to auto-complete the command", new ChatStyle().setColor(EnumChatFormatting.GREEN)));

        return true;
    }
}
