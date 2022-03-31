package com.vsu.maze_generation;

import com.vsu.maze_generation.dungeon.RoomGenerator;
import com.vsu.maze_generation.dungeon.parameters.BigDungeonParameters;
import com.vsu.maze_generation.dungeon.parameters.PlainRoomsParameters;
import com.vsu.model.Grid;
import com.vsu.service.GridService;

import java.util.HashSet;

import static com.vsu.model.Direction2D.*;

public class RandomWalkStrategy extends MazeGenerationStrategy {

    public RandomWalkStrategy() {
        type = MazeGenAlgorithms.RandomWalk;
    }

    @Override
    public void generate(Grid grid) {
        GridService gridService = new GridService();
        gridService.fillWithWalls(grid);
        //TODO: добавлять параметры из вью
        HashSet<Position> layout = new RoomGenerator(new Position(0, 0),
                grid.getRowSize() - 1, grid.getColSize() - 1, new BigDungeonParameters()).createRooms();
        gridService.genMazeByLayout(grid, layout);
    }
}
