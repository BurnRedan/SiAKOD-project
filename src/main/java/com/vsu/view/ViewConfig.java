package com.vsu.view;

import com.vsu.model.TileType;
import javafx.scene.paint.Color;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class ViewConfig {

    @Getter
    private static final ViewConfig INSTANCE = new ViewConfig(0);

    Map<TileType, Color> tileTypeColorMap;
    Map<TileViewType, Color> tileViewTypeColorMap;
    double tileGap;

    private ViewConfig(int tileGap) {
        tileTypeColorMap = new HashMap<>();
        tileTypeColorMap.put(TileType.Forest, Color.GREENYELLOW);
        tileTypeColorMap.put(TileType.Swamp, Color.DARKGREEN);
        tileTypeColorMap.put(TileType.Pavement, Color.WHITE);
        tileTypeColorMap.put(TileType.Wall, Color.BLACK);
        tileTypeColorMap.put(TileType.Lake, Color.ALICEBLUE);
        tileTypeColorMap.put(TileType.Room, Color.PALEGOLDENROD);

        tileViewTypeColorMap = new HashMap<>();
        tileViewTypeColorMap.put(TileViewType.Ordinary, tileTypeColorMap.get(TileType.Pavement));
        tileViewTypeColorMap.put(TileViewType.Source, Color.OLIVE);
        tileViewTypeColorMap.put(TileViewType.Path, Color.BISQUE);
        tileViewTypeColorMap.put(TileViewType.Destination, Color.SALMON);

        this.tileGap = tileGap;
    }
}
