package com.meowster.psim;

import static com.meowster.psim.ParticleFactory.createParticle;
import static com.meowster.psim.ParticleFactory.duplicateParticle;

class Grid {
    private static final double FIRE_SPREAD_PROB = 0.02;

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
            case STONE:
                processStone(p, cell);
                break;

            case DIRT:
                processDirt(p, cell);
                break;

            case GRASS:
                processGrass(p, cell);
                break;

            case SAND:
            case ASH:
                processSandOrAsh(p, cell);
                break;

            case WOOD:
                processWood(p, cell);
                break;

            case WATER:
                processWater(p, cell);
                break;

            case PLANT:
                processPlant(p, cell);
                break;

            case FIRE:
                processFire(p, cell);
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

    private void processStone(Particle p, Cell cell) {
        displaceDownwards(p, cell);
    }

    private void processDirt(Particle p, Cell cell) {
        if (displaceDownwards(p, cell)) {
            return;
        }
        Cell under = gu.below(cell);
        boolean onDirt = under == null || at(under).type() == Particle.Type.DIRT;
        Cell topside = gu.above(cell);
        if (onDirt && topside != null && at(topside).isEmpty()) {
            set(cell, createParticle(Particle.Type.GRASS));
        }
    }

    private void processGrass(Particle p, Cell cell) {
        // if grass is falling, turn it back into dirt
        if (displaceDownwards(p, cell)) {
            set(gu.below(cell), createParticle(Particle.Type.DIRT));
            return;
        }

        // if grass is no longer exposed to air, it's dirt once again
        Cell topside = gu.above(cell);
        if (topside != null && !at(topside).isEmpty()) {
            set(cell, createParticle(Particle.Type.DIRT));
        }
    }

    private void processSandOrAsh(Particle p, Cell cell) {
        if (displaceDownwards(p, cell)) {
            return;
        }

        // didn't go directly under - check for diag-left/right
        Cell diag;
        Cell side;
        if (gu.coinToss()) {
            diag = gu.leftBelow(cell);
            side = gu.left(cell);
        } else {
            diag = gu.rightBelow(cell);
            side = gu.right(cell);
        }

        if (side != null && diag != null) {
            if (at(side).isDisplaceable() && at(diag).isDisplaceable()) {
                swap(cell, diag);
            }
        }
    }

    private void processWater(Particle p, Cell cell) {
        if (emptyDownwards(p, cell)) {
            return;
        }

        // didn't swap with underneath: try left/right
        Cell side = gu.coinToss() ? gu.left(cell) : gu.right(cell);

        if (side != null && at(side).isEmpty()) {
            swap(cell, side);
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

    private void processFire(Particle p, Cell cell) {
        if (p.hasExpired()) {
            set(cell, createParticle(Particle.Type.EMPTY));
            return;
        }

        Cell adj = gu.selectAdjacent(cell);
        if (adj != null) {
            if (at(adj).isCombustible()) {
                Particle spark = duplicateParticle(p);
                set(adj, spark);
            } else {
                at(adj).ignite();
            }
        }
    }

    private void processWood(Particle p, Cell cell) {
        WoodParticle wp = (WoodParticle) p;
        if (wp.hasExpired()) {
            set(cell, createParticle(Particle.Type.ASH));
            return;
        }
        if (!wp.isBurning()) {
            return;
        }

        wp.burnTick();
        Cell adj = gu.selectAdjacent(cell);
        if (adj != null) {
            Particle p2 = at(adj);
            // burning wood will ignite adjacent wood
            if (gu.sameType(p, p2)) {
                p2.ignite();
            } else {
                maybeSetFireTo(p2, adj);
            }
        }
    }

    private void maybeSetFireTo(Particle p, Cell cell) {
        if (p.isCombustible() && gu.probability(FIRE_SPREAD_PROB)) {
            set(cell, createParticle(Particle.Type.FIRE));
        }
    }


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