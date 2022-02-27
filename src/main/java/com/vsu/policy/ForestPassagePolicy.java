package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class ForestPassagePolicy extends PassagePolicy {

    public ForestPassagePolicy() {
        destinationType = TileType.Forest;
    }

    @Override
    public Integer getWeight(Tile source, Tile destination) {
        return 0;
    }
}
