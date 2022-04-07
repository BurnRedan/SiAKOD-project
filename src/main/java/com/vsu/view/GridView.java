package com.vsu.view;

import com.vsu.model.Grid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GridView {

    Grid grid;
    TileView root;
    TileView target;
    TileView[][] matrix;

    public GridView(Grid grid) {
        root = null;
        this.grid = grid;
    }

    public void setGrid() {
        matrix = new TileView[grid.getRowSize()][grid.getColSize()];
    }



}
