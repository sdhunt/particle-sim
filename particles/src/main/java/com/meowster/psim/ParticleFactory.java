package com.meowster.psim;

import java.util.ArrayList;
import java.util.List;

class ParticleFactory {
    /**
     * Returns a list of particles that the user may "paint"
     * with as "tools".
     */
    static List<Particle> createParticleList() {
        List<Particle> list = new ArrayList<>();
        list.add(new EmptyParticle());
        list.add(new MetalParticle());
        list.add(new WoodParticle());
//        list.add(new AshParticle());
        list.add(new StoneParticle());
        list.add(new DirtParticle());
//        list.add(new GrassParticle());
        list.add(new SandParticle());
        list.add(new WaterParticle());
        list.add(new OilParticle());
        list.add(new PlantParticle());
        list.add(new FireParticle());
        return list;
    }

    /**
     * Creates and returns a particle of the specified type.
     */
    static Particle createParticle(Particle.Type type) {
        switch (type) {
            case EMPTY:
                return new EmptyParticle();
            case METAL:
                return new MetalParticle();
            case WOOD:
                return new WoodParticle();
            case ASH:
                return new AshParticle();
            case STONE:
                return new StoneParticle();
            case DIRT:
                return new DirtParticle();
            case GRASS:
                return new GrassParticle();
            case SAND:
                return new SandParticle();
            case WATER:
                return new WaterParticle();
            case OIL:
                return new OilParticle();
            case PLANT:
                return new PlantParticle();
            case FIRE:
                return new FireParticle();
            default:
                return null;
        }
    }

    /**
     * Returns a new particle of the same type as p.
     */
    static Particle duplicateParticle(Particle p) {
        return createParticle(p.type());
    }
}