package com.meowster.psim;

import java.awt.*;
import java.util.Random;

class ColorScale {
    private static final Random random = new Random();
    private Color[] scale;

    ColorScale(Color c1, Color c0, int gradations) {
        scale = new Color[gradations];
        for (int i = 0; i < gradations; i++) {
            scale[i] = computeColor(c0, c1, i);
        }
    }

    private Color computeColor(Color c0, Color c1, int index) {
        float r0 = compAsFloat(c0.getRed());
        float g0 = compAsFloat(c0.getGreen());
        float b0 = compAsFloat(c0.getBlue());

        float r1 = compAsFloat(c1.getRed());
        float g1 = compAsFloat(c1.getGreen());
        float b1 = compAsFloat(c1.getBlue());

        return new Color(
                scaledComp(r0, r1, index),
                scaledComp(g0, g1, index),
                scaledComp(b0, b1, index)
        );

    }

    private float compAsFloat(int comp) {
        return (float) comp / 255.0f;
    }

    private float scaledComp(float f0, float f1, int index) {
        float delta = (f1 - f0) / (scale.length - 1);
        return f0 + delta * index;
    }

    Color get(int index) {
        return scale[index];
    }

    Color fromTop(int n) {
        int r = random.nextInt(n);
        int i = scale.length - 1 - r;
        return scale[i];
    }
}