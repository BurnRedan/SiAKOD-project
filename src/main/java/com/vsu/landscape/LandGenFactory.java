package com.vsu.landscape;

import java.util.ArrayList;
import java.util.List;

public class LandGenFactory {
    List<LandGenStrategy> strategies;

    public LandGenFactory() {
        strategies = new ArrayList<>();
        strategies.add(new RandomLandGenStrategy());
    }

    public LandGenStrategy getStrategy(LandGenType type) {
        return strategies.stream().filter(s -> type.equals(s.getType())).findFirst().orElse(null);
    }

}
