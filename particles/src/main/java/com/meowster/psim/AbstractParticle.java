package com.meowster.psim;

import java.util.Random;

public abstract class AbstractParticle implements Particle {
    protected static Random random = new Random();

    @Override
    public double activeness() {
        return 0.0;
    }

    @Override
    public void tick() {
    }

    @Override
    public void ignite() {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isReplaceable() {
        return true;
    }

    @Override
    public boolean isDisplaceable() {
        return false;
    }

    @Override
    public boolean isCombustible() {
        return false;
    }

    @Override
    public boolean hasExpired() {
        return false;
    }

}

