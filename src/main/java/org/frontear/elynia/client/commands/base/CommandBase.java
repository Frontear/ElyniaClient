package org.frontear.elynia.client.commands.base;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.config.base.IConfigurable;

public abstract class CommandBase implements IConfigurable {
    public CommandData data = new CommandData(this, CommandInfo.class);
    protected Minecraft mc = Minecraft.getMinecraft();

    public abstract boolean DoCommand(String[] args);

    protected final void ClientResponse(IChatComponent message) {
        mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(Elynia.getElynia().commandManager.responseHead + " " + message.getFormattedText()));
    }

    protected final IChatComponent ResponseBuilder(String message, ChatStyle style) {
        return new ChatComponentText(message).setChatStyle(style);
    }

    protected final IChatComponent ResponseBuilder(String message) {
        return new ChatComponentText(message).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY));
    }

    @Override
    public String read(Gson gson) {
        return gson.toJson(data);
    }

    @Override
    public void set(Gson gson, JsonReader reader) { data = gson.fromJson(reader, CommandData.class); }

    @Override
    public boolean isConfigurable() { return true; }
}
