package com.dicoding.picodiploma.mywidgets;

import java.util.Random;

/**
 * Created by dicoding on 1/3/2017.
 */

class NumberGenerator {
    static int generate(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
