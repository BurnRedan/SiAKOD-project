package com.vsu.service;

import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.service.grid.GridTopology;

import java.util.concurrent.ThreadLocalRandom;

public class GridService {

    public void initGrid(Grid grid, int rowCount, int columnCount, GridTopology archetype) {
        archetype.initGrid(grid, rowCount, columnCount);
        //setRooms(grid, 4);
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


    //TODO
    public void setRooms(Grid grid, int count) {
        for (int room = 0; room < count; room++) {
            int row = ThreadLocalRandom.current().nextInt(0, grid.getRowSize() - 2);
            int col = ThreadLocalRandom.current().nextInt(0, grid.getColSize() - 2);
            float c = ThreadLocalRandom.current().nextFloat(0.05f, 0.1f);
            int s = (int) ((row + 1) * (col + 1) * c);
            c = ThreadLocalRandom.current().nextFloat(0.1f, 0.15f);
            int h = (int) (c * s) + 1;
            int w = s / h;
            int i = row, j = col;
            int iEnd = i + h, jEnd = col + w;
            if (iEnd >= grid.getRowSize()) {
                iEnd = grid.getRowSize() - 2;
            }
            if (jEnd >= grid.getColSize()) {
                jEnd = grid.getColSize() - 2;
            }
            Tile[][] m = grid.getMatrix();
            while (m[i][j] != null && i < iEnd) {
                while (m[i][j] != null && j < jEnd) {
                    m[i][j].setType(TileType.Room);
                    j++;
                }
                i++;
                j = col;
            }
        }
    }
}
