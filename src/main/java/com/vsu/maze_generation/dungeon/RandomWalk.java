package com.vsu.maze_generation.dungeon;

import com.vsu.maze_generation.dungeon.parameters.Parameters;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.service.GenerationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Random;

import static com.vsu.model.Direction2D.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class RandomWalk {

    Parameters parameters;
    Position startPos;
    int rowCount;
    int colCount;

    public RandomWalk(Position startPos, int rowCount, int colCount, Parameters parameters) {
        this.startPos = startPos;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.parameters = parameters;
    }



    public HashSet<Position> runRandomWalk() {
        Position curPos = startPos;
        HashSet<Position> floor = new HashSet<>();
        for (int i = 0; i < parameters.iterations; i++) {
            assert curPos != null;
            HashSet<Position> path = runRandomWalkIteration(curPos);
            floor.addAll(path);
            if (parameters.startRandomlyEachIteration)
                curPos = getRandomItemFromSet(floor);
        }

        return floor;
    }

    private <T> T getRandomItemFromSet(HashSet<T> set) {
        int idx = new Random().nextInt(set.size());
        int i = 0;
        for (T item : set) {
            if (i == idx)
                return item;
            i++;
        }
        return null;
    }

    public HashSet<Position> runRandomWalkIteration(Position pos) {
        HashSet<Position> path = new HashSet<>();
        Position prevPos = new Position(pos.row, pos.col);
        path.add(prevPos);
        for (int i = 0; i < parameters.walkLength; i++) {
            Position newPos = GenerationService.getVector2ByDirection(prevPos, getRandomDirection(), rowCount, colCount);
            path.add(newPos);
            prevPos.row = newPos.row;
            prevPos.col = newPos.col;
        }
        return path;
    }


    //TODO: возможно удалить
    public HashSet<Tile> runRandomWalk(Grid grid) {
        Tile curTile = grid.getMatrix()[startPos.row][startPos.col];
        HashSet<Tile> floor = new HashSet<>();
        for (int i = 0; i < parameters.iterations; i++) {
            assert curTile != null;
            HashSet<Tile> path = runRandomWalkIteration(grid, new Position(curTile.row, curTile.column));
            floor.addAll(path);
            if (parameters.startRandomlyEachIteration)
                curTile = getRandomItemFromSet(floor);
        }

        return floor;
    }

    //TODO: возможно удалить
    public HashSet<Tile> runRandomWalkIteration(Grid grid, Position pos) {
        HashSet<Tile> path = new HashSet<>();
        Tile prevTile = grid.getMatrix()[pos.row][pos.col];
        path.add(prevTile);
        for (int i = 0; i < parameters.walkLength; i++) {
            Tile newTile = GenerationService.getTileByDirection(grid, prevTile, getRandomDirection());
            newTile.setType(TileType.Pavement);
            path.add(newTile);
            prevTile = newTile;
        }
        return path;
    }
}
