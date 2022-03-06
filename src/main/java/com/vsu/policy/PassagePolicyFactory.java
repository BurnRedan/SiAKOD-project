package com.vsu.policy;

import com.vsu.model.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PassagePolicyFactory {

    private final List<PassagePolicy> policies;

    public PassagePolicyFactory() {
        policies = new ArrayList<>();
        policies.add(new LakePassagePolicy());
        policies.add(new WallPassagePolicy());
        policies.add(new SwampPassagePolicy());
        policies.add(new ForestPassagePolicy());
        policies.add(new PavementPassagePolicy());
    }

    public Integer getDistance(Tile source, Tile destination) {
        return Objects.requireNonNull(
                policies.stream()
                        .filter(p -> destination.getType().equals(p.destinationType))
                        .findFirst().orElse(null)
        ).getDistance(source, destination);
    }
}
