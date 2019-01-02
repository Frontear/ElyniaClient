package org.frontear.elynia.client.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mojang.authlib.GameProfile;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.frontear.elynia.client.commands.base.CommandBase;
import org.frontear.elynia.client.commands.base.CommandInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

@CommandInfo(name = "History", desc = "See username history")
public class History extends CommandBase {
    @Override public boolean DoCommand(String[] args) throws Exception {
        if (true) return true; //todo: prettify this
        if (args.length == 1) {
            String nameHistory = readfromURL(new URL("https://api.mojang.com/user/profiles/"
                    + ((Map<String, String>) new Gson().fromJson(readfromURL(new URL(
                            "https://api.mojang.com/users/profiles/minecraft/" + args[0])),
                    new TypeToken<Map<String, String>>(){}.getType())).get("id")
                    + "/names"
            ));
            if (nameHistory.isEmpty()) {
                ClientResponse(ResponseBuilder(args[0] + " has had no username changes."));
            }
            else {
                StringBuilder string = new StringBuilder();
                for (GameProfile profile : ((Collection<GameProfile>)new Gson().fromJson(nameHistory, new TypeToken<Collection<GameProfile>>(){}.getType()))) {
                    string.append("\"" + profile.getName() + "\", ");
                }
                ClientResponse(ResponseBuilder(
                        new ChatComponentText(args[0]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD)).getFormattedText() +
                        " has had the usernames: " + string.substring(0, string.length() - 2) + "."));
            }
        }
        else {
            ClientResponse(ResponseBuilder("No username specified"));
        }

        return true;
    }

    private String readfromURL(URL url) {
        StringBuilder string = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                string.append(s);
            }
            reader.close();
        }
        catch (IOException e) {}

        return string.toString();
    }
}
