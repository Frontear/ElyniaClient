package org.frontear.elynia.client.mods.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.frontear.elynia.ElyniaClient;

public class ModBase {
    public ModInfo info = getClass().getAnnotation(ModInfo.class); // get the information from the @ModInfo annotation
    protected Minecraft mc = Minecraft.getMinecraft(); // minecraft instance
    protected boolean state; // state of mod
    private KeyBinding mod; // keybinding of the mod which detects keypresses and lists it as an official keybind

    public ModBase() {
        /* ClientRegistry adds our mod to the official controls menu of Minecraft, allowing for easy binding. Additionally,
        having a space before the category text allows it to sort to the absolute top of the controls menu list. */
        ClientRegistry.registerKeyBinding(mod = new KeyBinding(info.name(), info.key(), " " + ElyniaClient.CLIENT_NAME));
        MinecraftForge.EVENT_BUS.register(this); // register any and all events that will be in our mods
    }

    public final void Toggle() {
        onToggle(state = !state);
    } // call the onToggle method, and inverse state
    protected void onToggle(boolean state) {} // this should be overrided if you want custom behaviour when your mod is toggled on or off

    @SubscribeEvent
    public final void onKey(InputEvent.KeyInputEvent event) {
        // it's really annoying that event doesn't have a 'getKeyCode' method or such that can make this easier. I wouldn't even need a KeyBinding if that were true.
        if (mod.isKeyDown())
            Toggle();
    }
}
