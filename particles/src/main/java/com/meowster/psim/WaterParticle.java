package com.meowster.psim;

import java.awt.*;

public class WaterParticle extends AbstractParticle {
    private static final Color WATER_COLOR = new Color(0.4f, 0.5f, 0.9f);
    private static final Color WATER_BRIGHTER = WATER_COLOR.brighter();
    private static final int CYCLE_PERIOD = 500;
    private static final ProbDistrib pdistrib =
            new ProbDistrib(2, 10, 20, 50, 60, 60, 60);

    private final ColorScale colorScale;
    private final Clock clock;

    private Color currentColor;

    WaterParticle() {
        colorScale = new ColorScale(WATER_COLOR, WATER_BRIGHTER, pdistrib.size());
        currentColor = nextColor();
        clock = new Clock(1, CYCLE_PERIOD).loop();
    }

    private Color nextColor() {
        return colorScale.get(pdistrib.nextIndex());
    }

    @Override
    public Type type() {
        return Type.WATER;
    }

    @Override
    public Color color() {
        return currentColor;
    }

    @Override
    public String name() {
        return "Water";
    }

    @Override
    public void tick() {
        if (clock.countdown()) {
            currentColor = nextColor();
        }
    }

    @Override
    public double activeness() {
        return 0.9;
    }

    @Override
    public boolean isDisplaceable() {
        return true;
    }

}
