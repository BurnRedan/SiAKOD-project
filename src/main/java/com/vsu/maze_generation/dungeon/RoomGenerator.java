package com.vsu.maze_generation.dungeon;

import com.vsu.maze_generation.dungeon.parameters.DungeonGenParameters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static com.vsu.maze_generation.dungeon.Direction2D.*;

public class RoomGenerator extends RandomWalk {

    int dungeonWidth, dungeonHeight;

    public RoomGenerator(Position startPos, int rowCount, int colCount, DungeonGenParameters parameters) {
        super(startPos, rowCount, colCount, parameters);
        dungeonWidth = colCount;
        dungeonHeight = rowCount;
        this.parameters = parameters;
    }

    public HashSet<Position> createRooms() {
        List<BoundingBox> roomsList = new Bsp().runBsp(new BoundingBox(dungeonWidth, dungeonHeight, startPos),
                parameters.minRoomWidth, parameters.minRoomHeight);
        HashSet<Position> floor = parameters.randomWalkRooms ?
                createRoomsRandomly(roomsList) : createSimpleRooms(roomsList);

        List<Position> roomsCenters = new ArrayList<>();
        for (BoundingBox room : roomsList) {
            roomsCenters.add(room.center);
        }

        HashSet<Position> corridors = connectRooms(roomsCenters);
        floor.addAll(corridors);

        return floor;
    }

    private HashSet<Position> createRoomsRandomly(List<BoundingBox> roomsList) {
        HashSet<Position> floor = new HashSet<>();
        for (BoundingBox bound : roomsList) {
            Position roomCenter = new Position(bound.center.row, bound.center.col);
            RandomWalk randomWalk = new RandomWalk(roomCenter, rowCount, colCount, parameters);
            HashSet<Position> roomFloor = randomWalk.runRandomWalk();
            for (Position pos : roomFloor) {
                if (pos.col > (bound.startPoint.add(parameters.offset)).col
                        && pos.col <= (bound.max.subtract(parameters.offset)).col &&
                        pos.row > (bound.startPoint.add(parameters.offset).row)
                        && pos.row <= (bound.max.subtract(parameters.offset)).row)
                {
                    floor.add(pos);
                }
            }
        }

        return floor;
    }

    private HashSet<Position> createSimpleRooms(List<BoundingBox> roomsList) {

        HashSet<Position> floor = new HashSet<>();

        for (BoundingBox room : roomsList) {
            for (int col = parameters.offset; col <= room.width - parameters.offset; col++) {
                for (int row = parameters.offset; row <= room.height - parameters.offset; row++) {
                    Position pos = room.startPoint.add(new Position(row, col));
                    floor.add(pos);
                }
            }
        }

        return floor;
    }

    private HashSet<Position> connectRooms(List<Position> roomsCenters) {

        HashSet<Position> corridors = new HashSet<>();
        Position curRoomCenter = roomsCenters.get(new Random().nextInt(0, roomsCenters.size()));
        roomsCenters.remove(curRoomCenter);

        while (roomsCenters.size() > 0) {
            Position closest = findClosestPointTo(curRoomCenter, roomsCenters);
            roomsCenters.remove(closest);
            HashSet<Position> newCorridor = createCorridor(curRoomCenter, closest);
            curRoomCenter.row = closest.row;
            curRoomCenter.col = closest.col;
            corridors.addAll(newCorridor);
        }

        return corridors;
    }

    private Position findClosestPointTo(Position curRoomCenter, List<Position> roomsCenters) {
        Position closest = Position.zero;
        int distance = Integer.MAX_VALUE;

        for (Position pos : roomsCenters) {
            int currentDistance = Position.distance(pos, curRoomCenter);
            if (currentDistance < distance) {
                distance = currentDistance;
                closest = pos;
            }
        }

        return closest;
    }

    private HashSet<Position> createCorridor(Position curRoomCenter, Position destination) {
        HashSet<Position> corridor = new HashSet<>();
        Position pos = curRoomCenter;
        corridor.add(pos);

        while (pos.row != destination.row) {
            if (destination.row > pos.row) {
                pos = pos.add(Position.down);
                if (pos.row < 0)
                    pos.row = 0;
            } else {
                pos = pos.add(Position.up);
                if (pos.row >= rowCount)
                    pos.row = rowCount - 1;
            }

            corridor.add(pos);
        }

        while (pos.col != destination.col) {
            if (destination.col > pos.col) {
                pos = pos.add(Position.right);
                if (pos.col >= colCount)
                    pos.col = colCount - 1;
            } else {
                pos = pos.add(Position.left);
                if (pos.col < 0)
                    pos.col = 0;
            }

            corridor.add(pos);
        }

        return corridor;
    }

}
