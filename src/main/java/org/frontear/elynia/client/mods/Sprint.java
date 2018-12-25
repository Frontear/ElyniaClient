package org.frontear.elynia.client.mods;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "Sprint", key = Keyboard.KEY_V)
public class Sprint extends ModBase {
    @SubscribeEvent public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!data.state) return; if (mc.thePlayer == null) return;
        if (canSprint()) {
            mc.thePlayer.setSprinting(true);
        }
    }

    private boolean canSprint() {
        // EntityPlayerSP, onLivingUpdate
        return mc.thePlayer.onGround && !(mc.thePlayer.movementInput.jump) && !(mc.thePlayer.movementInput.sneak) && mc.thePlayer.movementInput.moveForward >= (0.8F) && !mc.thePlayer.isSprinting() && ((float)mc.thePlayer.getFoodStats().getFoodLevel() > 6.0F || mc.thePlayer.capabilities.allowFlying) && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isPotionActive(Potion.blindness);
    }
}
