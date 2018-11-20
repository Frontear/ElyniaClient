package org.frontear.elynia.client.mods;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "Sprint", key = Keyboard.KEY_V)
public class Sprint extends ModBase {
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!state) return;
        if (mc.thePlayer.moveForward > 0 && !mc.thePlayer.isSprinting() && mc.thePlayer.getFoodStats().getFoodLevel() > 6) {
            mc.thePlayer.setSprinting(true);
        }
    }
}
