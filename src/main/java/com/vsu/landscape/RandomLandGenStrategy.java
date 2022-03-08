package com.vsu.landscape;

import java.util.concurrent.ThreadLocalRandom;

public class RandomLandGenStrategy extends LandGenStrategy {

    public RandomLandGenStrategy() {
        type = LandGenType.Random;
    }

    @Override
    public int[][] getLandscape(int row, int col) {
        int[][] m = new int[row][col];
        for (int[] r : m) {
            for (int item : r) {
                item = ThreadLocalRandom.current().nextInt(1, 100);
            }
        }
        return m;
    }
}
