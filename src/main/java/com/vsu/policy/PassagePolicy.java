package com.vsu.policy;

import com.vsu.model.Tile;
import com.vsu.model.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: upgrade policies
public abstract class PassagePolicy {

    private final List<PassagePolicy> policies;
    protected TileType destinationType;
    protected final int DEFAULT_WEIGHT = 1;

    public PassagePolicy() {
        policies = new ArrayList<>();
        policies.add(new LakePassagePolicy());
        policies.add(new WallPassagePolicy());
        policies.add(new SwampPassagePolicy());
        policies.add(new ForestPassagePolicy());
        policies.add(new PavementPassagePolicy());
    }

    public Integer getWeight(Tile source, Tile destination) {
        return Objects.requireNonNull(
                policies.stream()
                        .filter(p -> destination.getType().equals(p.destinationType))
                        .findFirst().orElse(null)
        ).getWeight(source, destination);
    }
}
