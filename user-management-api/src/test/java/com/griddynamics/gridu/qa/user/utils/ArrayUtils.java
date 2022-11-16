package com.griddynamics.gridu.qa.user.utils;

import java.util.List;
import java.util.Random;

public class ArrayUtils {

    public static <E> E getRandomElement(List<E> list){

        if(list.isEmpty()){
            return null;
        }

        return list.get(new Random().nextInt(list.size()));
    }

}
