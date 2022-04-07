package com.vsu.maze_generation.dungeon.parameters;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
@Builder
public class DungeonGenParameters {
    int iterations, walkLength;
    boolean startRandomlyEachIteration;
    boolean randomWalkRooms;
    int minRoomWidth, minRoomHeight;
    int offset;
}
