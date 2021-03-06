package org.frontear.elynia.client.mods.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.frontear.elynia.ElyniaClient;
import org.frontear.elynia.basic.Base;

public class ModBase extends Base<ModInfo, ModData> {
    protected Minecraft mc = Minecraft.getMinecraft(); // minecraft instance
    public KeyBinding binding; // keybinding of the binding which detects keypresses and lists it as an official keybind

    public ModBase() {
        data = new ModData(this, ModInfo.class);
        /* ClientRegistry adds our binding to the official controls menu of Minecraft, allowing for easy binding. Additionally,
        having a space before the category text allows it to sort to the absolute top of the controls menu list. */
        ClientRegistry.registerKeyBinding(binding = new KeyBinding(data.name, data.key, " " + ElyniaClient.CLIENT_NAME));
        MinecraftForge.EVENT_BUS.register(this); // register any and all events that will be in our mods
    }

    public final void Toggle() { onToggle(data.state = !data.state); } // call the onToggle method, and inverse state
    public void onToggle(boolean state) {} // this should be overridden if you want custom behaviour when your binding is toggled on or off

    @Override public boolean isConfigurable() { return !data.hide; }
}
