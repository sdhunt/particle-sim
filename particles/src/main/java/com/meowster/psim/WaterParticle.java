package com.meowster.psim;

import java.awt.*;

public class WaterParticle extends AbstractParticle {

    @Override
    public Type type() {
        return Type.WATER;
    }

    @Override
    public Color color() {
        return Parameters.WATER_COLOR;
    }

    @Override
    public String name() {
        return "Water";
    }

    @Override
    public double activeness() {
        return 0.9;
    }

    @Override
    public boolean isDisplaceable() {
        return true;
    }

}
