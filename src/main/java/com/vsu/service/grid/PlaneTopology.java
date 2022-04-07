package com.vsu.service.grid;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

public class PlaneTopology implements GridTopology {
    @Override
    public Tile north(Tile source, Grid grid) {
        return source.row - 1 < 0 ? null : grid.getMatrix()[source.row - 1][source.col];
    }

    @Override
    public Tile south(Tile source, Grid grid) {
        return source.row + 1 >= grid.getMatrix().length ? null : grid.getMatrix()[source.row + 1][source.col];
    }

    @Override
    public Tile west(Tile source, Grid grid) {
        return source.col - 1 < 0 ? null : grid.getMatrix()[source.row][source.col - 1];
    }

    @Override
    public Tile east(Tile source, Grid grid) {
        return source.col + 1 >= grid.getMatrix()[0].length ? null : grid.getMatrix()[source.row][source.col + 1];
    }

    @Override
    public void initGrid(Grid grid, int rowCount, int columnCount) {
        grid.setMatrix(rowCount, columnCount);
        grid.setRowSize(rowCount);
        grid.setColSize(columnCount);
        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                grid.getMatrix()[i][j] = new Tile(i, j, new PlaneTopology(), 0);
            }
        }
    }
}
