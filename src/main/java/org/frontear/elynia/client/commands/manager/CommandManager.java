package org.frontear.elynia.client.commands.manager;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.client.commands.Clear;
import org.frontear.elynia.client.commands.base.CommandBase;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<CommandBase> commands = new ArrayList<CommandBase>();
    public final String commandPrefix;
    public final String responseHead = (new ChatComponentText("[").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GRAY)).getFormattedText()
    + new ChatComponentText(ElyniaClient.CLIENT_NAME).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE)).getFormattedText()
    + new ChatComponentText("]").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GRAY)).getFormattedText()); // yes it's ugly.

    public CommandManager(String prefix) {
        this.commandPrefix = prefix;

        commands.add(new Clear());
    }

    public CommandBase GetMod(Class<? extends CommandBase> modClass) {
        for (CommandBase command : commands) {
            if (command.getClass() == modClass)
                return command;
        }

        return null;
    }

    public ArrayList<CommandBase> GetMods() {
        return commands;
    }

    public String[] CommandArgs(String message) {
        return message.split(" ");
    }

    public void Close() {
        commands.clear();
    }
}