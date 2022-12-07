package com.griddynamics.gridu.qa.user.tf.utils;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public class NumberRange {

    private int minRange;
    private int maxRange;

    public Integer randomInt() {
        return new Random().nextInt(maxRange - minRange) + minRange;
    }

    public Long randomLong() {
        return (long) randomInt();
    }

}
