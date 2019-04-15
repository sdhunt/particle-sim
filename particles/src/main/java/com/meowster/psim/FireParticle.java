package com.meowster.psim;

import java.awt.*;

public class FireParticle extends ColorAgingParticle {
    private static final Color FIRE_COLOR = new Color(0.9f, 0.5f, 0.3f);
    private static final Color FIRE_EMBER_COLOR = new Color(0.6f, 0.3f, 0.3f);

    FireParticle() {
        super(FIRE_COLOR, FIRE_EMBER_COLOR, 5, 16);
    }

    @Override
    public Type type() {
        return Type.FIRE;
    }

    @Override
    public String name() {
        return "Fire";
    }

    @Override
    public double activeness() {
        return 1.0;
    }

    @Override
    public boolean hasExpired() {
        return clockAtZero();
    }
}
