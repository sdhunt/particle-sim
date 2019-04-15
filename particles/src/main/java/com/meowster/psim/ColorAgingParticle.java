package com.meowster.psim;

import java.awt.Color;

public abstract class ColorAgingParticle extends AbstractParticle {

    private final ColorScale colorScale;
    private final Clock clock;

    private Color currentColor;

    ColorAgingParticle(Color c1, Color c0, int nColors, int ageRoll) {
        colorScale = new ColorScale(c1, c0, nColors);
        clock = new Clock(nColors - 1, ageRoll);
        this.currentColor = colorScale.fromTop(2);
    }

    @Override
    public void tick() {
        if (!clock.atZero()) {
            if (clock.countdown()) {
                currentColor = colorScale.get(clock.majorValue());
            }
        }
    }

    @Override
    public Color color() {
        return currentColor;
    }

}
