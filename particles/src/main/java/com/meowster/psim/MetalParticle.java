package com.meowster.psim;

import java.awt.*;

public class MetalParticle extends AbstractParticle {

    @Override
    public Type type() {
        return Type.METAL;
    }

    @Override
    public Color color() {
        return Parameters.METAL_COLOR;
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