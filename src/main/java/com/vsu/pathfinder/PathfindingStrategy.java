package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import java.util.List;

public interface PathfindingStrategy {
    public int findPath(Grid grid, List<Tile> path);
}
