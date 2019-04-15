package com.meowster.psim;

public class Clock {
    private final Counter major;
    private final Counter minor;
    private boolean loop = false;

    Clock(int majorTicks, int minorTicks) {
        major = new Counter(majorTicks);
        minor = new Counter(minorTicks);
    }

    Clock loop() {
        loop = true;
        return this;
    }

    boolean countdown() {
        boolean stepdown = false;

        if (loop || !major.atZero() || !minor.atZero()) {
            boolean rollover = minor.count();
            if (rollover) {
                stepdown = true;
                major.count();
            }
        }
        return stepdown;
    }

    boolean atStart() {
        return major.atMax();
    }

    boolean atZero() {
        return major.atZero() && minor.atZero();
    }

    int majorValue() {
        return major.value();
    }

    public String toString() {
        return "Clock: " + major.value() + " : " + minor.value();
    }
}
