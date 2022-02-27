package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class SwampPassagePolicy extends PassagePolicy {

    public SwampPassagePolicy() {
        destinationType = TileType.Swamp;
    }

    @Override
    public Integer getWeight(Tile source, Tile destination) {
        return 0;
    }
}
