package com.vsu.model;

import com.vsu.service.grid.GridTopology;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Tile {
    @EqualsAndHashCode.Exclude final GridTopology archetype;
    @Getter @Setter
    TileType type;
    @Getter @Setter
    int weight;
    public int row;
    public int column;
    @Getter @Setter
    @EqualsAndHashCode.Exclude boolean isPath;
    @Getter @Setter
    boolean isRoot;
    @Getter @Setter
    boolean isDest;

    public Tile(int row, int column, GridTopology archetype, int weight) {
        this.row = row;
        this.column = column;
        this.archetype = archetype;
        type = TileType.Pavement;
        this.weight = weight;
        isPath = false;
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
        return String.format("%s - (row: %d, col: %d) weight: %d", type, row, column, weight);
    }

}
