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
    final Rectangle rectangle;
    final StackPane stackPane;
    final PropertyChangeSupport support;
    final View view;
    final GridView gridView;
    boolean isClicked;

    public TileView(Tile tile, int size, View view, GridView gridView) {
        support = new PropertyChangeSupport(this);
        this.tile = tile;
        this.size = size;
        this.view = view;
        this.gridView = gridView;

        color = ViewConfig.getINSTANCE().getTileTypeColorMap().get(tile.getType());
        rectangle = new Rectangle(size - ViewConfig.getINSTANCE().getTileGap(),
                size - ViewConfig.getINSTANCE().getTileGap());
        rectangle.setFill(ViewConfig.getINSTANCE().getTileTypeColorMap().get(tile.getType()));
        setTileStroke(false);

        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.setTranslateY(tile.row * size);
        stackPane.setTranslateX(tile.col * size);

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

        rectangle = new Rectangle(size - ViewConfig.getINSTANCE().getTileGap(),
                size - ViewConfig.getINSTANCE().getTileGap());
        rectangle.setFill(ViewConfig.getINSTANCE().getTileTypeColorMap().get(tile.getType()));
        setTileStroke(false);

        stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        stackPane.setTranslateY(tile.row * size);
        stackPane.setTranslateX(tile.col * size);

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
            isClicked = true;
            String tilePickerChoice = view.tileViewTypeComboBox.getValue().toString();
            if (tilePickerChoice.equals(TileViewType.Source.toString())) {
                if (tile.getType() == TileType.Wall) {
                    return;
                }
                if (!(gridView.getRoot() == null)) {
                    gridView.getRoot().setColorEvent(ViewConfig.getINSTANCE()
                            .getTileTypeColorMap().get(tile.getType()));
                }
                gridView.setRoot(this);
                setColorEvent(ViewConfig.getINSTANCE().getTileViewTypeColorMap().get(TileViewType.Source));
            } else if (tilePickerChoice.equals(TileViewType.Destination.toString())) {
                if (tile.getType() == TileType.Wall) {
                    return;
                }
                if (!(gridView.getTarget() == null)) {
                    gridView.getTarget().setColorEvent(ViewConfig.getINSTANCE()
                            .getTileViewTypeColorMap().get(TileViewType.Ordinary));
                }
                gridView.setTarget(this);
                setColorEvent(ViewConfig.getINSTANCE().getTileViewTypeColorMap().get(TileViewType.Destination));
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
        rectangle.resize(size - ViewConfig.getINSTANCE().getTileGap(),
                size - ViewConfig.getINSTANCE().getTileGap());
        stackPane.setTranslateY(tile.row * size);
        stackPane.setTranslateX(tile.col * size);
        if (!isClicked) {
            rectangle.setFill(ViewConfig.getINSTANCE().getTileTypeColorMap().get(tile.getType()));
        }
    }

}
