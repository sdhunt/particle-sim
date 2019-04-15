package com.meowster.psim;

import java.awt.*;

public class AshParticle extends AbstractParticle {
    private static final Color ASH_COLOR_1 = new Color(0.8f, 0.8f, 0.85f);
    private static final Color ASH_COLOR_2 = new Color(0.75f, 0.75f, 0.8f);

    private final Color color;
    AshParticle() {
        color = choice(ASH_COLOR_1, ASH_COLOR_2);
    }
    @Override
    public Type type() {
        return Type.ASH;
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public String name() {
        return "Ash";
    }

    @Override
    public double activeness() {
        return 0.5;
    }

}
