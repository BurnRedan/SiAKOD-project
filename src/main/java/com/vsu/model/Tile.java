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
    public int i;
    public int j;

    public Tile(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Tile north(Grid grid) {
        return i - 1 < 0 ? null : grid.matrix[i - 1][j];
    }

    public Tile south(Grid grid) {
        return i + 1 >= grid.matrix.length - 1 ? null : grid.matrix[i + 1][j];
    }

    public Tile west(Grid grid) {
        return j - 1 < 0 ? null : grid.matrix[i][j - 1];
    }

    public Tile east(Grid grid) {
        return j + 1 >= grid.matrix[0].length - 1 ? null : grid.matrix[i][j + 1];
    }

    public boolean isWall() {
        return type == TileType.Wall;
    }

    @Override
    public String toString() {
        return String.format("%s - (%d, %d) weight: %d", type, i, j, weight);
    }
}
