package com.meowster.psim;

import java.awt.*;

import static com.meowster.psim.Parameters.PLANT_COLOR;
import static com.meowster.psim.Parameters.PLANT_DECAY_COLOR;

public class PlantParticle extends ColorAgingParticle {
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
        return 0.05;
    }

}
