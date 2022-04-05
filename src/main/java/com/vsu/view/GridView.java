package com.vsu.view;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        //matrix = new TileView[getGrid().getRowSize()][grid.getColSize()];
    }

    public void setGrid() {
        matrix = new TileView[grid.getRowSize()][grid.getColSize()];
    }



}
