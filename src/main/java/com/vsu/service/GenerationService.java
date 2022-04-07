package com.vsu.service;

import com.vsu.model.Grid;
import com.vsu.model.Tile;

import static com.vsu.model.Direction2D.*;

public class GenerationService {
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
