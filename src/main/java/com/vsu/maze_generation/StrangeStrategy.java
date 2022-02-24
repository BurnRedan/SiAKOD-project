package com.vsu.maze_generation;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.state.GridService;
import lombok.Getter;

import java.util.*;

@Getter
public class StrangeStrategy extends MazeGenerationStrategy {

    public StrangeStrategy() {
        type = MazeGenAlgorithm.Strange;
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
            addNeighbours(grid, current, neighbours);
            neighbours.removeIf(visited::contains);
            if (neighbours.isEmpty()) {
                current = stack.pop();
                continue;
            }

            //Tile randomNeighbour = neighbours.get((int)(Math.random() * (neighbours.size())));
            Tile randomNeighbour = neighbours.get(getRandomInt(neighbours.size(), 0));
            removeWallBetween(grid, current, randomNeighbour);

            current = randomNeighbour;
            visited.add(randomNeighbour);
            stack.push(randomNeighbour);
        }
    }



    protected void addNeighbours(Grid grid, Tile tile, List<Tile> neighbours) {

        neighbours.clear();
        Tile tmp;

        tmp = tile.north(grid);
        if(tmp != null) {
            neighbours.add(tmp.column % 2 != 0 ? grid.getMatrix()[tmp.row][tmp.column - 1] : tmp);
        }

        tmp = tile.south(grid);
        if(tmp != null) {
            neighbours.add(tmp.column % 2 != 0 ? grid.getMatrix()[tmp.row][tmp.column + 1] : tmp);
        }

        tmp = tile.west(grid);
        if(tmp != null) {
            neighbours.add(tmp.row % 2 != 0 ? grid.getMatrix()[tmp.row - 1][tmp.column] : tmp);
        }

        tmp = tile.east(grid);
        if(tmp != null) {
            neighbours.add(tmp.row % 2 != 0 ? grid.getMatrix()[tmp.row + 1][tmp.column] : tmp);
        }
    }

    protected void removeWallBetween(Grid grid, Tile a, Tile b) {
        int x = a.row;
        int y = a.column;

        if (a.row < b.row) {
            x++;
        } else if (a.column < b.column) {
            y++;
        } else if (a.row > b.row) {
            x--;
        } else if (a.column > b.column) {
            y--;
        }

        grid.getMatrix()[x][y].setType(TileType.Empty);
    }

    protected int getRandomInt(int max, int min)
    {
        Random random = new Random();
        return ((int) (Math.random()*(max - min))) + min;
    }

}
