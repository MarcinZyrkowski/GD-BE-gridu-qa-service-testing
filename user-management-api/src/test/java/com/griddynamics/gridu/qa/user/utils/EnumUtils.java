package com.griddynamics.gridu.qa.user.utils;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;

public class EnumUtils {

    public static <E extends Enum<E>> E getRandomElement(Class<E> enumClass) {

        E[] enumConstants = enumClass.getEnumConstants();
        ArrayList<E> enumList = new ArrayList<E>(Arrays.asList(enumConstants));

        return ArrayUtils.getRandomElement(enumList);
    }

}
