package com.vsu.factory;

import com.vsu.maze_generation.*;

import java.util.ArrayList;
import java.util.List;

public class MazeGenerationFactory {

    List<MazeGenerationStrategy> strategies;

    public MazeGenerationFactory() {
        strategies = new ArrayList<>();
        strategies.add(new PrimStrategy());
        strategies.add(new KruskalStrategy());
        strategies.add(new BacktrackingStrategy());
    }

    public MazeGenerationStrategy getStrategy(MazeGenAlgorithms strategy) {
        return strategies.stream().filter(s -> strategy.equals(s.getType())).findFirst().orElse(null);
    }
}
