package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraStrategyTest {

    @Test
    void findPathTest1() {
        Grid grid = new Grid(3, 3);
        grid.getMatrix()[0][0].setType(TileType.Wall);
        grid.getMatrix()[1][1].setType(TileType.Wall);
        grid.getMatrix()[0][2].setType(TileType.Pavement);
        grid.getMatrix()[1][0].setType(TileType.Pavement);
        grid.getMatrix()[1][2].setType(TileType.Pavement);
        grid.getMatrix()[2][0].setType(TileType.Pavement);
        grid.getMatrix()[2][1].setType(TileType.Pavement);
        grid.getMatrix()[2][2].setType(TileType.Pavement);
        List<Tile> path = new DijkstraStrategy().findPath(grid, grid.getMatrix()[0][1], grid.getMatrix()[2][0]);
        assertTrue(path.contains(grid.getMatrix()[0][1]));
        assertTrue(path.contains(grid.getMatrix()[0][2]));
        assertTrue(path.contains(grid.getMatrix()[1][2]));
        assertTrue(path.contains(grid.getMatrix()[2][2]));
        assertTrue(path.contains(grid.getMatrix()[2][1]));
        assertTrue(path.contains(grid.getMatrix()[2][0]));
    }

    @Test
    void findPathTest2() {
        Grid grid = new Grid(3, 3);
        grid.getMatrix()[0][0].setType(TileType.Wall);
        grid.getMatrix()[1][1].setType(TileType.Wall);
        grid.getMatrix()[0][2].setType(TileType.Pavement);
        grid.getMatrix()[1][0].setType(TileType.Pavement);
        grid.getMatrix()[1][2].setType(TileType.Pavement);
        grid.getMatrix()[2][0].setType(TileType.Pavement);
        grid.getMatrix()[2][1].setType(TileType.Pavement);
        grid.getMatrix()[2][2].setType(TileType.Wall);
        List<Tile> path = new DijkstraStrategy().findPath(grid, grid.getMatrix()[0][1], grid.getMatrix()[2][0]);
        assertTrue(path.isEmpty());
    }
}