package com.meowster.psim;

class Counter {
    private final int max;

    private int count;

    Counter(int max) {
        this.max = max;
        count = max;
    }

    int value() {
        return count;
    }

    boolean count() {
        boolean rollover = false;
        count--;
        if (count < 0) {
            count = max;
            rollover = true;
        }
        return rollover;
    }

    boolean atMax() {
        return count == max;
    }

    boolean atZero() {
        return count == 0;
    }
}
