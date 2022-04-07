package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class PathfindingStrategy {

    PathfindingAlgorithms algorithm;

    public abstract List<Tile> findPath(Grid grid, Tile source, Tile destination);

    protected List<Tile> getNeighbours(Grid grid, Tile tile) {
        Tile tmp;
        List<Tile> neighbours = new ArrayList<>();

        tmp = tile.north(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }
        tmp = tile.south(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }
        tmp = tile.west(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }
        tmp = tile.east(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }

        return neighbours;
    }
}
