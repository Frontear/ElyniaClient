package org.frontear.elynia.client.commands.manager;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.basic.Manager;
import org.frontear.elynia.client.commands.base.CommandBase;

public class CommandManager extends Manager<CommandBase> {
    public final String commandPrefix;
    public final String responseHead = (new ChatComponentText("[").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GRAY)).getFormattedText()
    + new ChatComponentText(ElyniaClient.CLIENT_NAME).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE)).getFormattedText()
    + new ChatComponentText("]").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GRAY)).getFormattedText()); // yes it's ugly.

    public CommandManager(String prefix) {
        this.commandPrefix = prefix;

        addReflectively("org.frontear.elynia.client.commands", CommandBase.class);
    }

    public String[] CommandArgs(String message) {
        return message.split(" ");
    }
}
