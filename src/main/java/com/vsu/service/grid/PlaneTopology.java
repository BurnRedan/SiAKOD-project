package com.vsu.service.grid;

import com.vsu.landscape.LandGenFactory;
import com.vsu.landscape.LandGenStrategy;
import com.vsu.landscape.LandGenType;
import com.vsu.model.Grid;
import com.vsu.model.Tile;

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
        //TODO: remove random land gen strategy with actual land gen
        LandGenStrategy landGenStrategy = new LandGenFactory().getStrategy(LandGenType.Random);
        int[][] landscape = landGenStrategy.getLandscape(rowCount, columnCount);
        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                int weight = landscape[i][j];
                grid.getMatrix()[i][j] = new Tile(i, j, new PlaneTopology(), weight);
            }
        }
    }
}
