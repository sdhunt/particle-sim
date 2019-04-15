package com.meowster.psim;

import java.util.Random;

class ProbDistrib {
    private static final Random random = new Random();

    private final int[] cumulative;
    private final int total;

    ProbDistrib(int... density) {
        cumulative = new int[density.length];
        int total = 0;
        int i = 0;
        for (int value: density) {
            total += value;
            cumulative[i++] = total;
        }
        this.total = total;
    }


    int nextIndex() {
        int r = random.nextInt(total);
        int i = 0;
        while (r > cumulative[i++]) ;
        return i-1;
    }

    int size() {
        return cumulative.length;
    }

    static ProbDistrib equalDistrib(int n) {
        int[] density = new int[n];
        for (int i=0; i<n; i++) {
            density[i] = 1;
        }
        return new ProbDistrib(density);
    }
}
