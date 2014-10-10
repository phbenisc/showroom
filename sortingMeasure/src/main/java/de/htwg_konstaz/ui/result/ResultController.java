package de.htwg_konstaz.ui.result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstaz.ui.configuration.ConfigurationModel;
import de.htwg_konstaz.ui.main.IController;
import uk.napierdevsoc.sortingAlgorithms.SortingManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Accordion;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class ResultController implements IController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ResultController.class);

	public ResultController(String fxmlName, ConfigurationModel model) {
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


	public ResultController(ConfigurationModel model) {
		this("/fxml/Result.fxml", model);
	}


	private AlgorithmMeasureService service;
	
    @FXML
    private Accordion accordion;
	
    @FXML
    private StackPane stackPane;

	@FXML
	private AnchorPane anchorPane;


    @FXML
    private ProgressIndicator progressIndicator;

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

//	public void setData(ConfigurationModel model) {
//		final Set<String> algorithms = model.getAlgorithms();
//		this.resultModel = new ResultModel(algorithms);
//		for (final String string : algorithms) {
//			this.comparisionChart.getData().add(createSeries(string, this.resultModel.getComparisions(string)));
//			this.shiftChart.getData().add(createSeries(string, this.resultModel.getShifts(string)));
//			this.computionTimeChart.getData().add(createSeries(string, this.resultModel.getComputionTimes(string)));
//
//		}
//
//		final SortingManager sortingManager = new SortingManager();
//		sortingManager.profileAlgorithms(algorithms,
//				(name, problemSize, list) -> ResultController.this.resultModel.addData(name, problemSize, list),
//				model.getProblemGenerator(), model.getNumberOfStartElements(), model.getStepSize(), model.getNumberOfSteps(),
//				model.getRepetitions());
//
//	}

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert this.comparisionChart != null : "fx:id=\"comparisionChart\" was not injected: check your FXML file 'Result.fxml'.";
		assert this.shiftChart != null : "fx:id=\"shiftChart\" was not injected: check your FXML file 'Result.fxml'.";
		assert this.computionTimeChart != null : "fx:id=\"computionTimeChart\" was not injected: check your FXML file 'Result.fxml'.";
		assert this.progressIndicator  != null : "progresIndicator was not injected";
		LOGGER.error("progressIndicator={} service={}",progressIndicator, service);
		progressIndicator.progressProperty().bind(service.progressProperty());
		region.visibleProperty().bind(service.runningProperty());
		progressIndicator.visibleProperty().bind(service.runningProperty());
		service.start();
				
		AnchorPane.setBottomAnchor(stackPane, 0.0);
		AnchorPane.setLeftAnchor(stackPane, 0.0);
		AnchorPane.setRightAnchor(stackPane, 0.0);
		AnchorPane.setTopAnchor(stackPane, 0.0);
//		AnchorPane.setBottomAnchor(accordion, 0.0);
//		AnchorPane.setLeftAnchor(accordion, 0.0);
//		AnchorPane.setRightAnchor(accordion, 0.0);
//		AnchorPane.setTopAnchor(accordion, 0.0);
		
		service.setOnSucceeded(e-> {
			Result value = service.getValue();
			comparisionChart.dataProperty().bind(value.getComparisions());
			computionTimeChart.dataProperty().bind(value.getComputionTimes());
			shiftChart.dataProperty().bind(value.getShifts());
		});
		anchorPane.autosize();
		stackPane.autosize();
	}

	/**
	 * @param string
	 * @param comparisions
	 * @return
	 */
	private XYChart.Series<Number, Number> createSeries(String string, ObservableList<Data<Number, Number>> comparisions) {
		final XYChart.Series<Number, Number> compSer = new Series<Number, Number>(comparisions);
		compSer.setName(string);
		return compSer;
	}

	@Override
	public Node getContentNode() {
		return anchorPane;
	}
}
