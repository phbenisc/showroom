package de.htwg_konstaz.sortingMeasure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstaz.ui.main.MainWindowController;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

//        String fxmlFile = "/fxml/hello.fxml";
//        log.debug("Loading FXML for main view from: {}", fxmlFile);
//        FXMLLoader loader = new FXMLLoader();
//        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        MainWindowController mainWindowController = new MainWindowController("/fxml/MainWindow.fxml");
        Parent rootNode = (Parent) mainWindowController.getContentNode();
//        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);
//        scene.getStylesheets().add("/styles/styles.css");
//
        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
}
