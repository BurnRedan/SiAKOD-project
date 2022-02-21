package com.vsu.view;

import com.vsu.model.Grid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GridView implements PropertyChangeListener {

    Grid grid;
    TileView root;
    TileView target;

    public GridView(Grid grid) {
        root = null;
        this.grid = grid;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
