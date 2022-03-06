package com.vsu.model;

import com.vsu.service.grid.PlaneTopology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Test
    public void equalsTest1() {
        Tile tile1 = new Tile(5, 5, new PlaneTopology(), 5);
        Tile tile2 = new Tile(5, 5, new PlaneTopology(), 5);
        assertEquals(tile1, tile2);
    }

    @Test
    public void equalsTest2() {
        Tile tile1 = new Tile(5, 6, new PlaneTopology(), 5);
        Tile tile2 = new Tile(5, 5, new PlaneTopology(), 5);
        assertNotEquals(tile1, tile2);
    }
}