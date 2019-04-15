package com.meowster.psim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.List;

import static com.meowster.psim.ParticleFactory.createParticleList;

public class GameCanvas extends JPanel {
    private static final Color BG_COLOR = Parameters.CANVAS_BG_COLOR;

    private static final int CELL_SIZE = Parameters.GRID_CELL_SIZE;
    private static final int ROWS = Parameters.GRID_ROWS;
    private static final int COLS = Parameters.GRID_COLS;

    private static final int WIDTH = CELL_SIZE * COLS;
    private static final int HEIGHT = CELL_SIZE * ROWS;

    // what we are currently drawing with the mouse
    private Particle.Type tool = Particle.Type.EMPTY;

    // our particle grid model
    private Grid grid = new Grid(ROWS, COLS);

    GameCanvas() {
        Dimension d = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);

        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        // create and start the main-loop process in a background thread...
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new MainLoop());
    }

    List<Particle> availableParticles() {
        return createParticleList();
    }

    void setTool(Particle.Type type) {
        tool = type;
    }

    void reset() {
        grid.fillGridWithEmpty();
    }

    public void paint(Graphics g) {
        paintBG(g);
        paintGrid(g);
    }

    private void paintBG(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void paintGrid(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Particle p = grid.at(row, col);
                paintCell(g, row, col, p.color());
            }
        }
    }

    private void paintCell(Graphics g, int row, int col, Color color) {
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
    private Cell toRowCol(MouseEvent e) {
        int row = e.getY() / CELL_SIZE;
        int col = e.getX() / CELL_SIZE;
        if (row < 0 || row >= ROWS ||
                col < 0 || col >= COLS) {
            return null;
        }
        return new Cell(row, col);
    }

    private void mouseCellEvent(Cell where) {
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

