package com.meowster.psim;

class Counter {
    private final int max;

    private int count;

    Counter(int max) {
        this.max = max;
        count = max;
    }

    int count() {
        count = count == 0 ? max : count - 1;
        return count;
    }

    boolean atZero() {
        return count == 0;
    }
}
