package org.frontear.elynia.client.commands;

import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.client.commands.base.CommandBase;
import org.frontear.elynia.client.commands.base.CommandInfo;

@CommandInfo(name = "Help", desc = "lists all commands")
public class Help extends CommandBase {
    @Override
    public boolean DoCommand(String[] args) {
        ClientResponse(ResponseBuilder("--- Help menu ---", new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
        for (CommandBase command : ElyniaClient.INSTANCE.commandManager.GetCommands()) {
            ClientResponse(ResponseBuilder(command.info.name() + ": " + command.info.desc(), new ChatStyle().setColor(EnumChatFormatting.WHITE)));
        }
        ClientResponse(ResponseBuilder("todo: implement tab to auto-complete", new ChatStyle().setColor(EnumChatFormatting.GREEN)));

        return true;
    }
}
