package com.meowster.psim;

import java.util.Random;

public abstract class AbstractParticle implements Particle {
    Random random = new Random();

    public double activeness() {
        return 0.0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isDisplaceable() {
        return false;
    }

    public boolean isCombustible() {
        return false;
    }

    public boolean hasExpired() {
        return false;
    }

    public void ignite() {
    }
}

