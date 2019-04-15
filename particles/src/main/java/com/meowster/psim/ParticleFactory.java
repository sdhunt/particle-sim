package com.meowster.psim;

import java.util.ArrayList;

public class ParticleFactory {
    static ArrayList<Particle> createParticleList() {
        ArrayList<Particle> list = new ArrayList<>();
        list.add(new EmptyParticle());
        list.add(new MetalParticle());
        list.add(new SandParticle());
//        list.add(new WaterParticle());
//        list.add(new PlantParticle());
//        list.add(new FireParticle());
//        list.add(new WoodParticle());
//        list.add(new VacParticle());
//        list.add(new FairyParticle());
        // NOTE: don't add ASH
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
//            case WATER:
//                return new WaterParticle();
//            case PLANT:
//                return new PlantParticle();
//            case FIRE:
//                return new FireParticle();
//            case WOOD:
//                return new WoodParticle();
//            case ASH:
//                return new AshParticle();
//            case VAC:
//                return new VacParticle();
//            case FAIRY:
//                return new FairyParticle();
            default:
                return null;
        }
    }
}