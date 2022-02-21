package com.vsu.view;

import com.vsu.model.TileType;
import javafx.scene.paint.Color;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewConfig {

    @Getter
    static final ViewConfig instance = new ViewConfig();

    Map<TileType, Color> typeColorMap;
    double tileGap = 0;

    private ViewConfig() {
        typeColorMap = new HashMap<>();
        typeColorMap.put(TileType.Root, Color.YELLOW);
        typeColorMap.put(TileType.Target, Color.PURPLE);
        typeColorMap.put(TileType.Empty, Color.WHITE);
        typeColorMap.put(TileType.Wall, Color.BLACK);
        typeColorMap.put(TileType.Path, Color.DEEPPINK);
        typeColorMap.put(TileType.Visited, Color.LIGHTGREEN);
        typeColorMap.put(TileType.Highlight, Color.AZURE);
    }
}
