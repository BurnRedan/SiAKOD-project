package com.vsu.view;

import com.vsu.factory.MazeGenerationFactory;
import com.vsu.maze_generation.MazeGenAlgorithm;
import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.Grid;
import com.vsu.service.GridService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewController {
    GridView model;
    View view;

    public ViewController(GridView model, View view) {
        this.model = model;
        this.view = view;
        this.view.createGrid();
        view.setTriggers(this);
    }

    public void generateMaze(MazeGenAlgorithm algorithm, Grid grid) {
        MazeGenerationFactory mazeGenerationFactory = new MazeGenerationFactory();
        MazeGenerationStrategy strategy = mazeGenerationFactory.getMazeStrategy(algorithm);
        GridService gridService = new GridService();
        gridService.generateMaze(grid, strategy);
    }

    public void clearGrid(Grid grid) {
        GridService gridService = new GridService();
        gridService.clearGrid(grid);
    }

    public void clearGrid(GridView model) {
        GridService gridService = new GridService();
        gridService.clearGrid(model.getGrid());
    }


}
