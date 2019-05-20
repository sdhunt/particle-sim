package com.meowster.psim;

import java.awt.*;

public class WaterParticle extends ColoredParticle {
    private static final Color WATER_COLOR = new Color(0.4f, 0.5f, 0.9f);
    private static final Color WATER_BRIGHTER = new Color(0.5f, 0.6f, 1.0f);
    private static final int CYCLE_PERIOD = 500;
//    private static final ProbDistrib pdistrib =
//            new ProbDistrib(2, 10, 20, 50, 60, 60, 60);
    private static final int N_COLORS = 7;
    private static final int N_FIRST = 4;

    WaterParticle() {
        super(WATER_COLOR, WATER_BRIGHTER, N_COLORS, N_FIRST);
        shimmerOn(CYCLE_PERIOD);
    }

    @Override
    public Type type() {
        return Type.WATER;
    }

    @Override
    public String name() {
        return "Water";
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
