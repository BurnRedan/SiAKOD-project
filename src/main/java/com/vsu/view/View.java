package com.vsu.view;

import com.vsu.AI.Entity;
import com.vsu.AI.EntityMoveLogic;
import com.vsu.AI.EntityType;
import com.vsu.maze_generation.MazeGenAlgorithms;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.pathfinder.PathfindingAlgorithms;
import com.vsu.service.GridService;
import com.vsu.service.grid.PlaneTopology;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lombok.Getter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class View implements PropertyChangeListener {


    int WIDTH = 1380;
    int HEIGHT = 700;

    GridView model;
    @Getter
    Scene scene;

    int padding = 2;
    int defaultRowCount = 30;
    int defaultColCount = 30;
    int defaultTileSize = 24;
    double leftPanelSize = 0.2;
    boolean update = false;

    VBox leftPane;
    Pane parentGridPane;
    Pane gridPane;

    Button genMazeButton;
    Button clearButton;
    Button createSeekerButton;
    Button createRunnerButton;
    Button enableEntityAI;
    Button findPathButton;

    CheckBox setTileStrokeCheckBox;

    ComboBox<MazeGenAlgorithms> mazeGenAlgorithmsComboBox;
    ComboBox<PathfindingAlgorithms> pathfindingAlgorithmsComboBox;
    ComboBox<TileViewType> tileViewTypeComboBox;

    Timeline timeLine;
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
        createSeekerButton = new Button("Create seeker");
        createRunnerButton = new Button("Create runner");
        enableEntityAI = new Button("Enable entity AI");
        findPathButton = new Button("Find path");

        Label tileTypeLabel = new Label("Tile picker");
        tileViewTypeComboBox = new ComboBox<>((FXCollections.observableArrayList(TileViewType.Source, TileViewType.Path)));
        tileViewTypeComboBox.getSelectionModel().selectFirst();
        Label mazeGenLabel = new Label("Maze generation algorithm");
        mazeGenAlgorithmsComboBox = new ComboBox<>(FXCollections.observableArrayList(MazeGenAlgorithms.values()));
        mazeGenAlgorithmsComboBox.getSelectionModel().selectFirst();
        Label pathfindingLabel = new Label("Pathfinding algorithm");
        pathfindingAlgorithmsComboBox = new ComboBox<>(FXCollections.observableArrayList(PathfindingAlgorithms.values()));
        pathfindingAlgorithmsComboBox.getSelectionModel().selectFirst();

        setTileStrokeCheckBox = new CheckBox("Tile border");
        setTileStrokeCheckBox.setSelected(false);

        leftPane.getChildren().addAll(createPane, setTileStrokeCheckBox, genMazeButton, clearButton, createSeekerButton, createRunnerButton,
                enableEntityAI, mazeGenLabel, mazeGenAlgorithmsComboBox, pathfindingLabel, pathfindingAlgorithmsComboBox, findPathButton,
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
            model.clearSeekers();
        });

        //TODO: упростить поиск подходящих клеток
        createSeekerButton.setOnAction(event -> {
            List<Tile> nonWallTiles = new ArrayList<>();
            for (Tile[] matrix : model.getGrid().getMatrix()) {
                for (Tile tile : matrix) {
                    if (tile != null && tile.getEntity() == null
                            && tile.getType() != TileType.Wall) {
                        nonWallTiles.add(tile);
                    }
                }
            }
            int size = defaultTileSize;
            Tile tile = nonWallTiles.get(new Random().nextInt(0, nonWallTiles.size()));
            Entity entity = new Entity(EntityType.Seeker, tile);
            tile.setEntity(entity);
            model.addSeeker(entity);
            initGridView(model.getGrid(), size);
            repaintGrid(model.getGrid(), size);

        });

        createSeekerButton.setOnAction(event -> {
            List<Tile> nonWallTiles = GridService.getNonWallTiles(model.getGrid());
            Tile tile = nonWallTiles.get(new Random().nextInt(0, nonWallTiles.size()));
            Entity entity = new Entity(EntityType.Seeker, tile);
            tile.setEntity(entity);
            model.addSeeker(entity);
            initGridView(model.getGrid(), defaultTileSize);
            repaintGrid(model.getGrid(), defaultTileSize);
        });

        createRunnerButton.setOnAction(event -> {
            List<Tile> nonWallTiles = GridService.getNonWallTiles(model.getGrid());
            Tile tile = nonWallTiles.get(new Random().nextInt(0, nonWallTiles.size()));
            Entity entity = new Entity(EntityType.Runner, tile);
            tile.setEntity(entity);
            model.setRunner(entity);
            initGridView(model.getGrid(), defaultTileSize);
            repaintGrid(model.getGrid(), defaultTileSize);

        });

        enableEntityAI.setOnAction(event -> {
            update = !update;
            for (Entity entity : model.getSeekers()) {
                EntityMoveLogic.findPath(model.getGrid(), entity,
                        model.getRunner(), PathfindingAlgorithms.Dijkstra);
            }
            timeLine = (timeLine == null) ? new Timeline(
                    new KeyFrame(Duration.millis(250),
                            evt -> {
                                for (Entity entity : model.getSeekers()) {
                                    EntityMoveLogic.seek(model.getGrid(), entity);
                                }
                                initGridView(model.getGrid(), defaultTileSize);
                                repaintGrid(model.getGrid(), defaultTileSize);
                            })) : timeLine;
            timeLine.setCycleCount(Timeline.INDEFINITE);
            if (update) {
                timeLine.play();
            } else {
                timeLine.stop();
            }
        });


        genMazeButton.setOnAction(event ->
                FXCollections.observableArrayList(MazeGenAlgorithms.values())
                        .stream()
                        .filter(item -> mazeGenAlgorithmsComboBox.getValue()
                                .toString()
                                .equals(item.toString()))
                        .forEachOrdered(item ->
                        {
                            if (gridPane != null) {
                                viewController.clearGrid(model.getGrid());
                                model.clearSeekers();
                                model.deleteRunner();
                                viewController.generateMaze(item, model.getGrid());
                                int size = defaultTileSize;
                                repaintGrid(model.getGrid(), size);
                            }
                        }));

        findPathButton.setOnAction(event ->
                FXCollections.observableArrayList(PathfindingAlgorithms.values())
                        .stream()
                        .filter(item -> pathfindingAlgorithmsComboBox.getValue()
                                .toString()
                                .equals(item.toString()))
                        .forEachOrdered(item ->
                        {
                            if (gridPane != null) {
                                int size = defaultTileSize;
                                resetGrid(model);
                                repaintGrid(model.getGrid(), size);
                                viewController
                                        .getPath(
                                                item,
                                                model.getGrid(),
                                                model.getRoot().getTile(),
                                                model.getTarget().getTile()
                                        );
                                repaintGrid(model.getGrid(), size);
                            }
                        }));
    }

    public void createGrid() {
        int rowCount = defaultRowCount;
        rowCount = rowCount % 2 == 0 ? rowCount - 1 : rowCount;
        int colCount = defaultColCount;
        colCount = colCount % 2 == 0 ? colCount - 1 : colCount;
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
                if (tile.getEntity() != null) {
                    if (tile.getEntity().getType().equals(EntityType.Seeker)) {
                        tileView = new TileView(tile, tileSize,
                                ViewConfig.getINSTANCE().getEntityTypeColorMap().get(EntityType.Seeker), this, model);
                    } else if (tile.getEntity().getType().equals(EntityType.Runner)) {
                        tileView = new TileView(tile, tileSize,
                                ViewConfig.getINSTANCE().getEntityTypeColorMap().get(EntityType.Runner), this, model);
                    }
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

                if (tile.isPath() && !model.getRoot().getTile().equals(tile) &&
                        !model.getTarget().getTile().equals(tile)) {

                    model.getMatrix()[i][j]
                            .setColor(ViewConfig.getINSTANCE().getTileViewTypeColorMap().get(TileViewType.Path));

                } else if (tile.getEntity() != null) {
                    if (tile.getEntity().getType().equals(EntityType.Seeker)) {

                        model.getMatrix()[i][j]
                                .setColorEvent(ViewConfig.getINSTANCE().getEntityTypeColorMap().get(EntityType.Seeker));

                    } else if (tile.getEntity().getType().equals(EntityType.Runner)) {

                        model.getMatrix()[i][j]
                                .setColorEvent(ViewConfig.getINSTANCE().getEntityTypeColorMap().get(EntityType.Runner));

                    }
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
                if (!tile.getTile().equals(model.getRoot().getTile()) &&
                        !tile.getTile().equals(model.getTarget().getTile())) {
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
