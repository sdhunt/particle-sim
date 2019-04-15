package com.meowster.psim;

import java.awt.*;

public class EmptyParticle extends AbstractParticle {

    public Type type() {
        return Particle.Type.EMPTY;
    }

    public Color color() {
        return Parameters.EMPTY_COLOR;
    }

    public String name() {
        return "Empty";
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean isDisplaceable() {
        return true;
    }

}

