package com.meowster.psim;

import java.awt.*;

public class EmptyParticle extends AbstractParticle {

    @Override
    public Type type() {
        return Particle.Type.EMPTY;
    }

    @Override
    public Color color() {
        return Parameters.EMPTY_COLOR;
    }

    @Override
    public String name() {
        return "Empty";
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isDisplaceable() {
        return true;
    }

}

