package com.vsu.maze_generation;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.state.GridService;

import java.util.*;

public class BacktrackingStrategy extends MazeGenerationStrategy {

    public BacktrackingStrategy() {
        type = MazeGenAlgorithm.Backtracking;
    }

    @Override
    public void generate(Grid grid) {

        GridService gridService = new GridService();
        gridService.clearGrid(grid);
        gridService.fillWithWalls(grid);

        Stack<Tile> stack = new Stack<>();
        List<Tile> neighbours = new ArrayList<>();
        Set<Tile> visited = new HashSet<>();

        Tile current = grid.getMatrix()[0][0];
        stack.push(current);
        visited.add(current);

        while (!stack.isEmpty()) {
            getNeighbours(grid, current, neighbours);
            neighbours.removeIf(visited::contains);
            if (neighbours.isEmpty()) {
                current = stack.pop();
                continue;
            }

            Tile randomNeighbour = neighbours.get((int) (Math.random() * neighbours.size()));
            randomNeighbour.setType(TileType.Empty);

            removeWallBetween(grid, current, randomNeighbour);

            current = randomNeighbour;
            visited.add(randomNeighbour);
            stack.push(randomNeighbour);
        }
    }


    private void getNeighbours(Grid grid, Tile current, List<Tile> neighbours) {
        neighbours.clear();
        Tile tmp;

        tmp = current.north(grid);
        if (tmp != null) {
            neighbours.add(tmp.row % 2 != 0 ? grid.getMatrix()[tmp.row - 1][tmp.column] : tmp);
        }

        tmp = current.south(grid);
        if (tmp != null) {
            neighbours.add(tmp.row % 2 != 0 ? grid.getMatrix()[tmp.row + 1][tmp.column] : tmp);
        }

        tmp = current.west(grid);
        if (tmp != null) {
            neighbours.add(tmp.column % 2 != 0 ? grid.getMatrix()[tmp.row][tmp.column - 1] : tmp);
        }

        tmp = current.east(grid);
        if (tmp != null) {
            neighbours.add(tmp.column % 2 != 0 ? grid.getMatrix()[tmp.row][tmp.column + 1] : tmp);
        }
    }

    private void removeWallBetween(Grid grid, Tile a, Tile b) {
        int i = a.row;
        int j = a.column;

        if (a.row < b.row) {
            i++;
        } else if (a.column < b.column) {
            j++;
        } else if (a.row > b.row) {
            i--;
        } else if (a.column > b.column) {
            j--;
        }

        grid.getMatrix()[i][j].setType(TileType.Empty);
    }
}
