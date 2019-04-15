package com.meowster.psim;

/**
 * Represents a cell (location) in the particle grid.
 */
public class Cell {
    private final int row;
    private final int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public String toString() {
        return "[row:" + row + ", col:" + col + "]";
    }
}