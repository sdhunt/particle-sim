package com.meowster.psim;

import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel {
    GamePanel() {
        GameCanvas canvas = new GameCanvas();
        GameControls controls = new GameControls(canvas);

        setLayout(new BorderLayout());
        add(controls, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);
    }
}
