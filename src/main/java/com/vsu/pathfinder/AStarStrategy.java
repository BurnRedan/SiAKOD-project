package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import java.util.List;

public class AStarStrategy extends PathfindingStrategy {

    PathfindingAlgorithms algorithm;

    public AStarStrategy() {
        algorithm =  PathfindingAlgorithms.AStar;
    }

    @Override
    public int findPath(Grid grid, List<Tile> path) {
        return 0;
    }
}
