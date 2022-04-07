package com.vsu.maze_generation;

import java.util.ArrayList;
import java.util.List;

public class MazeGenerationFactory {

    List<MazeGenerationStrategy> strategies;

    public MazeGenerationFactory() {
        strategies = new ArrayList<>();
        strategies.add(new BacktrackingStrategy());
        strategies.add(new RandomWalkStrategy());
    }

    public MazeGenerationStrategy getStrategy(MazeGenAlgorithms strategy) {
        return strategies.stream().filter(s -> strategy.equals(s.getType())).findFirst().orElse(null);
    }
}
