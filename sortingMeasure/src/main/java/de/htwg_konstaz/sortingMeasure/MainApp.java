package de.htwg_konstaz.sortingMeasure;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstaz.ui.main.MainWindowController;

public class MainApp extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        LOGGER.info("Starting MeasureAlgorithms JavaFX and Maven demonstration application");


        MainWindowController mainWindowController = new MainWindowController("/fxml/MainWindow.fxml");
        Parent rootNode = (Parent) mainWindowController.getContentNode();
        Scene scene = new Scene(rootNode);
        stage.setTitle("JavaFX MeasureAlgorithms");
        stage.setScene(scene);
        stage.show();
    }
}
