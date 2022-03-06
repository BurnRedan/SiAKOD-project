package com.vsu.service;

import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.service.grid.GridTopology;

public class GridService {

    public void initGrid(Grid grid, int rowCount, int columnCount, GridTopology archetype) {
        archetype.initGrid(grid, rowCount, columnCount);
    }

    public void generateMaze(Grid grid, MazeGenerationStrategy strategy) {
        strategy.generate(grid);
    }

    public void clearGrid(Grid grid) {
        for (int y = 0; y < grid.getColSize(); y++) {
            for (int x = 0; x < grid.getRowSize(); x++) {
                grid.getMatrix()[x][y].setType(TileType.Pavement);
            }
        }
    }

    public void fillWithWalls(Grid grid) {
        for (int y = 0; y < grid.getColSize(); y++) {
            for (int x = 0; x < grid.getRowSize(); x++) {
                grid.getMatrix()[x][y].setType(TileType.Wall);
            }
        }
    }
}
