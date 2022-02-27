package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public class PavementPassagePolicy extends PassagePolicy {

    public PavementPassagePolicy() {
        destinationType = TileType.Pavement;
    }

    @Override
    public Integer getWeight(Tile source, Tile destination) {
        if (source.getType() == TileType.Pavement) {
            return DEFAULT_WEIGHT;
        }
        //TODO: add biomes
        return 0;
    }
}
