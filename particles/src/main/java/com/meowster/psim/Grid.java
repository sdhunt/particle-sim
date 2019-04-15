package com.meowster.psim;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    int numRows;
    int numCols;
    Particle[][] gridContents;
    ArrayList<Particle> availableParticles;
    Random random = new Random();

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

    Particle at(RowCol cell) {
        return at(cell.row, cell.col);
    }

    Particle at(int row, int col) {
        return gridContents[row][col];
    }

    void set(RowCol cell, Particle p) {
        gridContents[cell.row][cell.col] = p;
    }


    void applyTool(Particle.Type tool, RowCol cell) {
        Particle current = at(cell);
        if (current.type() != tool) {
            Particle p = ParticleFactory.createParticle(tool);
            set(cell, p);
        }
    }

    // == particle processing
    void processRandomParticle() {
        RowCol cell = pickRandomCell();
        Particle p = at(cell);

        if (probability(p.activeness())) {
            // this particle is active and should "do its thing!"
            doYourThing(p, cell);
        }
    }

    RowCol pickRandomCell() {
        int row = random.nextInt(numRows);
        int col = random.nextInt(numCols);
        return new RowCol(row, col);
    }

    void doYourThing(Particle p, RowCol cell) {
        switch (p.type()) {
            case SAND:
//            case ASH:
                processSandOrAsh(p, cell);
                break;

/*
            case WATER:
                processWater((WaterParticle) p, cell);
                break;

            case PLANT:
                processPlant((PlantParticle) p, cell);
                break;

            case FIRE:
                processFire((FireParticle) p, cell);
                break;

            case WOOD:
                processWood((WoodParticle) p, cell);
                break;

            case VAC:
                processVac((VacParticle) p, cell);
                break;

            case FAIRY:
                processFairy((FairyParticle) p, cell);
                break;
*/

            default:
                // ignore other particle types
                break;
        }
    }

    void processSandOrAsh(Particle p, RowCol cell) {
        RowCol cellUnder = below(cell);
        if (cellUnder != null) {
            Particle p2 = at(cellUnder);
            if (p2.isDisplaceable()) {
                swap(cell, cellUnder);
                return;
            }
        }

        // didn't go directly under - check for diag-left/right
        RowCol cellDiag;
        RowCol cellSide;
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

/*
    void processWater(WaterParticle wp, RowCol cell) {
        RowCol cellUnder = below(cell);
        if (cellUnder != null) {
            Particle p2 = at(cellUnder);
            if (p2.isEmpty()) {
                swap(cell, cellUnder);
                return;
            }
        }

        // if we get to here, we didn't swap with underneath: try left/right
        RowCol cellSide;
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
*/

/*
    void processPlant(PlantParticle pp, RowCol cell) {
        RowCol adj = selectAdjacent(cell);
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
    void processFire(FireParticle fp, RowCol cell) {
        if (fp.hasExpired()) {
            set(cell, make(Particle.Type.EMPTY));
            return;
        }

        RowCol adj = selectAdjacent(cell);
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
    void processWood(WoodParticle wp, RowCol cell) {
        if (wp.hasExpired()) {
            set(cell, make(Particle.Type.ASH));
            return;
        }

        if (!wp.isBurning()) {
            return;
        }
        wp.burnTick();

        RowCol adj = selectAdjacent(cell);
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
    void processVac(VacParticle vp, RowCol cell) {
        RowCol adj = selectAdjacent(cell);
        if (adj != null) {
            Particle p = at(adj);
            if (p.type() == Particle.Type.ASH) {
                set(adj, make(Particle.Type.EMPTY));
            }
        }
    }
*/

/*
    void processFairy(FairyParticle fp, RowCol cell) {
        RowCol adj = selectAdjacent(cell);
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

    boolean validRowCol(RowCol rc) {
        return rc.row >= 0 && rc.row < numRows &&
                rc.col >= 0 && rc.col < numCols;
    }

    RowCol selectAdjacent(RowCol cell) {
        int dx = 0;
        int dy = 0;
        switch (random.nextInt(4)) {
            case 0: dx += 1; break;
            case 1: dx -= 1; break;
            case 2: dy += 1; break;
            case 3: dy -= 1; break;
        }
        RowCol adj = new RowCol(cell.row + dy, cell.col + dx);
        return validRowCol(adj) ? adj : null;
    }

    void swap(RowCol cell1, RowCol cell2) {
        Particle p1 = at(cell1);
        Particle p2 = at(cell2);
        set(cell1, p2);
        set(cell2, p1);
    }

    RowCol below(RowCol cell) {
        if (cell.row < numRows - 1) {
            return new RowCol(cell.row + 1, cell.col);
        }
        return null;
    }

    RowCol leftBelow(RowCol cell) {
        if (cell.row < numRows - 1 && cell.col > 0) {
            return new RowCol(cell.row + 1, cell.col - 1);
        }
        return null;
    }

    RowCol rightBelow(RowCol cell) {
        if (cell.row < numRows - 1 && cell.col < numCols - 1) {
            return new RowCol(cell.row + 1, cell.col + 1);
        }
        return null;
    }

    RowCol toLeft(RowCol cell) {
        if (cell.col > 0) {
            return new RowCol(cell.row, cell.col - 1);
        }
        return null;
    }

    RowCol toRight(RowCol cell) {
        if (cell.col < numCols - 1) {
            return new RowCol(cell.row, cell.col + 1);
        }
        return null;
    }
}