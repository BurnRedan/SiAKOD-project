package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class WallPassagePolicy extends PassagePolicy {

    public WallPassagePolicy() {
        destinationType = TileType.Wall;
    }

    @Override
    public Integer getDistance(Tile source, Tile destination) {
        //TODO: can change to returning some value, if pawn has some powers to break walls
        return Integer.MAX_VALUE;
    }
}
