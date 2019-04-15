package com.meowster.psim;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    GameControls controls;
    GameCanvas canvas;

    GamePanel() {
        canvas = new GameCanvas();
        controls = new GameControls(canvas);

        setLayout(new BorderLayout());
        add(controls, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);
    }

}
