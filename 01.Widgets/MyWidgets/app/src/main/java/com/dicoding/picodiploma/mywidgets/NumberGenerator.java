package com.dicoding.picodiploma.mywidgets;

import java.util.Random;

/**
 * Created by dicoding on 1/3/2017.
 */

public class NumberGenerator {

    public static int Generate(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
