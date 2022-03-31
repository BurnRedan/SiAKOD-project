package com.vsu.view;

import com.vsu.model.TileType;
import javafx.scene.paint.Color;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

//TODO: review
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ViewConfig {

    @Getter
    private static final ViewConfig instance = new ViewConfig();

    final Map<TileType, Color> tileTypeColorMap;
    final Map<TileViewType, Color> tileViewTypeColorMap;
    double tileGap;

    private ViewConfig() {
        tileTypeColorMap = new HashMap<>();
        tileTypeColorMap.put(TileType.Forest, Color.GREENYELLOW);
        tileTypeColorMap.put(TileType.Swamp, Color.DARKGREEN);
        tileTypeColorMap.put(TileType.Pavement, Color.WHITE);
        tileTypeColorMap.put(TileType.Wall, Color.BLACK);
        tileTypeColorMap.put(TileType.Lake, Color.ALICEBLUE);
        tileTypeColorMap.put(TileType.Room, Color.PALEGOLDENROD);

        tileViewTypeColorMap = new HashMap<>();
        tileViewTypeColorMap.put(TileViewType.Ordinary, tileTypeColorMap.get(TileType.Pavement));
        tileViewTypeColorMap.put(TileViewType.Root, Color.OLIVE);
        tileViewTypeColorMap.put(TileViewType.Path, Color.BISQUE);
        tileViewTypeColorMap.put(TileViewType.Dest, Color.SALMON);
    }
}
