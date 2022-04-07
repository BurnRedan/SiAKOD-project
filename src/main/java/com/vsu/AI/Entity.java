package com.vsu.AI;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Entity {

    @Getter @Setter
    Tile tile;
    @Getter @Setter
    EntityType type;
    @Getter @Setter
    List<Tile> path;


    public Entity(EntityType type, Tile tile){
        this.type = type;
        this. tile = tile;
        path = new ArrayList<>();
    }



}
