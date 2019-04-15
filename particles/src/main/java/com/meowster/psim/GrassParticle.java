package com.meowster.psim;

import java.awt.*;

public class GrassParticle extends AbstractParticle {
    private static final Color GRASS_COLOR = new Color(0.5f, 1.0f, 0.5f);

    @Override
    public Type type() {
        return Type.GRASS;
    }

    @Override
    public Color color() {
        return GRASS_COLOR;
    }

    @Override
    public String name() {
        return "GRASS";
    }

    @Override
    public double activeness() {
        return 0.5;
    }

}
