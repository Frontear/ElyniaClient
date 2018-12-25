package org.frontear.elynia.client.gui.color;

import org.frontear.elynia.client.Elynia;
import org.frontear.elynia.client.mods.Rainbow;

import java.awt.*;

public class Colors {
    private int rgb;

    private Colors(Color color) {
        this.rgb = color.getRGB();
    }

    public static Colors Rainbow() {
        return new Colors(Elynia.getElynia().modManager.getSpecific(Rainbow.class).data.state ? Color.getHSBColor((float)(System.currentTimeMillis() % 7500L) / 7500f, 0.8f, 0.8f) : new Color(255, 255, 255));
    }

    public int getRGB() {
        return rgb;
    }
}
