package com.meowster.psim;

import java.awt.*;

public class MetalParticle extends AbstractParticle {
    private static final Color METAL_COLOR = new Color(0.5f, 0.5f, 0.6f);

    @Override
    public Type type() {
        return Type.METAL;
    }

    @Override
    public Color color() {
        return METAL_COLOR;
    }

    @Override
    public String name() {
        return "Metal";
    }

    @Override
    public boolean isReplaceable() {
        return false;
    }
}