package com.vsu.maze_generation.dungeon;

import lombok.ToString;

import static com.vsu.maze_generation.dungeon.Direction2D.*;

@ToString
public class BoundingBox {
    int width;
    int height;
    Position startPoint;
    Position center;
    Position max;

    public BoundingBox(int width, int height, Position startPoint) {
        this.width = width;
        this.height = height;
        this.startPoint = startPoint;
        center = new Position(height / 2 + startPoint.row, width / 2 + startPoint.col);
        max = new Position(startPoint.row + height, startPoint.col + width);
    }
}
