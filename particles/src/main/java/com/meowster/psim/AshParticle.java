package com.meowster.psim;

import java.awt.*;

public class AshParticle extends AbstractParticle {

    public Type type() {
        return Particle.Type.ASH;
    }

    public Color color() {
        return Parameters.ASH_COLOR;
    }

    public String name() {
        return "Ash";
    }

    public double activeness() {
        return 0.2;
    }

}

