package com.griddynamics.gridu.qa.user.utils;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public class LongRange {

    private int minRange;
    private int maxRange;

    public Long randomLong() {
        return (long) new Random().nextInt(maxRange - minRange) + minRange;
    }

}
