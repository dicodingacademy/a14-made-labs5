package com.example.dicoding.mywidgets;

import java.util.Random;

/**
 * Created by dicoding on 1/3/2017.
 */

public class NumberGenerator {

    public static int Generate(int max){

        Random random = new Random();
        int randomInt = random.nextInt(max);
        return randomInt;
    }
}
