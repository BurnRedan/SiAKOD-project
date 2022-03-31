package com.vsu.service;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import static com.vsu.model.Direction2D.*;

public class GenerationService {

    //TODO: возможно удалить
    public static Tile getTileByDirection(Grid grid, Tile tile, Position direction) {
        int row = tile.row + direction.row;
        int col = tile.column + direction.col;
        if (row < 0)
            row = 0;
        if (col < 0)
            col = 0;
        if (row >= grid.getRowSize())
            row--;
        if (col >= grid.getColSize())
            col--;
        return grid.getMatrix()[row][col];
    }


    //TODO: скорее всего лучше убрать данные грида и передавать сразу валидные данные,
    //TODO: то есть сделать функцию максимально тупой
    public static Position getVector2ByDirection(Position pos, Position direction, int rowCount, int colCount) {
        int row = pos.row + direction.row;
        int col = pos.col + direction.col;

        if (row < 0)
            row = 0;
        if (col < 0)
            col = 0;

        if (row >= rowCount)
            row--;
        if (col >= colCount)
            col--;

        return new Position(row, col);
    }
}
