package com.meowster.psim;

import org.junit.Test;

public class ClockTest {

    @Test
    public void basic() {
        Clock clock = new Clock(5, 3);
        System.out.println(clock);
        for (int i = 0; i < 30; i++) {
            boolean bigTick = clock.countdown();
            System.out.println(clock + " " + bigTick);
        }
    }
}
