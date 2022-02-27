package com.vsu.service.grid;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

public class PlaneTopology implements GridTopology {
    @Override
    public Tile north(Tile source, Grid grid) {
        return source.row - 1 < 0 ? null : grid.getMatrix()[source.row - 1][source.column];
    }

    @Override
    public Tile south(Tile source, Grid grid) {
        return source.row + 1 >= grid.getMatrix().length - 1 ? null : grid.getMatrix()[source.row + 1][source.column];
    }

    @Override
    public Tile west(Tile source, Grid grid) {
        return source.column - 1 < 0 ? null : grid.getMatrix()[source.row][source.column - 1];
    }

    @Override
    public Tile east(Tile source, Grid grid) {
        return source.column + 1 >= grid.getMatrix()[0].length - 1 ? null : grid.getMatrix()[source.row][source.column + 1];
    }

    @Override
    public void initGrid(Grid grid, int rowCount, int columnCount) {
        grid.setMatrix(rowCount, columnCount);
        grid.setXSize(rowCount);
        grid.setYSize(columnCount);

        for (int y = 0; y < grid.getYSize(); y++) {
            for (int x = 0; x < grid.getXSize(); x++) {
                grid.getMatrix()[x][y] = new Tile(x, y, new PlaneTopology());
            }
        }
    }
}
