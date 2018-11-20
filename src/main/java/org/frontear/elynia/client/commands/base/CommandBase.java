package org.frontear.elynia.client.commands.base;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public abstract class CommandBase {
    public CommandInfo info = getClass().getAnnotation(CommandInfo.class);
    protected Minecraft mc = Minecraft.getMinecraft();

    public abstract boolean DoCommand(String[] args);

    protected final void ClientResponse(IChatComponent message) {
        mc.ingameGUI.getChatGUI().printChatMessage(message);
    }

    protected final IChatComponent ResponseBuilder(String message, ChatStyle style) {
        return new ChatComponentText(message).setChatStyle(style);
    }

    protected final IChatComponent ResponseBuilder(String message) {
        return new ChatComponentText(message).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY));
    }
}
