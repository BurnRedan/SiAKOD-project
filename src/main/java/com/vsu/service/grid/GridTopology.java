package com.vsu.service.grid;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

public interface GridTopology {

    public Tile north(Tile source, Grid grid);
    public Tile south(Tile source, Grid grid);
    public Tile west(Tile source, Grid grid);
    public Tile east(Tile source, Grid grid);

    public void initGrid(Grid grid, int rowCount, int columnCount);
}
