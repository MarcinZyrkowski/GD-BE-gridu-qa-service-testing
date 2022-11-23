package com.griddynamics.gridu.qa.user.tests.test_data;

import com.griddynamics.gridu.qa.user.ObjectFactory;
import com.griddynamics.gridu.qa.user.utils.NumberRange;

public abstract class TestData {

    protected final static ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    public static String createRandomDigitSequence(int digitSequenceLength) {
        NumberRange digit = new NumberRange(0, 9);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digitSequenceLength; i++) {
            sb.append(digit.randomInt());
        }
        return sb.toString();
    }

}
