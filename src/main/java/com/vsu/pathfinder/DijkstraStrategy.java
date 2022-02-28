package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import java.util.List;

public class DijkstraStrategy extends PathfindingStrategy {

    PathfindingAlgorithms algorithm;

    public DijkstraStrategy() {
        algorithm =  PathfindingAlgorithms.Dijkstra;
    }

    @Override
    public int findPath(Grid grid, List<Tile> path) {
        return 0;
    }
}
