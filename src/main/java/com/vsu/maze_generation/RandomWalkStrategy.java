package com.vsu.maze_generation;

import com.vsu.maze_generation.dungeon.RoomGenerator;
import com.vsu.maze_generation.dungeon.parameters.DungeonGenParameters;
import com.vsu.model.Grid;
import com.vsu.service.GridService;

import java.util.HashSet;

import static com.vsu.maze_generation.dungeon.Direction2D.*;

public class RandomWalkStrategy extends MazeGenerationStrategy {

    public RandomWalkStrategy() {
        super(false);
        type = MazeGenAlgorithms.RandomWalk;
    }

    @Override
    public void generate(Grid grid) {
        GridService gridService = new GridService();
        gridService.fillWithWalls(grid);

        DungeonGenParameters parameters = DungeonGenParameters.builder()
                .randomWalkRooms(true)
                .minRoomWidth(13)
                .minRoomHeight(13)
                .startRandomlyEachIteration(true)
                .iterations(30)
                .offset(2)
                .walkLength(50)
                .build();

        HashSet<Position> layout = new RoomGenerator(new Position(0, 0),
                grid.getRowSize() - 1, grid.getColSize() - 1, parameters).createRooms();

        genMazeByLayout(grid, layout);
    }

    private void genMazeByLayout(Grid grid, HashSet<Position> layout) {
        int i = 0;
        for (Position pos : layout) {
            i++;
            super.setPathTileType(grid.getMatrix()[pos.row][pos.col]);
        }
    }
}
