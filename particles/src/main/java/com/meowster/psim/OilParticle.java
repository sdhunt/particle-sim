package com.meowster.psim;

import java.awt.*;

public class OilParticle extends BurnableParticle {
    private static final Color OIL_COLOR = new Color(0.1f, 0.1f, 0.1f);
    private static final Color OIL_COLOR_2 = new Color(0.15f, 0.15f, 0.18f);

    private static final Color OIL_BURN = new Color(0.9f, 0.1f, 0.8f);
    private static final Color OIL_GLOW = new Color(0.8f, 0.0f, 0.7f);

    private static final int CYCLE_PERIOD = 500;
    private static final int BURN_DURATION = 1200;

//    private static final ProbDistrib pdistrib =
//            new ProbDistrib(2, 10, 20, 50, 60, 60, 60);

    private static final int N_COLORS = 7;
    private static final int N_FIRST = 4;

    OilParticle() {
        super(OIL_COLOR, OIL_COLOR_2, N_COLORS, BURN_DURATION);
        shimmerOn(CYCLE_PERIOD);
        burningColors(OIL_BURN, OIL_GLOW, N_COLORS);
    }

    @Override
    public Type type() {
        return Type.OIL;
    }

    @Override
    public String name() {
        return "Oil";
    }

    @Override
    public double activeness() {
        return 0.6;
    }

    @Override
    public boolean isDisplaceable() {
        return true;
    }

}
