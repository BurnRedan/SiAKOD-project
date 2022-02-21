package com.vsu.pathfinder.heuristic;

import com.vsu.model.Tile;

public interface HeuristicStrategy {
    public double computeHeuristic(Tile root, Tile target);
}
