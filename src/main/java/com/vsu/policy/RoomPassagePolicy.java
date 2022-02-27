package com.vsu.policy;

import com.vsu.model.Tile;

public class RoomPassagePolicy extends PassagePolicy{

    @Override
    public Integer getWeight(Tile source, Tile destination) {
        return 0;
    }


}
