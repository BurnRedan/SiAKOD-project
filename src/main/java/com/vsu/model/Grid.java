package com.vsu.model;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Grid {
    @Setter
    int rowSize;
    @Setter
    int colSize;

    Tile[][] matrix;


    public void setMatrix(int rowSize, int colSize) {
        matrix = new Tile[rowSize][colSize];
    }
}
