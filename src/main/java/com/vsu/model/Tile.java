package com.vsu.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tile {
    @Getter @Setter
    TileType type;
    @Getter @Setter
    int weight;
    public int row;
    public int column;

    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Tile north(Grid grid) {
        return row - 1 < 0 ? null : grid.matrix[row - 1][column];
    }

    public Tile south(Grid grid) {
        return row + 1 >= grid.matrix.length - 1 ? null : grid.matrix[row + 1][column];
    }

    public Tile west(Grid grid) {
        return column - 1 < 0 ? null : grid.matrix[row][column - 1];
    }

    public Tile east(Grid grid) {
        return column + 1 >= grid.matrix[0].length - 1 ? null : grid.matrix[row][column + 1];
    }

    @Override
    public String toString() {
        return String.format("%s - (%d, %d) weight: %d", type, row, column, weight);
    }
}
