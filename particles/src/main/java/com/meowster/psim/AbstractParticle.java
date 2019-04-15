package com.meowster.psim;

import java.awt.*;
import java.util.Random;

public abstract class AbstractParticle implements Particle {
    private static Random random = new Random();

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

    int fromRange(int limit) {
        return random.nextInt(limit);
    }

    Color choice(Color... selection) {
        return selection[fromRange(selection.length)];
    }

    void print(String s) {
        System.out.println(s);
    }
}

