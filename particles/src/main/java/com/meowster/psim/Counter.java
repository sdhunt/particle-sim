package com.meowster.psim;

public class Counter {
    private final int max;

    private int count;

    public Counter(int max) {
        this.max = max;
        count = max;
    }

    public int count() {
        count = count == 0 ? max : count - 1;
        return count;
    }

    public boolean atZero() {
        return count == 0;
    }
}
