package com.vsu.service.grid;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import java.util.concurrent.ThreadLocalRandom;

public class PlaneTopology implements GridTopology {
    @Override
    public Tile north(Tile source, Grid grid) {
        return source.row - 1 < 0 ? null : grid.getMatrix()[source.row - 1][source.column];
    }

    @Override
    public Tile south(Tile source, Grid grid) {
        return source.row + 1 >= grid.getMatrix().length ? null : grid.getMatrix()[source.row + 1][source.column];
    }

    @Override
    public Tile west(Tile source, Grid grid) {
        return source.column - 1 < 0 ? null : grid.getMatrix()[source.row][source.column - 1];
    }

    @Override
    public Tile east(Tile source, Grid grid) {
        return source.column + 1 >= grid.getMatrix()[0].length ? null : grid.getMatrix()[source.row][source.column + 1];
    }

    @Override
    public void initGrid(Grid grid, int rowCount, int columnCount) {
        grid.setMatrix(rowCount, columnCount);
        grid.setRowSize(rowCount);
        grid.setColSize(columnCount);

        for (int y = 0; y < grid.getColSize(); y++) {
            for (int x = 0; x < grid.getRowSize(); x++) {
                //TODO: remove random weights after landscape generation
                int weight = ThreadLocalRandom.current().nextInt(1, 99);
                grid.getMatrix()[x][y] = new Tile(x, y, new PlaneTopology(), weight);
            }
        }
    }
}
