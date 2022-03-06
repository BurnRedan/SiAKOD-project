package com.vsu.view;

import com.vsu.factory.MazeGenerationFactory;
import com.vsu.factory.PathfindingFactory;
import com.vsu.maze_generation.MazeGenAlgorithms;
import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.pathfinder.PathfindingAlgorithms;
import com.vsu.service.GridService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewController {

    public ViewController(View view) {
        view.createGrid();
        view.setTriggers(this);
    }

    public void generateMaze(MazeGenAlgorithms algorithm, Grid grid) {
        MazeGenerationFactory mazeGenerationFactory = new MazeGenerationFactory();
        MazeGenerationStrategy strategy = mazeGenerationFactory.getStrategy(algorithm);
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


    public void getPath(PathfindingAlgorithms algorithm, Grid grid, Tile source, Tile dest) {
        for (Tile[] row : grid.getMatrix()) {
            for (Tile tile : row) {
                tile.setPath(false);
            }
        }
        List<Tile> path = new PathfindingFactory().getStrategy(algorithm).findPath(grid, source, dest);
        for (Tile tile : path) {
            tile.setPath(true);
        }
    }
}
