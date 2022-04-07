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

    public void fillWithPavements(Grid grid) {
        for (int y = 0; y < grid.getColSize(); y++) {
            for (int x = 0; x < grid.getRowSize(); x++) {
                grid.getMatrix()[x][y].setType(TileType.Pavement);
                grid.getMatrix()[x][y].setRoot(false);
                grid.getMatrix()[x][y].setDest(false);
                grid.getMatrix()[x][y].setPath(false);
            }
        }
    }

    public void fillWithWalls(Grid grid) {
        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                Tile tile = grid.getMatrix()[i][j];
                if (tile.getType() == TileType.Room) {
                    continue;
                }
                tile.setType(TileType.Wall);
            }
        }
    }
}
