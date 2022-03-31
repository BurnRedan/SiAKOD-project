package com.vsu.maze_generation.dungeon;

import com.vsu.model.Direction2D;

import java.util.*;

import static com.vsu.model.Direction2D.*;

public class ProceduralGenAlgorithms {

    //TODO: почему то может вернуть пустой список
    public static List<BoundingBox> bsp(BoundingBox spaceToSplit, int minWidth, int minHeight) {
        Queue<BoundingBox> roomsQueue = new LinkedList<>();
        List<BoundingBox> roomsList = new ArrayList<>();
        roomsQueue.add(spaceToSplit);

        while (roomsQueue.size() > 0) {
            BoundingBox room = roomsQueue.poll();
            if (room.height < minHeight || room.width < minWidth)
                continue;

            if (new Random().nextDouble() < 0.5) {
                if (room.height >= minHeight * 2)
                    splitHorizontally(roomsQueue, room);
                else if (room.width >= minWidth * 2)
                    splitVertically(roomsQueue, room);
                else
                    roomsList.add(room);
            } else {
                if (room.height >= minHeight * 2)
                    splitVertically(roomsQueue, room);
                else if (room.width >= minWidth * 2)
                    splitHorizontally(roomsQueue, room);
                else
                    roomsList.add(room);
            }
        }

        return roomsList;
    }

    //TODO: test
    private static void splitVertically(Queue<BoundingBox> roomsQueue, BoundingBox room) {
        int xSplit = new Random().nextInt(1, room.width);
        BoundingBox room1 = new BoundingBox(xSplit, room.height, room.startPoint);
        BoundingBox room2 = new BoundingBox(room.width - xSplit, room.height,
                new Position(room.startPoint.row, room.startPoint.col + xSplit));
        roomsQueue.add(room1);
        roomsQueue.add(room2);
    }

    private static void splitHorizontally(Queue<BoundingBox> roomsQueue, BoundingBox room) {
        int ySplit = new Random().nextInt(1, room.height);
        BoundingBox room1 = new BoundingBox(room.width, ySplit, room.startPoint);
        BoundingBox room2 = new BoundingBox(room.width, room.height - ySplit,
                new Position(room.startPoint.row + ySplit, room.startPoint.col));
        roomsQueue.add(room1);
        roomsQueue.add(room2);
    }

}
