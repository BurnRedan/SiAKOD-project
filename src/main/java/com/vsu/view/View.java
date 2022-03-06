package com.vsu.view;

import com.vsu.maze_generation.MazeGenAlgorithms;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.pathfinder.PathfindingAlgorithms;
import com.vsu.service.GridService;
import com.vsu.service.grid.PlaneTopology;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {

    int WIDTH = 1380;
    int HEIGHT = 700;

    GridView model;
    @Getter
    Scene scene;

    TextField txtXTiles;
    TextField txtYTiles;
    TextField txtTileSize;

    int padding = 2;
    String defaultRowSize = "35";
    String defaultColSize = "55";
    String defaultTileSize = "20";
    double leftPanelSize = 0.2;

    VBox leftPane;
    Pane parentGridPane;
    Pane gridPane;

    Button genMazeButton;
    Button clearButton;
    Button findPathButton;
    Button recreateGridButton;

    CheckBox setTileStrokeCheckBox;

    ComboBox<MazeGenAlgorithms> mazeGenAlgorithmsComboBox;
    ComboBox<PathfindingAlgorithms> pathfindingAlgorithmsComboBox;
    ComboBox<TileViewType> tileViewTypeComboBox;

    public View(GridView model) {
        this.model = model;
        parentGridPane = new Pane();
        gridPane = null;

        leftPane = new VBox();
        leftPane.setPadding(new Insets(padding, padding, padding, padding));
        leftPane.setSpacing(10);

        GridPane createPane = new GridPane();
        createPane.setHgap(5);
        createPane.setPadding(new Insets(padding, padding, padding, padding));
        createPane.add(new Text("X: "), 0, 0);
        txtXTiles = new TextField(defaultRowSize);
        createPane.add(txtXTiles, 1, 0);
        createPane.add(new Text("Y: "), 2, 0);
        txtYTiles = new TextField(defaultColSize);
        createPane.add(txtYTiles, 3, 0);
        createPane.add(new Text("Size: "), 4, 0);
        txtTileSize = new TextField(defaultTileSize);
        createPane.add(txtTileSize, 5, 0);

        setTileStrokeCheckBox = new CheckBox("Tile border");
        setTileStrokeCheckBox.setSelected(false);

        genMazeButton = new Button("Generate maze");
        clearButton = new Button("Clear");
        findPathButton = new Button("Find path");
        recreateGridButton = new Button("Recreate grid");

        Label tileTypeLabel = new Label("Tile picker");
        tileViewTypeComboBox = new ComboBox<>(FXCollections.observableArrayList(TileViewType.values()));
        tileViewTypeComboBox.getSelectionModel().selectFirst();
        Label mazeGenLabel = new Label("Maze generation algorithm");
        mazeGenAlgorithmsComboBox = new ComboBox<>(FXCollections.observableArrayList(MazeGenAlgorithms.values()));
        mazeGenAlgorithmsComboBox.getSelectionModel().selectFirst();
        Label pathfindingLabel = new Label("Pathfinding algorithm");
        pathfindingAlgorithmsComboBox = new ComboBox<>(FXCollections.observableArrayList(PathfindingAlgorithms.values()));
        pathfindingAlgorithmsComboBox.getSelectionModel().selectFirst();

        setTileStrokeCheckBox = new CheckBox("Tile border");
        setTileStrokeCheckBox.setSelected(false);

        leftPane.getChildren().addAll(createPane, setTileStrokeCheckBox, genMazeButton, clearButton, recreateGridButton,
                mazeGenLabel, mazeGenAlgorithmsComboBox, pathfindingLabel, pathfindingAlgorithmsComboBox, findPathButton,
                tileTypeLabel, tileViewTypeComboBox);
        scene = new Scene(initComponents(), WIDTH, HEIGHT);
    }

    private SplitPane initComponents() {
        VBox vBox = new VBox();
        vBox.getChildren().add(parentGridPane);
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(leftPane, vBox);

        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(leftPanelSize));
        leftPane.minWidthProperty().bind(splitPane.widthProperty().multiply(leftPanelSize));

        return splitPane;
    }

    public void setTriggers(ViewController viewController) {

        clearButton.setOnAction(event -> {
            viewController.clearGrid(model);
            int size = Integer.parseInt(txtTileSize.getText());
            repaintGrid(model.getGrid(), size);
        });

        genMazeButton.setOnAction(event -> {
            FXCollections.observableArrayList(MazeGenAlgorithms.values())
                    .stream()
                    .filter(item -> mazeGenAlgorithmsComboBox.getValue().toString().equals(item.toString()))
                    .forEachOrdered(item -> {
                        if (gridPane != null) {
                            viewController.generateMaze(item, model.getGrid());
                            int size = Integer.parseInt(txtTileSize.getText());
                            repaintGrid(model.getGrid(), size);
                        }
                    });
        });

        //TODO: debug double click
        findPathButton.setOnAction(event -> {
            FXCollections.observableArrayList(PathfindingAlgorithms.values())
                    .stream()
                    .filter(item -> pathfindingAlgorithmsComboBox.getValue().toString().equals(item.toString()))
                    .forEachOrdered(item -> {
                        if (gridPane != null) {
                            int size = Integer.parseInt(txtTileSize.getText());
                            resetGrid(model);
                            repaintGrid(model.getGrid(), size);
                            viewController
                                    .getPath(item, model.getGrid(), model.getRoot().getTile(), model.getTarget().getTile());
                            repaintGrid(model.getGrid(), size);
                        }
                    });
        });

        recreateGridButton.setOnAction(event -> {
            createGrid();
        });
    }

    public void createGrid() {
        int rowCount = Integer.parseInt(txtXTiles.getText());
        rowCount = rowCount % 2 == 0 ? rowCount - 1 : rowCount;
        int colCount = Integer.parseInt(txtYTiles.getText());
        colCount = colCount % 2 == 0 ? colCount - 1 : colCount;
        int size = Integer.parseInt(txtTileSize.getText());
        GridService gridService = new GridService();
        gridService.initGrid(model.getGrid(), rowCount, colCount, new PlaneTopology());
        initGridView(model.getGrid(), size);
    }

    private void initGridView(Grid grid, int tileSize) {

        gridPane = new Pane();
        model.setGrid();

        boolean setEnabled = setTileStrokeCheckBox.isSelected();

        int i = 0, j = 0;
        for (Tile[] row : grid.getMatrix()) {
            for (Tile tile : row) {

                TileView tileView;
                if (tile.isRoot()) {
                    tileView = new TileView(tile, tileSize,
                            ViewConfig.getInstance().getTileViewTypeColorMap().get(TileViewType.Root), this, model);
                } else if (tile.isDest()) {
                    tileView = new TileView(tile, tileSize,
                            ViewConfig.getInstance().getTileViewTypeColorMap().get(TileViewType.Dest), this, model);
                } else if (tile.isPath()) {
                    tileView = new TileView(tile, tileSize,
                            ViewConfig.getInstance().getTileViewTypeColorMap().get(TileViewType.Path), this, model);
                } else {
                    tileView = new TileView(tile, tileSize, this, model);
                }

                tileView.addPropertyChangedListener(this);
                tileView.setTileStroke(setEnabled);

                model.getMatrix()[i][j] = tileView;
                gridPane.getChildren().add(tileView.getStackPane());
                j++;
            }
            i++;
            j = 0;
        }
        parentGridPane.getChildren().add(gridPane);
    }

    //TODO: debug size change
    private void repaintGrid(Grid grid, int size) {
        parentGridPane.getChildren().remove(gridPane);
        gridPane = new Pane();
        boolean setEnabled = setTileStrokeCheckBox.isSelected();

        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {

                TileView tileView = model.getMatrix()[i][j];
                Tile tile = grid.getMatrix()[i][j];
                tileView.setTile(tile, size);

                if (tile.isPath() && !model.getRoot().getTile().equals(tile) &&
                        !model.getTarget().getTile().equals(tile)) {

                    model.getMatrix()[i][j]
                            .setColor(ViewConfig.getInstance().getTileViewTypeColorMap().get(TileViewType.Path));

                }
                model.getMatrix()[i][j].setTileStroke(setEnabled);
                gridPane.getChildren().add(model.getMatrix()[i][j].getStackPane());
            }
        }
        parentGridPane.getChildren().add(gridPane);
    }

    private void resetGrid(GridView model) {
        for (TileView[] row : model.getMatrix()) {
            for (TileView tile : row) {
                if (!tile.getTile().equals(model.getRoot().getTile()) && !tile.getTile().equals(model.getTarget().getTile())) {
                    tile.setColor(ViewConfig.getInstance().getTileTypeColorMap().get(tile.getTile().getType()));
                    tile.getTile().setPath(false);
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int size = Integer.parseInt(txtTileSize.getText());
        repaintGrid(model.getGrid(), size);
    }
}
