package com.vsu.pathfinder;

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
