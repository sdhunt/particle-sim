package com.meowster.psim;

import java.awt.*;

public class SandParticle extends AbstractParticle {

    @Override
    public Type type() {
        return Particle.Type.SAND;
    }

    @Override
    public Color color() {
        return Parameters.SAND_COLOR;
    }

    @Override
    public String name() {
        return "Sand";
    }

    @Override
    public double activeness() {
        return 0.5;
    }

}
