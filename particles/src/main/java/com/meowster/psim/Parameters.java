package com.meowster.psim;

import java.awt.*;

class Parameters {

    static final Color CONTROLS_BG_COLOR = new Color(0.9f, 0.8f, 0.5f);
    static final Color CANVAS_BG_COLOR = new Color(0.2f, 0.2f, 0.5f);

    static final Color EMPTY_COLOR = new Color(0.1f, 0.2f, 0.3f);
    static final Color METAL_COLOR = new Color(0.5f, 0.5f, 0.6f);
    static final Color SAND_COLOR = new Color(0.95f, 0.95f, 0.7f);
    static final Color WATER_COLOR = new Color(0.5f, 0.6f, 1.0f);

    static final Color PLANT_COLOR = new Color(0.5f, 0.9f, 0.6f);
    static final Color PLANT_DECAY_COLOR = new Color(0.3f, 0.5f, 0.2f);
    static final Color FIRE_COLOR = new Color(0.9f, 0.5f, 0.3f);
    static final Color FIRE_EMBER_COLOR = new Color(0.6f, 0.3f, 0.3f);
    static final Color WOOD_COLOR = new Color(0.6f, 0.4f, 0.2f);
    static final Color ASH_COLOR = new Color(0.9f, 0.9f, 0.95f);

    static final int GRID_ROWS = 80;
    static final int GRID_COLS = 100;
    static final int GRID_CELL_SIZE = 10;

    static final int STEPS_PER_LOOP = 20000;
    static final long MAIN_LOOP_PAUSE_MS = 8;

}
