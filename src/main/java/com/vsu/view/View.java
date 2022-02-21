package com.vsu.view;

import com.vsu.maze_generation.MazeGenAlgorithm;
import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.state.GridService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;

public class View {

    int WIDTH = 1380;
    int HEIGHT = 720;

    GridView model;
    @Getter
    Scene scene;

    TextField txtXTiles;
    TextField txtYTiles;
    TextField txtTileSize;

    int padding = 2;
    String defaultXSize = "53";
    String defaultYSize = "35";
    String defaultTileSize = "20";
    double leftPanelSize = 0.2;

    VBox leftPane;
    Pane parentGridPane;
    Pane gridPane;

    Button genMazeButton;
    Button clearButton;
    Button createGridButton;

    CheckBox setTileStrokeCheckBox;

    ComboBox<MazeGenAlgorithm> comboBoxMazeGenAlgorithm;

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
        txtXTiles = new TextField(defaultXSize);
        createPane.add(txtXTiles, 1, 0);
        createPane.add(new Text("Y: "), 2, 0);
        txtYTiles = new TextField(defaultYSize);
        createPane.add(txtYTiles, 3, 0);
        createPane.add(new Text("Size: "), 4, 0);
        txtTileSize = new TextField(defaultTileSize);
        createPane.add(txtTileSize, 5, 0);

        setTileStrokeCheckBox = new CheckBox("Tile border");
        setTileStrokeCheckBox.setSelected(false);

        genMazeButton = new Button("Generate maze");
        clearButton = new Button("Clear");
        createGridButton = new Button("Create grid");

        comboBoxMazeGenAlgorithm = new ComboBox<>(FXCollections.observableArrayList(MazeGenAlgorithm.values()));
        comboBoxMazeGenAlgorithm.getSelectionModel().selectFirst();

        setTileStrokeCheckBox = new CheckBox("Tile border");
        setTileStrokeCheckBox.setSelected(false);

        leftPane.getChildren().addAll(createPane, setTileStrokeCheckBox, genMazeButton, clearButton,
                createGridButton, comboBoxMazeGenAlgorithm);
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
            fillViewGrid(model.getGrid(), Integer.parseInt(txtTileSize.getText()));
        });

        genMazeButton.setOnAction(event -> {
            FXCollections.observableArrayList(MazeGenAlgorithm.values())
                    .stream()
                    .filter(item -> comboBoxMazeGenAlgorithm.getValue().toString().equals(item.toString()))
                    .forEachOrdered(item -> {
                        if (gridPane != null) {
                            viewController.generateMaze(item, model.getGrid());
                            int size = Integer.parseInt(txtTileSize.getText());
                            fillViewGrid(model.getGrid(), size);
                        }
                    });
        });

        createGridButton.setOnAction(event -> {
            int x = Integer.parseInt(txtXTiles.getText());
            x = x % 2 == 0 ? x - 1 : x;
            int y = Integer.parseInt(txtYTiles.getText());
            y = y % 2 == 0 ? y - 1 : y;
            int size = Integer.parseInt(txtTileSize.getText());
            GridService gridService = new GridService();
            gridService.initGrid(model.getGrid(), x, y);
            fillViewGrid(model.getGrid(), size);
        });
    }

    public void createGrid() {
        createGridButton.fire();
    }

    private void fillViewGrid(Grid grid, int tileSize) {
        gridPane = new Pane();

        boolean setEnabled = setTileStrokeCheckBox.isSelected();

        for (Tile[] row : grid.getMatrix()) {
            for (Tile tile : row) {
                TileView tileView = new TileView(tile, tileSize);
                tileView.setTileStroke(setEnabled);
                gridPane.getChildren().add(tileView.getStackPane());
            }
        }
        parentGridPane.getChildren().add(gridPane);
    }
}
