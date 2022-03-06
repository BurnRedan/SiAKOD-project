package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import java.util.*;

public class AStarStrategy extends PathfindingStrategy {

    public AStarStrategy() {
        algorithm =  PathfindingAlgorithms.AStar;
    }

    @Override
    public List<Tile> findPath(Grid grid, Tile source, Tile destination) {
        return null;
    }
}
