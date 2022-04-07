package com.vsu.maze_generation;

import com.vsu.model.Grid;
import lombok.Getter;

@Getter
public abstract class MazeGenerationStrategy {

    MazeGenAlgorithms type;

    public abstract void generate(Grid grid);

}
