package com.meowster.psim;

public class RowCol {
    int row;
    int col;

    RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "[row:" + row + ", col:" + col + "]";
    }
}