package com.meowster.psim;

import java.awt.*;

public class SandParticle extends AbstractParticle {

    public Type type() {
        return Particle.Type.SAND;
    }

    public Color color() {
        return Parameters.SAND_COLOR;
    }

    public String name() {
        return "Sand";
    }

    public double activeness() {
        return 0.5;
    }

}
