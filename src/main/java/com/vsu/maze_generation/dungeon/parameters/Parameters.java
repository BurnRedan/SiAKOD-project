package com.vsu.maze_generation.dungeon.parameters;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class Parameters {
    int iterations, walkLength;
    boolean startRandomlyEachIteration;
    boolean randomWalkRooms;
    int minRoomWidth, minRoomHeight;
    int offset;
}
