package com.meowster.psim;

import java.awt.*;

public interface Particle {
    enum Type {
        EMPTY, METAL, SAND, WATER, PLANT, // FIRE, WOOD, ASH, VAC, FAIRY
    }

    /**
     * Returns the enumeration constant representing the particle type.
     */
    Type type();

    /**
     * Returns the color in which the particle should be drawn.
     */
    Color color();

    /**
     * Returns the name of this particle.
     */
    String name();

    /**
     * Returns a value between 0.0 and 1.0, representing how "active" these
     * particles are. 0.0 means no activity; 0.5 means active 50% of the time;
     * 1.0 means active all the time.
     */
    double activeness();

    /**
     * Invoked when the particle is selected and needs to do something.
     */
    void tick();

    /**
     * May be invoked when a source of heat (fire, lava, etc.) is adjacent.
     */
    void ignite();

    /**
     * Returns true if this particle is an EMPTY particle.
     */
    boolean isEmpty();

    /**
     * Returns true if this particle can be replaced by the particle tool.
     */
    boolean isReplaceable();

    /**
     * Returns true if this particle is displaceable (by sand, etc.)
     */
    boolean isDisplaceable();

    /**
     * Returns true if this particle will be consumed by fire.
     */
    boolean isCombustible();

    /**
     * Returns true if this particle has reached the end of its life.
     */
    boolean hasExpired();
}
