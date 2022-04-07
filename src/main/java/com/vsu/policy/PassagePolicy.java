package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class PassagePolicy {

    public Integer getDistance(Tile source, Tile destination) {
        int weight = source.getWeight() + destination.getWeight();

        if (destination.getType() == TileType.Pavement) {
            return weight;
        }

        if (destination.getType() == TileType.Room) {
            return weight + 1;
        }

        if (destination.getType() == TileType.Forest) {
            return weight + 5;
        }

        if (destination.getType() == TileType.Lake) {
            return weight * 2;
        }

        if (destination.getType() == TileType.Swamp) {
            return weight * 5;
        }

        return Integer.MAX_VALUE;
    }
}
