package com.meowster.psim;

import java.awt.*;

public class WoodParticle extends BurnableParticle {
    private static final Color WOOD_COLOR = new Color(0.6f, 0.4f, 0.2f);
    private static final Color WOOD_COLOR_2 = new Color(0.65f, 0.45f, 0.2f);

    private static final Color WOOD_GLOW = new Color(1.0f, 0.25f, 0.05f);
    private static final Color WOOD_EMBER = new Color(0.7f, 0.25f, 0.05f);
    private static final int GRADS = 5;
    private static final int BURN_DURATION = 500;

    WoodParticle() {
        super(WOOD_GLOW, WOOD_EMBER, GRADS, BURN_DURATION);
        setColor(choice(WOOD_COLOR, WOOD_COLOR_2));
    }

    @Override
    public Type type() {
        return Type.WOOD;
    }

    @Override
    public String name() {
        return "Wood";
    }

    @Override
    public double activeness() {
        return 0.2;
    }

    @Override
    public boolean isReplaceable() {
        return false;
    }
}
