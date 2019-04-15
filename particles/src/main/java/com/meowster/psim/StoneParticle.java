package com.meowster.psim;

import java.awt.*;

public class StoneParticle extends AbstractParticle {
    private static final Color STONE_COLOR = new Color(0.5f, 0.5f, 0.5f);

    @Override
    public Type type() {
        return Type.STONE;
    }

    @Override
    public Color color() {
        return STONE_COLOR;
    }

    @Override
    public String name() {
        return "Stone";
    }

    @Override
    public double activeness() {
        return 0.6;
    }

}
