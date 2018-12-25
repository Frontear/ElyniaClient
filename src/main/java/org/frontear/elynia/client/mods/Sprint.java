package org.frontear.elynia.client.mods;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "Sprint", key = Keyboard.KEY_V)
public class Sprint extends ModBase {
    @SubscribeEvent public void onLivingUpdate(TickEvent.PlayerTickEvent event) {
        if (!data.state) return; if (event.phase != TickEvent.Phase.START) return; // see EntityPlayer.onUpdate
        if (event.side != Side.CLIENT) return; // only work on the player if it is a EntityPlayerSP
        if (canSprint((EntityPlayerSP) event.player)) {
            event.player.setSprinting(true);
        }
    }

    private boolean canSprint(EntityPlayerSP player) {
        // EntityPlayerSP, onLivingUpdate
        return player.onGround && !(player.movementInput.jump) && !(player.movementInput.sneak) && player.movementInput.moveForward >= (0.8F) && !player.isSprinting() && ((float)player.getFoodStats().getFoodLevel() > 6.0F || player.capabilities.allowFlying) && !player.isUsingItem() && !player.isPotionActive(Potion.blindness);
    }
}
