package com.vsu.service;

import com.vsu.AI.Entity;
import com.vsu.maze_generation.MazeGenerationStrategy;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.service.grid.GridTopology;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.vsu.maze_generation.dungeon.Direction2D.*;

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
                grid.getMatrix()[x][y].setEntity(null);
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

    public void genMazeByLayout(Grid grid, HashSet<Position> layout) {
        int i = 0;
        for (Position pos : layout) {
            i++;
            grid.getMatrix()[pos.row][pos.col].setType(TileType.Pavement);
        }
    }

    public static List<Tile> getNonWallTiles(Grid grid){
        List<Tile> nonWallTiles = new ArrayList<>();
        for (Tile[] matrix :grid.getMatrix()) {
            for (Tile tile : matrix) {
                if (tile != null && tile.getEntity() == null
                        && tile.getType() != TileType.Wall) {
                    nonWallTiles.add(tile);
                }
            }
        }
        return nonWallTiles;
    }

    public List<Tile> getNeighbours(Grid grid, Tile tile) {
        Tile tmp;
        List<Tile> neighbours = new ArrayList<>();

        tmp = tile.north(grid);
        if (tmp != null) {
            neighbours.add(tmp);
        }
        tmp = tile.south(grid);
        if (tmp != null) {
            neighbours.add(tmp);
        }
        tmp = tile.west(grid);
        if (tmp != null) {
            neighbours.add(tmp);
        }
        tmp = tile.east(grid);
        if (tmp != null) {
            neighbours.add(tmp);
        }

        return neighbours;
    }
}
