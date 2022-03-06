package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class LakePassagePolicy extends PassagePolicy {

    public LakePassagePolicy() {
        destinationType = TileType.Lake;
    }

    @Override
    public Integer getDistance(Tile source, Tile destination) {
        return 0;
    }
}
