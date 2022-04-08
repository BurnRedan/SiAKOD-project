package com.vsu.view;

import com.vsu.AI.Entity;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GridView {

    Grid grid;
    TileView root;
    TileView target;
    TileView[][] matrix;
    List<Entity> seekers;
    Entity runner;
    @Getter @Setter
    boolean isMazeGenerated;

    public void addSeeker(Entity entity) {
        seekers.add(entity);
    }

    public void removeSeeker(Entity entity) {
        seekers.remove(entity);
    }

    public void clearSeekers() {
        seekers = new ArrayList<>();
    }

    public void setRunner(Entity runner) {
        if (this.runner != null) {
            this.runner.getTile().setEntity(null);
        }
        this.runner = runner;
    }

    public void deleteRunner() {
        runner = null;
    }


    public GridView(Grid grid) {
        root = null;
        this.grid = grid;
        seekers = new ArrayList<>();
        isMazeGenerated = false;
        runner = null;
    }

    public void setGrid() {
        matrix = new TileView[grid.getRowSize()][grid.getColSize()];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TileView[] tileViews : matrix) {
            for (TileView tileView : tileViews) {
                sb.append(tileView.getColor() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
