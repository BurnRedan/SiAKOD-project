package com.vsu.view;

import com.vsu.model.Tile;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class TileView {
    Tile tile;
    int size;
    Color color;
    Rectangle rectangle;
    StackPane stackPane;

    public TileView(Tile tile, int size) {
        this.tile = tile;
        this.size = size;

        color = ViewConfig.getInstance().getTypeColorMap().get(tile.getType());
        rectangle = new Rectangle(size - ViewConfig.getInstance().getTileGap(),
                size - ViewConfig.getInstance().getTileGap());
        rectangle.setFill(ViewConfig.getInstance().getTypeColorMap().get(tile.getType()));
        setTileStroke(false);

        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.setTranslateX(tile.i * size);
        stackPane.setTranslateY(tile.j * size);
    }

    public void setTileStroke(boolean setStroke) {
        rectangle.setStroke(setStroke ? Color.LIGHTGRAY : null);
    }
}
