package com.vsu.model;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.vsu.model.Direction2D.*;
import static org.junit.jupiter.api.Assertions.*;

class Direction2DTest {
    @Test
    void addValTest(){
        Position position = new Position(1, 1);
        assertEquals(new Position(2, 2), position.add(1));
    }

    @Test
    void addPosTest(){
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 1);
        assertEquals(new Position(2, 2), position1.add(position2));
    }

    @Test
    void subtractTest(){
        Position position = new Position(2, 2);
        assertEquals(new Position(1, 1), position.subtract(1));
    }

    @Test
    void distanceTest(){
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 1);
        Position position3 = new Position(1, 1);
        Position position4 = new Position(1, 1);
        assertEquals(Position.distance(position1, position2), Position.distance(position3, position4));
    }
}