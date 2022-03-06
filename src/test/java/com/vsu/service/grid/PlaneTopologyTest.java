package com.vsu.service.grid;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.service.GridService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTopologyTest {

    //TODO
    @Test
    void north() {
        Grid grid = new Grid();
        GridService gridService = new GridService();
        gridService.initGrid(grid, 4, 4, new PlaneTopology());
        assertEquals(grid.getMatrix()[1][1].north(grid), grid.getMatrix()[0][1]);
        assertNull(grid.getMatrix()[0][1].north(grid));
        assertEquals(grid.getMatrix()[3][3].north(grid), grid.getMatrix()[2][3]);
    }

    @Test
    void south() {
    }

    @Test
    void west() {
    }

    @Test
    void east() {
        Grid grid = new Grid();
        GridService gridService = new GridService();
        gridService.initGrid(grid, 4, 4, new PlaneTopology());

        assertEquals(grid.getMatrix()[1][1].east(grid), grid.getMatrix()[1][2]);
        assertNull(grid.getMatrix()[0][3].east(grid));
        Tile tmp = grid.getMatrix()[3][2].east(grid);
        assertEquals(tmp, grid.getMatrix()[3][3]);
    }
}