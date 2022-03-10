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
        Grid grid = new Grid();
        GridService gridService = new GridService();
        gridService.initGrid(grid, 4, 4, new PlaneTopology());

        assertEquals(grid.getMatrix()[1][1].south(grid), grid.getMatrix()[2][1]);
        assertNull(grid.getMatrix()[3][1].south(grid));
        assertEquals(grid.getMatrix()[2][3].south(grid), grid.getMatrix()[3][3]);
    }

    @Test
    void west() {
        Grid grid = new Grid();
        GridService gridService = new GridService();
        gridService.initGrid(grid, 4, 4, new PlaneTopology());

        assertEquals(grid.getMatrix()[1][1].west(grid), grid.getMatrix()[1][0]);
        assertNull(grid.getMatrix()[0][0].west(grid));
        assertEquals(grid.getMatrix()[3][2].west(grid), grid.getMatrix()[3][1]);
    }

    @Test
    void east() {
        Grid grid = new Grid();
        GridService gridService = new GridService();
        gridService.initGrid(grid, 4, 4, new PlaneTopology());

        assertEquals(grid.getMatrix()[1][1].east(grid), grid.getMatrix()[1][2]);
        assertNull(grid.getMatrix()[0][3].east(grid));
        assertEquals(grid.getMatrix()[3][2].east(grid), grid.getMatrix()[3][3]);
    }
}