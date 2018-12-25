package org.frontear.elynia.config.base;

import java.io.File;

public interface IConfigure {
    File getFile(File origin); // origin is the Elynia folder, which is defined inside of the Configuration.java
}
