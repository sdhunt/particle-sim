package com.meowster.psim;

import javax.swing.*;

public class ParticleSim extends Display {

    private ParticleSim() {
        super("Particle Simulator", new GamePanel());
    }

    public static void main(String[] args) {
        new ParticleSim();
    }
}
