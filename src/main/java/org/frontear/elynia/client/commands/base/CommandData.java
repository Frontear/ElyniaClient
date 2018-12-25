package org.frontear.elynia.client.commands.base;

import com.google.gson.annotations.Expose;
import org.frontear.elynia.basic.Data;

public class CommandData extends Data<CommandInfo> {
    @Expose public String name;
    @Expose public String desc;
    @Expose public int usageCount;

    CommandData(Object instance, Class<CommandInfo> infoClass) {
        super(instance, infoClass);

        name = info.name();
        desc = info.desc();
        usageCount = 0;
    }
}
