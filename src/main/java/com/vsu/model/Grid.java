package com.vsu.model;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Grid {
    @Setter
    int xSize;
    @Setter
    int ySize;

    Tile[][] matrix;


    public void setMatrix(int xSize, int ySize) {
        matrix = new Tile[xSize][ySize];
    }
}
