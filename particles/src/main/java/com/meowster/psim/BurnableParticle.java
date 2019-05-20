package com.meowster.psim;

import java.awt.*;

public abstract class BurnableParticle extends ColoredParticle {

    private static final int GLOW_CYCLE = 10;

    private final int burnDuration;

    private boolean burning = false;
    private boolean burntOut = false;
    private Clock burnClock = null;
    private ColorScale burningColorScale;

    BurnableParticle(Color c1, Color c0, int nColors, int burnDuration) {
        super(c1, c0, nColors, 1);
        this.burnDuration = burnDuration;
        burningColors(c1, c0, nColors);
    }

    void burningColors(Color c1, Color c0, int nColors) {
        burningColorScale = new ColorScale(c1, c0, nColors);
    }

    @Override
    public ColorScale colorScale() {
        return isBurning() ? burningColorScale : super.colorScale();
    }

    @Override
    public void tick() {
        super.tick();
        if (burning) {
            burnTick();
        }
    }

    @Override
    public void ignite() {
        if (!burntOut && !burning) {
            burning = true;
            burnClock = new Clock(1, burnDuration);
            shimmerOn(GLOW_CYCLE);
        }
    }

    @Override
    public boolean hasExpired() {
        return burntOut;
    }

    boolean isBurning() {
        return burning;
    }

    void burnTick() {
        if (burnClock.countdown()) {
            burning = false;
            burnClock = null;
            burntOut = true;
        }
    }
}
