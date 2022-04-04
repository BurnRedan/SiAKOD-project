package com.vsu.model;


import com.vsu.service.grid.PlaneTopology;
import lombok.*;

@NoArgsConstructor
@Getter
public class Grid {
    @NonNull
    @Setter
    int rowSize;
    @NonNull
    @Setter
    int colSize;

    Tile[][] matrix;

    public Grid(@NonNull int rowSize, @NonNull int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        matrix = new Tile[rowSize][colSize];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Tile(i, j, new PlaneTopology(), 1);
            }
        }
    }

    public void setMatrix(int rowSize, int colSize) {
        matrix = new Tile[rowSize][colSize];
    }
}
