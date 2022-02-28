package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import java.util.List;

public abstract class PathfindingStrategy {
    public abstract int findPath(Grid grid, List<Tile> path);
}
