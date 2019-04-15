package com.meowster.psim;

import java.awt.*;

public class MetalParticle extends AbstractParticle {

    public Type type() {
        return Particle.Type.METAL;
    }

    public Color color() {
        return Parameters.METAL_COLOR;
    }

    public String name() {
        return "Metal";
    }
}