package com.meowster.psim;

import org.junit.Test;

public class CounterTest {

    @Test
    public void basic() {
        Counter c = new Counter(5);
        while (!c.atZero()) {
            System.out.println(c.count());
        }
        System.out.println("Done");
    }
}
