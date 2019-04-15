package com.meowster.psim;

class GridUtils {
    private final int rows;
    private final int cols;

    GridUtils(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }


    Cell above(Cell cell) {
        if (cell.row() > 0) {
            return new Cell(cell.row() - 1, cell.col());
        }
        return null;
    }

    Cell leftAbove(Cell cell) {
        if (cell.row() > 0 && cell.col() > 0) {
            return new Cell(cell.row() - 1, cell.col() - 1);
        }
        return null;
    }

    Cell rightAbove(Cell cell) {
        if (cell.row() > 0 && cell.col() < cols - 1) {
            return new Cell(cell.row() - 1, cell.col() + 1);
        }
        return null;
    }

    Cell left(Cell cell) {
        if (cell.col() > 0) {
            return new Cell(cell.row(), cell.col() - 1);
        }
        return null;
    }

    Cell right(Cell cell) {
        if (cell.col() < cols - 1) {
            return new Cell(cell.row(), cell.col() + 1);
        }
        return null;
    }

    Cell below(Cell cell) {
        if (cell.row() < rows - 1) {
            return new Cell(cell.row() + 1, cell.col());
        }
        return null;
    }

    Cell leftBelow(Cell cell) {
        if (cell.row() < rows - 1 && cell.col() > 0) {
            return new Cell(cell.row() + 1, cell.col() - 1);
        }
        return null;
    }

    Cell rightBelow(Cell cell) {
        if (cell.row() < rows - 1 && cell.col() < cols - 1) {
            return new Cell(cell.row() + 1, cell.col() + 1);
        }
        return null;
    }

}
