package org.frontear.elynia.client.mods.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModInfo {
    String name();
    int key();
}
