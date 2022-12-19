package com.griddynamics.gridu.qa.payment.test_data;

import com.griddynamics.gridu.qa.payment.utils.NumberRange;

public class TestData {

    protected static String createRandomDigitSequence(int digitSequenceLength) {
        NumberRange digit = new NumberRange(0, 9);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digitSequenceLength; i++) {
            sb.append(digit.randomInt());
        }
        return sb.toString();
    }

}
