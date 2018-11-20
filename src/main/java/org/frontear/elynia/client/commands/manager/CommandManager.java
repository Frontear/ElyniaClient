package org.frontear.elynia.client.commands.manager;

import org.frontear.elynia.client.commands.Clear;
import org.frontear.elynia.client.commands.base.CommandBase;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<CommandBase> commands = new ArrayList<CommandBase>();
    public final String commandPrefix;
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
