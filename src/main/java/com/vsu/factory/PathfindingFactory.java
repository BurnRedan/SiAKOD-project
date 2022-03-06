package com.vsu.factory;

import com.vsu.pathfinder.AStarStrategy;
import com.vsu.pathfinder.DijkstraStrategy;
import com.vsu.pathfinder.PathfindingAlgorithms;
import com.vsu.pathfinder.PathfindingStrategy;

import java.util.ArrayList;
import java.util.List;

public class PathfindingFactory {
    List<PathfindingStrategy> strategies;

    public PathfindingFactory() {
        strategies = new ArrayList<>();
        strategies.add(new DijkstraStrategy());
        strategies.add(new AStarStrategy());
    }

    public PathfindingStrategy getStrategy(PathfindingAlgorithms algorithm) {
        return strategies.stream().filter(s -> algorithm.equals(s.getAlgorithm())).findFirst().orElse(null);
    }
}
