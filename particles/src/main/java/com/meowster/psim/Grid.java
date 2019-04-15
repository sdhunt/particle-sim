package com.meowster.psim;

import static com.meowster.psim.ParticleFactory.createParticle;
import static com.meowster.psim.ParticleFactory.duplicateParticle;

class Grid {
    private final int rows;
    private final int cols;
    private final Particle[][] gridContents;
    private final GridUtils gu;

    Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        gu = new GridUtils(rows, cols);
        gridContents = new Particle[rows][cols];
        fillGridWithEmpty();
    }

    void fillGridWithEmpty() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                gridContents[row][col] = new EmptyParticle();
            }
        }
    }

    void applyTool(Particle.Type tool, Cell cell) {
        Particle current = at(cell);
        if (current.type() != tool) {
            if (tool == Particle.Type.EMPTY || current.isReplaceable()) {
                set(cell, createParticle(tool));
            }
        }
    }

    Particle at(int row, int col) {
        return gridContents[row][col];
    }

    private Particle at(Cell cell) {
        return at(cell.row(), cell.col());
    }

    private void set(Cell cell, Particle p) {
        gridContents[cell.row()][cell.col()] = p;
    }

    private void swap(Cell c1, Cell c2) {
        Particle p1 = at(c1);
        Particle p2 = at(c2);
        set(c1, p2);
        set(c2, p1);
    }

    // == particle processing
    void processRandomParticle() {
        Cell cell = gu.selectRandomCell();
        Particle p = at(cell);
        if (gu.probability(p.activeness())) {
            // this particle is active and should "do its thing!"
            doYourThing(p, cell);
        }
    }

    private void doYourThing(Particle p, Cell cell) {
        p.tick();
        switch (p.type()) {
            case SAND:
//            case ASH:
                processSandOrAsh(p, cell);
                break;

            case WATER:
                processWater(p, cell);
                break;

            case PLANT:
                processPlant(p, cell);
                break;

            default:
                // ignore other particle types
                break;
        }
    }

    private boolean displaceDownwards(Particle p, Cell cell) {
        Cell cellUnder = gu.below(cell);
        if (cellUnder != null) {
            Particle p2 = at(cellUnder);
            if (p2.isDisplaceable()) {
                swap(cell, cellUnder);
                return true;
            }
        }
        return false;
    }

    private boolean emptyDownwards(Particle p, Cell cell) {
        Cell cellUnder = gu.below(cell);
        if (cellUnder != null) {
            Particle p2 = at(cellUnder);
            if (p2.isEmpty()) {
                swap(cell, cellUnder);
                return true;
            }
        }
        return false;
    }

    private void processSandOrAsh(Particle p, Cell cell) {
        if (displaceDownwards(p, cell)) {
            return;
        }

        // didn't go directly under - check for diag-left/right
        Cell cellDiag;
        Cell cellSide;
        if (gu.coinToss()) {
            cellDiag = gu.leftBelow(cell);
            cellSide = gu.left(cell);
        } else {
            cellDiag = gu.rightBelow(cell);
            cellSide = gu.right(cell);
        }

        if (cellSide != null && cellDiag != null) {
            if (at(cellSide).isDisplaceable() && at(cellDiag).isDisplaceable()) {
                swap(cell, cellDiag);
            }
        }
    }

    private void processWater(Particle p, Cell cell) {
        if (emptyDownwards(p, cell)) {
            return;
        }

        // didn't swap with underneath: try left/right
        Cell cellSide = gu.coinToss() ? gu.left(cell) : gu.right(cell);

        if (cellSide != null && at(cellSide).isEmpty()) {
            swap(cell, cellSide);
        }
    }

    private void processPlant(Particle p, Cell cell) {
        Cell adj = gu.selectAdjacent(cell);
        if (adj != null) {
            // plant will propagate through water
            if (at(adj).type() == Particle.Type.WATER) {
                set(adj, duplicateParticle(p));
            }
        }
    }

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
}