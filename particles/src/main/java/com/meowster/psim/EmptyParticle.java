package com.meowster.psim;

import java.awt.*;

public class EmptyParticle extends AbstractParticle {
    private static final Color EMPTY_COLOR = new Color(0.1f, 0.2f, 0.3f);

    @Override
    public Type type() {
        return Particle.Type.EMPTY;
    }

    @Override
    public Color color() {
        return EMPTY_COLOR;
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

