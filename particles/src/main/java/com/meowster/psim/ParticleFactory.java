package com.meowster.psim;

import java.util.ArrayList;
import java.util.List;

class ParticleFactory {
    static List<Particle> createParticleList() {
        List<Particle> list = new ArrayList<>();
        list.add(new EmptyParticle());
        list.add(new MetalParticle());
        list.add(new SandParticle());
        list.add(new WaterParticle());
        return list;
    }

    static Particle createParticle(Particle.Type type) {
        switch (type) {
            case EMPTY:
                return new EmptyParticle();
            case METAL:
                return new MetalParticle();
            case SAND:
                return new SandParticle();
            case WATER:
                return new WaterParticle();
            default:
                return null;
        }
    }
}