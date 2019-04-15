package com.meowster.psim;

import java.awt.Color;

public abstract class ColorAgingParticle extends ColoredParticle {

    private final Clock colorClock;

    ColorAgingParticle(Color c1, Color c0, int nColors, int ageRoll) {
        super(c1, c0, nColors, 2);
        colorClock = new Clock(nColors - 1, ageRoll);
    }

    @Override
    public void tick() {
        super.tick();
        if (!colorClock.atZero()) {
            if (colorClock.countdown()) {
                setColorByIndex(colorClock.majorValue());
            }
        }
    }

    boolean clockAtStart() {
        return colorClock.atStart();
    }

    boolean clockAtZero() {
        return colorClock.atZero();
    }
}
