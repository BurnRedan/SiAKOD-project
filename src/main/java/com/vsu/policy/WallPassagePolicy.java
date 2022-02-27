package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class WallPassagePolicy extends PassagePolicy {

    public WallPassagePolicy() {
        destinationType = TileType.Wall;
    }

    @Override
    public Integer getWeight(Tile source, Tile destination) {
        //TODO: can change to returning some value, if pawn has some powers to break walls
        return null;
    }
}
