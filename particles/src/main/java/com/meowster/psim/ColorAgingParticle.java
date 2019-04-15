package com.meowster.psim;

import java.awt.*;

public abstract class ColorAgingParticle extends AbstractParticle {

    ColorScale colorScale;
    int nColors;
    int ageRoll;

    int age = 0;
    int colorIndex = 0;

    ColorAgingParticle(Color c0, Color c1, int nColors, int ageRoll) {
        colorScale = new ColorScale(c0, c1, nColors);
        this.nColors = nColors;
        this.ageRoll = ageRoll;
    }

    boolean ageNew() {
        return colorIndex == 0;
    }

    boolean ageMax() {
        return colorIndex == nColors - 1;
    }

    public Color color() {
        if (!ageMax()) {
            age += 1;
            if (age % ageRoll == 0) {
                colorIndex += 1;
            }
        }
        return colorScale.get(colorIndex);
    }

}
