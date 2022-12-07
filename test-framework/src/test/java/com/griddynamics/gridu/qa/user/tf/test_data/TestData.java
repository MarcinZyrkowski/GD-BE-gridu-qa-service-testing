package com.griddynamics.gridu.qa.user.tf.test_data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.griddynamics.gridu.qa.user.ObjectFactory;
import com.griddynamics.gridu.qa.user.tf.utils.NumberRange;

public abstract class TestData {

    protected final static ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    protected static ObjectMapper objectMapper = new ObjectMapper();

    public static String createRandomDigitSequence(int digitSequenceLength) {
        NumberRange digit = new NumberRange(0, 9);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digitSequenceLength; i++) {
            sb.append(digit.randomInt());
        }
        return sb.toString();
    }

}
