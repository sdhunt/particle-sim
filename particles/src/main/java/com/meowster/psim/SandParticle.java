package com.meowster.psim;

import java.awt.*;

public class SandParticle extends AbstractParticle {
    private static final Color SAND_COLOR = new Color(0.95f, 0.95f, 0.7f);

    @Override
    public Type type() {
        return Type.SAND;
    }

    @Override
    public Color color() {
        return SAND_COLOR;
    }

    @Override
    public String name() {
        return "Sand";
    }

    @Override
    public double activeness() {
        return 0.5;
    }

}
