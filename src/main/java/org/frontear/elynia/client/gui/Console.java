package org.frontear.elynia.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatComponentText;
import org.apache.commons.lang3.ArrayUtils;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.client.commands.base.CommandBase;
import org.frontear.elynia.client.commands.manager.CommandManager;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class Console extends GuiScreen {
    private GuiTextField console;
    private final CommandManager commandManager = ElyniaClient.INSTANCE.commandManager;

    @Override
    public void initGui() {
        console = new GuiTextField(-1, fontRendererObj, this.width / 2 - 110, 2, 220, 14);
        console.setMaxStringLength(40);
        console.setText(commandManager.commandPrefix);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        console.drawTextBox();
        console.setFocused(true);
    }

    @Override
    public void updateScreen() {
        console.updateCursorCounter();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        boolean removeConsole = false;

        if (keyCode == Keyboard.KEY_RETURN) {
            ParseCommand(console.getText());
            removeConsole = true;
        }
        else if (keyCode == Keyboard.KEY_ESCAPE) {
            removeConsole = true;
        }

        if (removeConsole) mc.displayGuiScreen(null);

        console.textboxKeyTyped(typedChar, keyCode);
    }

    private void ParseCommand(String message) {
        boolean commandIssued = false;
        if (message.startsWith(".")) {
            for (CommandBase command : commandManager.GetMods()) {
                String the_command = message.replaceFirst(".", "");
                String[] the_arguments = commandManager.CommandArgs(the_command);

                if (command.info.name().equalsIgnoreCase(the_arguments[0])) {
                    commandIssued = command.DoCommand(ArrayUtils.remove(the_arguments, 0)); // the 0th element is just the command name, we don't need that as an argument, since it invokes the command
                }
            }

            if (!commandIssued) {
                mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Unknown command."));
            }
        }
    }
}
