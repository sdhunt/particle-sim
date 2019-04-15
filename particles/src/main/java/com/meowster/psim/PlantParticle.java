package com.meowster.psim;

import java.awt.*;

public class PlantParticle extends ColorAgingParticle {
    private static final Color PLANT_COLOR = new Color(0.4f, 0.9f, 0.5f);
    private static final Color PLANT_DECAY_COLOR = new Color(0.3f, 0.5f, 0.2f);

    PlantParticle() {
        super(PLANT_COLOR, PLANT_DECAY_COLOR, 12, 100);
    }

    @Override
    public Type type() {
        return Type.PLANT;
    }

    @Override
    public String name() {
        return "Plant";
    }

    @Override
    public double activeness() {
        return 0.1;
    }

    @Override
    public boolean isCombustible() {
        return !clockAtStart();
    }
}
