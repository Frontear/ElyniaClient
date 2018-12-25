package org.frontear.elynia.client.commands.base;

import com.google.gson.annotations.Expose;
import org.frontear.elynia.basic.Data;
import org.frontear.elynia.config.base.Dynamic;

public class CommandData extends Data<CommandInfo> {
    @Expose public String name;
    public String desc;
    @Expose @Dynamic public int usageCount;

    CommandData(Object instance, Class<CommandInfo> infoClass) {
        super(instance, infoClass);

        name = info.name();
        desc = info.desc();
        usageCount = 0;
    }
}
