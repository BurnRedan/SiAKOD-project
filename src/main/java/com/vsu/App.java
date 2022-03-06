package com.vsu;

import com.vsu.model.Grid;
import com.vsu.view.GridView;
import com.vsu.view.View;
import com.vsu.view.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        View view = createApp();
        stage.setScene(view.getScene());
        stage.show();
    }

    private View createApp() {
        GridView grid = new GridView(new Grid());
        View view = new View(grid);
        ViewController controller = new ViewController(view);
        return view;
    }

    public static void main(String[] args) {
        launch();
    }
}