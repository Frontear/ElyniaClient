package org.frontear.elynia.client.mods;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.frontear.elynia.client.mods.base.ModBase;
import org.frontear.elynia.client.mods.base.ModInfo;
import org.frontear.elynia.helper.Randomizer;
import org.frontear.elynia.helper.Timer;
import org.frontear.elynia.helper.utils.JavaField;
import org.frontear.elynia.helper.utils.JavaMethod;
import org.lwjgl.input.Keyboard;

@ModInfo(name = "AutoClicker", key = Keyboard.KEY_C)
public class AutoClicker extends ModBase {
    private final Randomizer randomizer = new Randomizer();
    private final Timer timer = new Timer();
    private final JavaField leftClickCounter = new JavaField(mc, "leftClickCounter");
    private final JavaMethod clickMouse = new JavaMethod(mc, "clickMouse");

    @SubscribeEvent public void onTick(LivingEvent.LivingUpdateEvent event) {
        if (!data.state) return;
        if (!(event.entity instanceof EntityPlayerSP)) return;
        if (mc.gameSettings.keyBindAttack.isKeyDown() && !((EntityPlayerSP) event.entity).isBlocking() && !event.entity.isEating()) {
            int random_cps = 1000 / (randomizer.nextInt(9, 11));
            if (timer.timeElapsed(random_cps)) {
                timer.resetTime();
                {
                    leftClickCounter.set(0);
                    clickMouse.invoke();
                }
            }
        }
        else {
            timer.resetTime();
        }
    }
}
