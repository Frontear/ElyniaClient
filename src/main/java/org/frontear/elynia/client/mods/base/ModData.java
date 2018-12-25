package org.frontear.elynia.client.mods.base;

import com.google.gson.annotations.Expose;
import org.frontear.elynia.basic.Data;

public class ModData extends Data<ModInfo> {
    @Expose public String name;
    @Expose public int key;
    public boolean hide;
    @Expose public boolean state;

    ModData(Object instance, Class<ModInfo> infoClass) {
        super(instance, infoClass);

        name = info.name();
        key = info.key();
        hide = info.hide();
        state = false;
    }
}
