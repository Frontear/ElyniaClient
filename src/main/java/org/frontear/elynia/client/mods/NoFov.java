package org.frontear.elynia.client.mods;

import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "NoFov", key = Keyboard.KEY_N)
public class NoFov extends ModBase {
    @SubscribeEvent public void onFovUpdate(FOVUpdateEvent event) {
        if (!data.state) return;
        if (event.entity.isPotionActive(Potion.moveSpeed) || event.entity.isPotionActive(Potion.moveSlowdown)) {
            if (event.entity.isSprinting()) event.newfov = 1.15f;
            else event.newfov = 1f;
        }
    }
}
