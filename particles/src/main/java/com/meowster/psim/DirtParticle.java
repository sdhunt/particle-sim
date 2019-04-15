package com.meowster.psim;

import java.awt.*;

public class DirtParticle extends AbstractParticle {
    private static final Color DIRT_COLOR = new Color(0.8f, 0.6f, 0.4f);

    @Override
    public Type type() {
        return Type.DIRT;
    }

    @Override
    public Color color() {
        return DIRT_COLOR;
    }

    @Override
    public String name() {
        return "Dirt";
    }

    @Override
    public double activeness() {
        return 0.5;
    }

}
