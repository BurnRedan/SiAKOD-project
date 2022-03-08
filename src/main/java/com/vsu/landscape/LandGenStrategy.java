package com.vsu.landscape;

import lombok.Getter;

@Getter
public abstract class LandGenStrategy {

    protected LandGenType type;

    public abstract int[][] getLandscape(int row, int col);
}
