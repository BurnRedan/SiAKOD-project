package com.vsu.model;

import javafx.geometry.Pos;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Random;

public class Direction2D {

    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Position {
        public static Position zero = new Position(0, 0);
        public static Position up = new Position(-1, 0);
        public static Position down = new Position(1, 0);
        public static Position left = new Position(0, -1);
        public static Position right = new Position(0, 1);

        public int row, col;

        public Position add(int val)  {
            return new Position(this.row + val, this.col + val);
        }

        public Position add(Position other) {
            return new Position(this.row + other.row, this.col + other.col);
        }

        public Position subtract(int val) {
            return new Position(this.row - val, this.col - val);
        }

        public static int distance(Position p1, Position p2) {
            return (p1.row - p2.row) * (p1.row - p2.row) + (p1.col - p2.col) * (p1.col - p2.col);
        }
    }

    @Getter
    static List<Position> cardinalDirectionList = List.of(
            new Position(0, 1),
            new Position(1, 0),
            new Position(0, -1),
            new Position(-1, 0)
    );

    public static Position getRandomDirection() {
        return cardinalDirectionList.get(new Random().nextInt(0, cardinalDirectionList.size()));
        
    }

}
