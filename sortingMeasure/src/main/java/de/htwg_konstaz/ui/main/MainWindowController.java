package de.htwg_konstaz.ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstaz.ui.configuration.ConfigurationController;
import de.htwg_konstaz.ui.result.ResultController;

public class MainWindowController implements IController{
	
	protected static  final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);
	
	/**
	 * @param fxmlName the name of the file, e.g. MainWindow.fxml
	 * @throws IOException when the file is loadable
	 */
	public MainWindowController(String fxmlName) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			LOGGER.error("Error while loading MainWindow", e);
			throw e;
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
    private Label status; // Value injected by FXMLLoader

    @FXML
    void newTabWithProfilingConfig(ActionEvent event) throws IOException {
//    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tab.fxml"));
//    	Tab tab = fxmlLoader.load();
//    	TabController controller = fxmlLoader.getController();
//    	controller.setMainWindowController(this);
    	Tab tab = new Tab("new");
//    	TabController tabController = new TabController();
//		Accordion loadResultContent = tabController.loadResultContent();
//    	AnchorPane value = new AnchorPane(loadResultContent);
		ConfigurationController configController = new ConfigurationController(tab);
		tab.setContent(configController.getContentNode());
    	tabPane.getTabs().add(tab); 
    	
//    	AnchorPane.setTopAnchor(loadResultContent, 0.0);
//    	AnchorPane.setRightAnchor(loadResultContent, 0.0);
//    	AnchorPane.setLeftAnchor(loadResultContent, 0.0);
//    	AnchorPane.setBottomAnchor(loadResultContent, 0.0);
    
//    	value.autosize();
//    	loadResultContent.autosize();
    }

    @FXML
    void openAboutMyApp(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        
        
    }

	@Override
	public Node getContentNode() {
		
		return rootVBox;
	}
}
