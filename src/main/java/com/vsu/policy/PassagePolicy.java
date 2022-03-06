package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

public abstract class PassagePolicy {

    protected TileType destinationType;

    public abstract Integer getDistance(Tile source, Tile destination);
}
