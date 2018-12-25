package org.frontear.elynia.client.commands;

import org.frontear.elynia.client.commands.base.CommandBase;
import org.frontear.elynia.client.commands.base.CommandInfo;

@CommandInfo(name = "Clear", desc = "Clears chat")
public class Clear extends CommandBase {
    @Override public boolean DoCommand(String[] args) {
        mc.ingameGUI.getChatGUI().clearChatMessages();
        ClientResponse(ResponseBuilder("Chat cleared!"));

        return true;
    }
}
