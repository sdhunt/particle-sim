package com.meowster.psim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GameCanvas extends JPanel {
    static final Color BG_COLOR = Parameters.CANVAS_BG_COLOR;

    static final int CELL_SIZE = Parameters.GRID_CELL_SIZE;
    static final int ROWS = Parameters.GRID_ROWS;
    static final int COLS = Parameters.GRID_COLS;

    static final int WIDTH = CELL_SIZE * COLS;
    static final int HEIGHT = CELL_SIZE * ROWS;

    Executor executor = Executors.newSingleThreadExecutor();
    MouseHandler mouseHandler = new MouseHandler();

    // where the mouse is (if not null)
    RowCol mouseCell = null;

    // what we are currently drawing with the mouse
    Particle.Type tool = Particle.Type.EMPTY;

    // our particle grid model
    Grid grid = new Grid(ROWS, COLS);

    GameCanvas() {
        Dimension d = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        // create and start the main-loop process in a background thread...
        executor.execute(new MainLoop());
    }

    ArrayList<Particle> availableParticles() {
        return ParticleFactory.createParticleList();
    }

    void setTool(Particle.Type type) {
        tool = type;
    }

    public void paint(Graphics g) {
        paintBG(g);
        paintGrid(g);
    }

    void paintBG(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    void paintGrid(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Particle p = grid.at(row, col);
                paintCell(g, row, col, p.color());
            }
        }
    }


    void paintCell(Graphics g, int row, int col, Color color) {
        int x = col * CELL_SIZE;
        int y = row * CELL_SIZE;
        g.setColor(color);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Returns the row and column of the mouse location
     * specified in the event, if within bounds of the
     * display, otherwise null.
     *
     * @param e the mouse event
     * @return the location, or null
     */
    private RowCol toRowCol(MouseEvent e) {
        int row = e.getY() / CELL_SIZE;
        int col = e.getX() / CELL_SIZE;
        if (row < 0 || row >= ROWS ||
                col < 0 || col >= COLS) {
            return null;
        }
        return new RowCol(row, col);
    }

    void mouseCellEvent(RowCol where) {
        mouseCell = where;
        if (where != null) {
            grid.applyTool(tool, where);
        }
        repaint();
    }


    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            mouseCellEvent(toRowCol(e));
        }

        public void mouseReleased(MouseEvent e) {
            mouseCellEvent(null);
        }

        public void mouseDragged(MouseEvent e) {
            mouseCellEvent(toRowCol(e));
        }
    }


    // this is the main processing loop (runs in a background thread)
    private class MainLoop implements Runnable {
        public void run() {
            while (true) {
                for (int i = 0; i < Parameters.STEPS_PER_LOOP; i++) {
                    grid.processRandomParticle();
                }
                repaint();
                pause();
            }
        }

        void pause() {
            try {
                Thread.sleep(Parameters.MAIN_LOOP_PAUSE_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

