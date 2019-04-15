package com.meowster.psim;

import java.util.List;
import java.util.Random;

public class Grid {
    private final int numRows;
    private final int numCols;
    private final Particle[][] gridContents;
    private final List<Particle> availableParticles;
    private final Random random = new Random();

    Grid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        availableParticles = ParticleFactory.createParticleList();
        gridContents = new Particle[numRows][numCols];
        fillGridWithEmpty();
    }


    void fillGridWithEmpty() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                gridContents[row][col] = new EmptyParticle();
            }
        }
    }

    Particle at(Cell cell) {
        return at(cell.row(), cell.col());
    }

    Particle at(int row, int col) {
        return gridContents[row][col];
    }

    void set(Cell cell, Particle p) {
        gridContents[cell.row()][cell.col()] = p;
    }


    void applyTool(Particle.Type tool, Cell cell) {
        Particle current = at(cell);
        if (current.type() != tool) {
            Particle p = ParticleFactory.createParticle(tool);
            set(cell, p);
        }
    }

    // == particle processing
    void processRandomParticle() {
        Cell cell = pickRandomCell();
        Particle p = at(cell);

        if (probability(p.activeness())) {
            // this particle is active and should "do its thing!"
            doYourThing(p, cell);
        }
    }

    Cell pickRandomCell() {
        int row = random.nextInt(numRows);
        int col = random.nextInt(numCols);
        return new Cell(row, col);
    }

    void doYourThing(Particle p, Cell cell) {
        switch (p.type()) {
            case SAND:
//            case ASH:
                processSandOrAsh(p, cell);
                break;

            case WATER:
                processWater((WaterParticle) p, cell);
                break;

            default:
                // ignore other particle types
                break;
        }
    }

    void processSandOrAsh(Particle p, Cell cell) {
        Cell cellUnder = below(cell);
        if (cellUnder != null) {
            Particle p2 = at(cellUnder);
            if (p2.isDisplaceable()) {
                swap(cell, cellUnder);
                return;
            }
        }

        // didn't go directly under - check for diag-left/right
        Cell cellDiag;
        Cell cellSide;
        if (chooseLeft()) {
            cellDiag = leftBelow(cell);
            cellSide = toLeft(cell);
        } else {
            cellDiag = rightBelow(cell);
            cellSide = toRight(cell);
        }

        if (cellDiag != null && at(cellSide).isDisplaceable()) {
            Particle p2 = at(cellDiag);
            if (p2.isDisplaceable()) {
                swap(cell, cellDiag);
            }
        }

    }

    void processWater(WaterParticle wp, Cell cell) {
        Cell cellUnder = below(cell);
        if (cellUnder != null) {
            Particle p2 = at(cellUnder);
            if (p2.isEmpty()) {
                swap(cell, cellUnder);
                return;
            }
        }

        // if we get to here, we didn't swap with underneath: try left/right
        Cell cellSide;
        if (chooseLeft()) {
            cellSide = toLeft(cell);
        } else {
            cellSide = toRight(cell);
        }

        if (cellSide != null) {
            Particle p2 = at(cellSide);
            if (p2.isEmpty()) {
                swap(cell, cellSide);
            }
        }
    }

/*
    void processPlant(PlantParticle pp, Cell cell) {
        Cell adj = selectAdjacent(cell);
        if (adj != null) {
            Particle p = at(adj);
            // plant will propagate through water
            if (p.type() == Particle.Type.WATER) {
                Particle plant = duplicate(pp);
                set(adj, plant);
            }
        }
    }
*/

/*
    void processFire(FireParticle fp, Cell cell) {
        if (fp.hasExpired()) {
            set(cell, make(Particle.Type.EMPTY));
            return;
        }

        Cell adj = selectAdjacent(cell);
        if (adj != null) {
            Particle p = at(adj);
            // fire will burn combustibles
            if (p.isCombustible()) {
                Particle spark = duplicate(fp);
                set(adj, spark);
            } else {
                p.ignite();
            }
        }
    }
*/

/*
    void processWood(WoodParticle wp, Cell cell) {
        if (wp.hasExpired()) {
            set(cell, make(Particle.Type.ASH));
            return;
        }

        if (!wp.isBurning()) {
            return;
        }
        wp.burnTick();

        Cell adj = selectAdjacent(cell);
        if (adj != null) {
            Particle p = at(adj);
            // burning wood will ignite adjacent wood
            if (p.type() == Particle.Type.WOOD) {
                p.ignite();
            } else if (p.isCombustible() && probability(0.1)) {
                set(adj, make(Particle.Type.FIRE));
            }
        }
    }
*/

/*
    void processVac(VacParticle vp, Cell cell) {
        Cell adj = selectAdjacent(cell);
        if (adj != null) {
            Particle p = at(adj);
            if (p.type() == Particle.Type.ASH) {
                set(adj, make(Particle.Type.EMPTY));
            }
        }
    }
*/

/*
    void processFairy(FairyParticle fp, Cell cell) {
        Cell adj = selectAdjacent(cell);
        if (adj != null) {
            Particle p = at(adj);
            if (p.isEmpty()) {
                swap(cell, adj);
            }
        }
    }
*/

    //---------------------
    // utility  functions

    Particle make(Particle.Type type) {
        return ParticleFactory.createParticle(type);
    }

    // Returns a new particle of the same type as p.
    Particle duplicate(Particle p) {
        return ParticleFactory.createParticle(p.type());
    }

    boolean probability(double prob) {
        return random.nextDouble() < prob;
    }

    boolean chooseLeft() {
        return random.nextInt(2) == 0;
    }

    boolean validCell(Cell c) {
        return c.row() >= 0 && c.row() < numRows &&
                c.col() >= 0 && c.col() < numCols;
    }

    Cell selectAdjacent(Cell cell) {
        int dx = 0;
        int dy = 0;
        switch (random.nextInt(4)) {
            case 0: dx += 1; break;
            case 1: dx -= 1; break;
            case 2: dy += 1; break;
            case 3: dy -= 1; break;
        }
        Cell adj = new Cell(cell.row() + dy, cell.col() + dx);
        return validCell(adj) ? adj : null;
    }

    void swap(Cell cell1, Cell cell2) {
        Particle p1 = at(cell1);
        Particle p2 = at(cell2);
        set(cell1, p2);
        set(cell2, p1);
    }

    Cell below(Cell cell) {
        if (cell.row() < numRows - 1) {
            return new Cell(cell.row() + 1, cell.col());
        }
        return null;
    }

    Cell leftBelow(Cell cell) {
        if (cell.row() < numRows - 1 && cell.col() > 0) {
            return new Cell(cell.row() + 1, cell.col() - 1);
        }
        return null;
    }

    Cell rightBelow(Cell cell) {
        if (cell.row() < numRows - 1 && cell.col() < numCols - 1) {
            return new Cell(cell.row() + 1, cell.col() + 1);
        }
        return null;
    }

    Cell toLeft(Cell cell) {
        if (cell.col() > 0) {
            return new Cell(cell.row(), cell.col() - 1);
        }
        return null;
    }

    Cell toRight(Cell cell) {
        if (cell.col() < numCols - 1) {
            return new Cell(cell.row(), cell.col() + 1);
        }
        return null;
    }
}