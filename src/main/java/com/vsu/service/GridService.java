package com.vsu.service;

import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.service.grid.GridTopology;

import java.util.HashSet;

import static com.vsu.model.Direction2D.*;

public class GridService {

    public void initGrid(Grid grid, int rowCount, int columnCount, GridTopology archetype) {
        archetype.initGrid(grid, rowCount, columnCount);
    }

    public void generateMaze(Grid grid, MazeGenerationStrategy strategy) {
        //TODO: делать генерацию не сразу, а сначала делать разметку, потом вызвать метод apply(List<Vector2>)
        strategy.generate(grid);
    }

    public void fillWithPavements(Grid grid) {
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

    //TODO: может вылететь с ошибкой из-за выхода за границы
    public void genMazeByLayout(Grid grid, HashSet<Position> layout) {
        int i = 0;
        for (Position pos : layout) {
            i++;
            grid.getMatrix()[pos.row][pos.col].setType(TileType.Pavement);
        }
    }
}
