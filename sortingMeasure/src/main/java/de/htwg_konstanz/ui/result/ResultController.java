package de.htwg_konstanz.ui.result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Accordion;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstanz.ui.configuration.ConfigurationModel;
import de.htwg_konstanz.ui.main.IController;
import de.htwg_konstanz.ui.main.TabController;

public class ResultController implements IController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ResultController.class);

	public ResultController(String fxmlName,TabController parrent,  ConfigurationModel model) {
		this.parrent = parrent;
		parrent.setTabName("Result");
		service = new AlgorithmMeasureService(model);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			LOGGER.error("Loading of file {} failed", e, fxmlName);
			e.printStackTrace();
		}
	}
	
	private TabController parrent;


//	public ResultController(ConfigurationModel model) {
//		this("/fxml/Result.fxml", model);
//	}


	private AlgorithmMeasureService service;
	
    @FXML
    private Accordion accordion;
	
    @FXML
    private StackPane stackPane;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Region region;

	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	// fx:id="comparisionChart"
	private LineChart<Number, Number> comparisionChart; // Value injected by FXMLLoader

	@FXML
	// fx:id="shiftChart"
	private LineChart<Number, Number> shiftChart; // Value injected by FXMLLoader

	@FXML
	// fx:id="computionTimeChart"
	private AreaChart<Number, Number> computionTimeChart; // Value injected by FXMLLoader


	@FXML
	// This method is called by the FXMLLoader when initialisation is complete
	void initialize() {
		parrent.status(service.progressProperty(), service.runningProperty());
		region.visibleProperty().bind(service.runningProperty());
		service.start();
				
		AnchorPane.setBottomAnchor(stackPane, 0.0);
		AnchorPane.setLeftAnchor(stackPane, 0.0);
		AnchorPane.setRightAnchor(stackPane, 0.0);
		AnchorPane.setTopAnchor(stackPane, 0.0);
		
		service.setOnSucceeded(e-> {
			Result value = service.getValue();
			comparisionChart.dataProperty().bind(value.getComparisions());
			computionTimeChart.dataProperty().bind(value.getComputionTimes());
			shiftChart.dataProperty().bind(value.getShifts());
		});
		anchorPane.autosize();
		stackPane.autosize();
	}



	@Override
	public Node getContentNode() {
		return anchorPane;
	}
}
