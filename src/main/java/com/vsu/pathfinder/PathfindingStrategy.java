package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class PathfindingStrategy {

    PathfindingAlgorithms algorithm;

    public abstract List<Tile> findPath(Grid grid, Tile source, Tile destination);
}
