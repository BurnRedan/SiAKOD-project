package com.vsu.view;

import com.vsu.model.Tile;
import com.vsu.model.TileType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class TileView {

    Tile tile;
    int size;
    public Color color;
    Rectangle rectangle;
    StackPane stackPane;
    PropertyChangeSupport support;
    View view;
    GridView gridView;
    boolean isClicked;

    public TileView(Tile tile, int size, View view, GridView gridView) {
        support = new PropertyChangeSupport(this);
        this.tile = tile;
        this.size = size;
        this.view = view;
        this.gridView = gridView;

        color = ViewConfig.getInstance().getTileTypeColorMap().get(tile.getType());
        rectangle = new Rectangle(size - ViewConfig.getInstance().getTileGap(),
                size - ViewConfig.getInstance().getTileGap());
        rectangle.setFill(ViewConfig.getInstance().getTileTypeColorMap().get(tile.getType()));
        setTileStroke(false);

        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.setTranslateY(tile.row * size);
        stackPane.setTranslateX(tile.column * size);

        isClicked = false;

        setEvents();
    }

    public TileView(Tile tile, int size, Color color, View view, GridView gridView) {
        support = new PropertyChangeSupport(this);
        this.tile = tile;
        this.size = size;
        this.color = color;
        this.view = view;
        this.gridView = gridView;

        rectangle = new Rectangle(size - ViewConfig.getInstance().getTileGap(),
                size - ViewConfig.getInstance().getTileGap());
        rectangle.setFill(ViewConfig.getInstance().getTileTypeColorMap().get(tile.getType()));
        setTileStroke(false);

        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.setTranslateY(tile.row * size);
        stackPane.setTranslateX(tile.column * size);

        setEvents();
    }

    public void setTileStroke(boolean setStroke) {
        rectangle.setStroke(setStroke ? Color.LIGHTGRAY : null);
    }

    public void addPropertyChangedListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private void setEvents() {
        stackPane.setOnMouseClicked(e -> {
            //TODO: maybe make fabric
            isClicked = true;
            String val = view.tileViewTypeComboBox.getValue().toString();
            if (val.equals(TileViewType.Ordinary.toString())) {
                setColorEvent(ViewConfig.getInstance().getTileTypeColorMap().get(TileType.Pavement));
            } else if (val.equals(TileViewType.Path.toString())) {
                setColorEvent(ViewConfig.getInstance().getTileViewTypeColorMap().get(TileViewType.Path));
            } else if (val.equals(TileViewType.Root.toString())) {
                if (!(gridView.getRoot() == null && tile.getType() != TileType.Wall)) {
                    gridView.getRoot().setColorEvent(ViewConfig.getInstance()
                            .getTileViewTypeColorMap().get(TileViewType.Ordinary));
                }
                gridView.setRoot(this);
                setColorEvent(ViewConfig.getInstance().getTileViewTypeColorMap().get(TileViewType.Root));
            } else if (val.equals(TileViewType.Dest.toString())) {
                if (!(gridView.getTarget() == null && tile.getType() != TileType.Wall)) {
                    gridView.getTarget().setColorEvent(ViewConfig.getInstance()
                            .getTileViewTypeColorMap().get(TileViewType.Ordinary));
                }
                gridView.setTarget(this);
                setColorEvent(ViewConfig.getInstance().getTileViewTypeColorMap().get(TileViewType.Dest));
            }
        });
    }

    public void setColorEvent(Color color) {
        support.firePropertyChange("color", this.color, color);
        this.color = color;
        rectangle.setFill(color);
    }

    public void setColor(Color color) {
        this.color = color;
        rectangle.setFill(color);
    }

    public void setTile(Tile tile, int size) {
        this.tile = tile;
        this.size = size;
        rectangle.resize(size - ViewConfig.getInstance().getTileGap(),
                size - ViewConfig.getInstance().getTileGap());
        stackPane.setTranslateY(tile.row * size);
        stackPane.setTranslateX(tile.column * size);
        if (!isClicked) {
            rectangle.setFill(ViewConfig.getInstance().getTileTypeColorMap().get(tile.getType()));
        }
    }

}
