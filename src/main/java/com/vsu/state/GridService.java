package com.vsu.state;

import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class GridService {

    public void initGrid(Grid grid, int xSize, int ySize) {
        grid.setMatrix(xSize, ySize);
        grid.setXSize(xSize);
        grid.setYSize(ySize);

        for (int y = 0; y < grid.getYSize(); y++) {
            for (int x = 0; x < grid.getXSize(); x++) {
                grid.getMatrix()[x][y] = new Tile(x, y);
            }
        }
    }

    public void generateMaze(Grid grid, MazeGenerationStrategy strategy) {
        strategy.generate(grid);
    }

    public void clearGrid(Grid grid) {
        for (int y = 0; y < grid.getYSize(); y++) {
            for (int x = 0; x < grid.getXSize(); x++) {
                grid.getMatrix()[x][y].setType(TileType.Empty);
            }
        }
    }

    public void fillWithWalls(Grid grid) {
        for (int y = 0; y < grid.getYSize(); y++) {
            for (int x = 0; x < grid.getXSize(); x++) {
                grid.getMatrix()[x][y].setType(TileType.Wall);
                //TODO: replace with default weight
                grid.getMatrix()[x][y].setWeight(1);
            }
        }
    }

}
