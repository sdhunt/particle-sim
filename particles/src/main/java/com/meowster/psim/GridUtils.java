package com.meowster.psim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GridUtils {
    private final int rows;
    private final int cols;
    private final Random random = new Random();

    GridUtils(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    private boolean validCoords(int row, int col) {
        return row >= 0 && row < rows &&
                col >= 0 && col < cols;
    }

    private Cell neighbor(Cell c, int dr, int dc) {
        int row = c.row() + dr;
        int col = c.col() + dc;
        return validCoords(row, col) ? new Cell(row, col) : null;
    }

    boolean validCell(Cell c) {
        return validCoords(c.row(), c.col());
    }

    Cell leftAbove(Cell cell) {
        return neighbor(cell, -1, -1);
    }

    Cell above(Cell cell) {
        return neighbor(cell, -1, 0);
    }

    Cell rightAbove(Cell cell) {
        return neighbor(cell, -1, 1);
    }

    Cell left(Cell cell) {
        return neighbor(cell, 0, -1);
    }

    Cell right(Cell cell) {
        return neighbor(cell, 0, 1);
    }

    Cell leftBelow(Cell cell) {
        return neighbor(cell, 1, -1);
    }

    Cell below(Cell cell) {
        return neighbor(cell, 1, 0);
    }

    Cell rightBelow(Cell cell) {
        return neighbor(cell, 1, 1);
    }

    Cell selectAdjacent(Cell cell) {
        int dr = 0;
        int dc = 0;
        switch (random.nextInt(4)) {
            case 0: dr++; break;
            case 1: dr--; break;
            case 2: dc++; break;
            case 3: dc--; break;
        }
        return neighbor(cell, dr, dc);
    }

    Cell selectRandomCell() {
        int row = random.nextInt(rows);
        int col = random.nextInt(cols);
        return new Cell(row, col);
    }

    boolean probability(double prob) {
        return random.nextDouble() < prob;
    }

    boolean coinToss() {
        return probability(0.5);
    }

    boolean sameType(Particle a, Particle b) {
        return a.type() == b.type();
    }

    List<Cell> adjacents(Cell cell) {
        Cell u = above(cell);
        Cell d = below(cell);
        Cell l = left(cell);
        Cell r = right(cell);
        List<Cell> adj = new ArrayList<>();
        if (u != null) adj.add(u);
        if (d != null) adj.add(d);
        if (l != null) adj.add(l);
        if (r != null) adj.add(r);
        return adj;
    }
}
