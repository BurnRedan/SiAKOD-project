package com.vsu.maze_generation;

import com.vsu.model.Grid;
import lombok.Getter;

@Getter
public abstract class MazeGenerationStrategy {

    MazeGenAlgorithm type;


//    public void generate(Grid grid) {
//        GridService gridService = new GridService();
//        gridService.clearGrid(grid);
//        gridService.fillWithWalls(grid);
//        algorithm(grid);
//    }

    public abstract void generate(Grid grid);

    //protected abstract void algorithm(Grid grid);

}
