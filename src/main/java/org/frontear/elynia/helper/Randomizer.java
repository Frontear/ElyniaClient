package org.frontear.elynia.helper;

import java.util.Random;

public class Randomizer {
    private final Random rand = new Random();
    public int nextInt(int min, int max) {
        return (rand.nextInt(max - min) + min);
    }
}
