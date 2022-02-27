package com.vsu.model;

import com.vsu.service.grid.GridTopology;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tile {
    final GridTopology archetype;
    @Getter @Setter
    TileType type;
    @Getter @Setter
    int weight;
    public int row;
    public int column;

    public Tile(int row, int column, GridTopology archetype) {
        this.row = row;
        this.column = column;
        this.archetype = archetype;
    }

    public Tile north(Grid grid) {
        return archetype.north(this, grid);
    }

    public Tile south(Grid grid) {
        return archetype.south(this, grid);
    }

    public Tile west(Grid grid) {
        return archetype.west(this, grid);
    }

    public Tile east(Grid grid) {
        return archetype.east(this, grid);
    }

    @Override
    public String toString() {
        return String.format("%s - (%d, %d) weight: %d", type, row, column, weight);
    }
}
