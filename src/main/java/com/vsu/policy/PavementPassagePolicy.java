package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class PavementPassagePolicy extends PassagePolicy {

    public PavementPassagePolicy() {
        destinationType = TileType.Pavement;
    }

    @Override
    public Integer getDistance(Tile source, Tile destination) {
        if (source.getType() == TileType.Pavement) {
            return source.getWeight() + destination.getWeight();
        }
        //TODO: add biomes
        return 0;
    }
}
