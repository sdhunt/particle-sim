package com.meowster.psim;

import java.awt.Color;

public abstract class ColorAgingParticle extends AbstractParticle {

    private ColorScale colorScale;

    private Color currentColor;
    private Clock clock;

    ColorAgingParticle(Color c1, Color c0, int nColors, int ageRoll) {
        this.currentColor = c1;
        colorScale = new ColorScale(c1, c0, nColors);
        clock = new Clock(nColors - 1, ageRoll);
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
