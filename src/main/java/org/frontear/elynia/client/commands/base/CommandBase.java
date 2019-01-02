package org.frontear.elynia.client.commands.base;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.frontear.elynia.basic.Base;
import org.frontear.elynia.client.Elynia;

public abstract class CommandBase extends Base<CommandInfo, CommandData> {
    protected Minecraft mc = Minecraft.getMinecraft();

    public CommandBase() { data = new CommandData(this, CommandInfo.class); }
    public abstract boolean DoCommand(String[] args) throws Exception;
    protected final void ClientResponse(IChatComponent message) {
        mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(Elynia.getElynia().commandManager.responseHead + " " + message.getFormattedText()));
    }
    protected final IChatComponent ResponseBuilder(String message, ChatStyle style) { return new ChatComponentText(message).setChatStyle(style); }
    protected final IChatComponent ResponseBuilder(String message) { return new ChatComponentText(message).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY)); }

    @Override public boolean isConfigurable() { return true; }
}
