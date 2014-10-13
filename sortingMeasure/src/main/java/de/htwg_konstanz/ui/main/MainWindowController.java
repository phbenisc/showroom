package de.htwg_konstanz.ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstanz.ui.configuration.ConfigurationController;

public class MainWindowController extends Application implements IController {
	
	protected static  final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);
	
	public MainWindowController() {
		this("/fxml/MainWindow.fxml");
	}
	
	/**
	 * @param fxmlName the name of the file, e.g. MainWindow.fxml
	 * @throws IOException when the file is loadable
	 */
	public MainWindowController(String fxmlName){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			LOGGER.error("Error while loading MainWindow", e);
		}
	}
	@FXML
	private VBox rootVBox;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader

    @FXML
    private ProgressBar progress;

    @FXML
    void newTabWithProfilingConfig(ActionEvent event){
    	if(event != null)
    		event.consume();
    	
    	Tab tab = new Tab("new");

		ConfigurationController configController = ControlerAndWindowFactory.getInstance().getNewConfigurationController(tab);
		tab.setContent(configController.getContentNode());
    	tabPane.getTabs().add(tab); 
    }

    @FXML
    void openAboutMyApp(ActionEvent event) {
    	new AboutMyApp(getHostServices());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        progress.visibleProperty().set(false);       
        
        //load always one tab
        newTabWithProfilingConfig(null);
    }

	@Override
	public Node getContentNode() {		
		return rootVBox;
	}
	
	/**
	 * will only be called if the JVM doesn't know that this is an javafx application
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception {    	
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        LOGGER.info("Starting MeasureAlgorithms JavaFX application");
        Parent rootNode = (Parent) this.getContentNode();
        Scene scene = new Scene(rootNode);
        stage.setTitle("JavaFX MeasureAlgorithms");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
