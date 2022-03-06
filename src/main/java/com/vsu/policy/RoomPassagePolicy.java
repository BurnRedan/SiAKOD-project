package com.vsu.policy;

import com.vsu.model.Tile;

public class RoomPassagePolicy extends PassagePolicy{

    @Override
    public Integer getDistance(Tile source, Tile destination) {
        return 0;
    }


}
