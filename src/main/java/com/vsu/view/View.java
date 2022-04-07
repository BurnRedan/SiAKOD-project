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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {


    int WIDTH = 1380;
    int HEIGHT = 700;

    GridView model;
    @Getter
    Scene scene;

    int padding = 2;
    int defaultRowCount = 69;
    int defaultColCount = 109;
    int defaultTileSize = 10;
    double leftPanelSize = 0.2;

    VBox leftPane;
    Pane parentGridPane;
    Pane gridPane;

    Button genMazeButton;
    Button clearButton;
    Button findPathButton;

    CheckBox setTileStrokeCheckBox;

    ComboBox<MazeGenAlgorithms> mazeGenAlgorithmsComboBox;
    ComboBox<PathfindingAlgorithms> pathfindingAlgorithmsComboBox;
    ComboBox<TileViewType> tileViewTypeComboBox;

    ViewController viewController;

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

        setTileStrokeCheckBox = new CheckBox("Tile border");
        setTileStrokeCheckBox.setSelected(false);

        genMazeButton = new Button("Generate maze");
        clearButton = new Button("Clear");
        findPathButton = new Button("Find path");

        Label tileTypeLabel = new Label("Tile picker");
        tileViewTypeComboBox = new ComboBox<>((FXCollections.observableArrayList(TileViewType.Source, TileViewType.Destination)));
        tileViewTypeComboBox.getSelectionModel().selectFirst();
        Label mazeGenLabel = new Label("Maze generation algorithm");
        mazeGenAlgorithmsComboBox = new ComboBox<>(FXCollections.observableArrayList(MazeGenAlgorithms.values()));
        mazeGenAlgorithmsComboBox.getSelectionModel().selectFirst();
        Label pathfindingLabel = new Label("Pathfinding algorithm");
        pathfindingAlgorithmsComboBox = new ComboBox<>(FXCollections.observableArrayList(PathfindingAlgorithms.values()));
        pathfindingAlgorithmsComboBox.getSelectionModel().selectFirst();

        setTileStrokeCheckBox = new CheckBox("Tile border");
        setTileStrokeCheckBox.setSelected(false);

        leftPane.getChildren().addAll(createPane, setTileStrokeCheckBox, genMazeButton, clearButton,
                mazeGenLabel, mazeGenAlgorithmsComboBox, pathfindingLabel, pathfindingAlgorithmsComboBox, findPathButton,
                tileTypeLabel, tileViewTypeComboBox);
        scene = new Scene(initComponents(), WIDTH, HEIGHT);

        viewController = new ViewController();
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

    public void setTriggers() {

        clearButton.setOnAction(event -> {
            viewController.clearGrid(model.getGrid());
            int size = defaultTileSize;
            initGridView(model.getGrid(), size);
            repaintGrid(model.getGrid(), size);
        });

        genMazeButton.setOnAction(event ->
                FXCollections.observableArrayList(MazeGenAlgorithms.values())
                .stream()
                .filter(item -> mazeGenAlgorithmsComboBox.getValue().toString().equals(item.toString()))
                .forEachOrdered(item -> {
                    if (gridPane != null) {
                        viewController.clearGrid(model.getGrid());
                        viewController.generateMaze(item, model.getGrid());
                        int size = defaultTileSize;
                        initGridView(model.getGrid(), size);
                        repaintGrid(model.getGrid(), size);
                    }
                }));

        findPathButton.setOnAction(event ->
                FXCollections.observableArrayList(PathfindingAlgorithms.values())
                .stream()
                .filter(item -> pathfindingAlgorithmsComboBox.getValue().toString().equals(item.toString()))
                .forEachOrdered(item -> {
                    if (gridPane != null) {
                        int size = defaultTileSize;
                        resetGrid(model);
                        repaintGrid(model.getGrid(), size);
                        viewController.getPath(
                                        item,
                                        model.getGrid(),
                                        model.getPathSource().getTile(),
                                        model.getPathDestination().getTile()
                                );
                        repaintGrid(model.getGrid(), size);
                    }
                }));
    }

    public void createGrid() {
        int rowCount = defaultRowCount;
        rowCount = rowCount % 2 == 0 ? rowCount + 1 : rowCount;
        int colCount = defaultColCount;
        colCount = colCount % 2 == 0 ? colCount + 1 : colCount;
        int size = defaultTileSize;
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
                            ViewConfig.getINSTANCE().getTileViewTypeColorMap().get(TileViewType.Source), this, model);
                } else if (tile.isDest()) {
                    tileView = new TileView(tile, tileSize,
                            ViewConfig.getINSTANCE().getTileViewTypeColorMap().get(TileViewType.Destination), this, model);
                } else if (tile.isPath()) {
                    tileView = new TileView(tile, tileSize,
                            ViewConfig.getINSTANCE().getTileViewTypeColorMap().get(TileViewType.Path), this, model);
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

    private void repaintGrid(Grid grid, int size) {
        parentGridPane.getChildren().remove(gridPane);
        gridPane = new Pane();
        boolean setEnabled = setTileStrokeCheckBox.isSelected();

        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {

                TileView tileView = model.getMatrix()[i][j];
                Tile tile = grid.getMatrix()[i][j];
                tileView.setTile(tile, size);

                if (tile.isPath() && !model.getPathSource().getTile().equals(tile) &&
                        !model.getPathDestination().getTile().equals(tile)) {

                    model.getMatrix()[i][j]
                            .setColor(ViewConfig.getINSTANCE().getTileViewTypeColorMap().get(TileViewType.Path));

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
                if (!tile.getTile().equals(model.getPathSource().getTile()) &&
                        !tile.getTile().equals(model.getPathDestination().getTile())) {
                    tile.setColor(ViewConfig.getINSTANCE().getTileTypeColorMap().get(tile.getTile().getType()));
                    tile.getTile().setPath(false);
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int size = defaultTileSize;
        repaintGrid(model.getGrid(), size);
    }
}
