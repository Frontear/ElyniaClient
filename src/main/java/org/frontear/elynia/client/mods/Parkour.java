package org.frontear.elynia.client.mods;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "Parkour", key = Keyboard.KEY_Z)
public class Parkour extends ModBase {
    @SubscribeEvent public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!data.state) return; if (event.phase != TickEvent.Phase.START) return; // see EntityPlayer.onUpdate
        if (event.side != Side.CLIENT) return; // only work on the player if it is a EntityPlayerSP

        if (event.player.onGround) {
            if (!mc.gameSettings.keyBindJump.isKeyDown()) {
                if (event.player.worldObj.getCollidingBoundingBoxes(
                        event.player, event.player.getEntityBoundingBox().addCoord(event.player.motionX,
                                event.player.motionY, event.player.motionZ)
                ).isEmpty()) {
                    event.player.jump();
                }
            }
        }
    }
}
