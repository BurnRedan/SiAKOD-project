package com.vsu.landscape;

import java.util.concurrent.ThreadLocalRandom;

public class RandomLandGenStrategy extends LandGenStrategy {

    public RandomLandGenStrategy() {
        type = LandGenType.Random;
    }

    @Override
    public int[][] getLandscape(int row, int col) {
        int[][] m = new int[row][col];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = ThreadLocalRandom.current().nextInt(1, 100);
            }
        }
        return m;
    }
}
