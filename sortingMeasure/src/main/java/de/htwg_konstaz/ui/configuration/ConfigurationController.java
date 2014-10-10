package de.htwg_konstaz.ui.configuration;

/**
 * Sample Skeleton for 'Configuration.fxml' Controller Class
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.problemGenerators.OrderedProblemGenerator;
import uk.napierdevsoc.problemGenerators.OrderedProblemGenerator.Order;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;
import uk.napierdevsoc.sortingAlgorithms.SortingManager;
import de.htwg_konstaz.ui.main.IController;
import de.htwg_konstaz.ui.main.MainWindowController;
import de.htwg_konstaz.ui.result.Result;
import de.htwg_konstaz.ui.result.ResultController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.StringConverter;

public class ConfigurationController implements IController {

	private SortingManager manager = new SortingManager();

	private Tab tab;

	protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

	public ConfigurationController(Tab tab) throws IOException {
		this("/fxml/Configuration.fxml", tab);
	}

	/**
	 * @param fxmlName
	 *            the name of the file, e.g. MainWindow.fxml
	 * @throws IOException
	 *             when the file is loadable
	 */
	public ConfigurationController(String fxmlName,Tab tab) throws IOException {
		this.tab = tab;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			LOGGER.error("Error while loading MainWindow", e);
			throw e;
		}

		generatorConfigs.add(new OrderedProblemGeneratorController());
	}

	@FXML
	// fx:id="root"
	private ScrollPane root; // Value injected by FXMLLoader

	@FXML
	// fx:id="repSlider"
	private Slider repSlider; // Value injected by FXMLLoader
	@FXML
	// fx:id="stepSizeField"
	private TextField stepSizeField; // Value injected by FXMLLoader
	@FXML
	// fx:id="startEleSlider"
	private Slider startEleSlider; // Value injected by FXMLLoader
	@FXML
	// fx:id="repField"
	private TextField repField; // Value injected by FXMLLoader
	@FXML
	// fx:id="nrOfStepsSlider"
	private Slider nrOfStepsSlider; // Value injected by FXMLLoader
	@FXML
	// fx:id="generatorPane"
	private AnchorPane generatorPane; // Value injected by FXMLLoader
	@FXML
	// fx:id="nrOfStepsField"
	private TextField nrOfStepsField; // Value injected by FXMLLoader
	@FXML
	// fx:id="startEleField"
	private TextField startEleField; // Value injected by FXMLLoader
	@FXML
	// fx:id="stepSizeSlider"
	private Slider stepSizeSlider; // Value injected by FXMLLoader

	// Generator
	@FXML
	// fx:id="generatorChoiceBox"
	private ChoiceBox<IProblemGeneratorConfig> generatorChoiceBox; // Value injected by FXMLLoader

	// Algorithms
	@FXML
	// fx:id="algorithmsFlow"
	private FlowPane algorithmsFlow; // Value injected by FXMLLoader

	private StringConverter<Number> converter = new StringConverter<Number>() {
		@Override
		public String toString(Number object) {
			return object.toString();
		}

		@Override
		public Integer fromString(String string) {
			return Integer.parseInt(string);
		}
	};

	private ChangeListener<IProblemGeneratorConfig> generatorListener = new ChangeListener<IProblemGeneratorConfig>() {
		@Override
		public void changed(ObservableValue<? extends IProblemGeneratorConfig> observable, IProblemGeneratorConfig oldValue,
				IProblemGeneratorConfig newValue) {
			ObservableList<Node> children = generatorPane.getChildren();
			children.clear();
			children.add(newValue.getContentNode());
			actualProblemGeneratorConfigValue = newValue;
		}
	};

	private MainWindowController mainController;
	private IProblemGeneratorConfig actualProblemGeneratorConfigValue;
	private List<IProblemGeneratorConfig> generatorConfigs = new ArrayList<IProblemGeneratorConfig>();

	@FXML
	// This method is called by the FXMLLoader when initialisation is complete
	void initialize() {
		// General
		repField.textProperty().bindBidirectional(repSlider.valueProperty(), converter);
		startEleField.textProperty().bindBidirectional(startEleSlider.valueProperty(), converter);
		stepSizeField.textProperty().bindBidirectional(stepSizeSlider.valueProperty(), converter);
		nrOfStepsField.textProperty().bindBidirectional(nrOfStepsSlider.valueProperty(), converter);

		ObservableList<IProblemGeneratorConfig> observableArrayList = FXCollections.observableArrayList(generatorConfigs);
		generatorChoiceBox.itemsProperty().set(observableArrayList);
		generatorChoiceBox.valueProperty().addListener(generatorListener);
		generatorChoiceBox.valueProperty().set(observableArrayList.stream().findFirst().orElse(new OrderedProblemGeneratorController()));

		for (String algo : manager.getAlgorithms()) {
			CheckBox checkbox = new CheckBox(algo);
			checkbox.setIndeterminate(true);
			algorithmsFlow.getChildren().add(checkbox);
		}
	}

	@FXML
	void loadResultPage(ActionEvent event) {
		Set<String> algos = new HashSet<>();
		for (Node checkBox : algorithmsFlow.getChildren()) {
			if (checkBox instanceof CheckBox) {
				algos.add(((CheckBox) checkBox).getText());
			}
		}

		ConfigurationModel configurationModel = new ConfigurationModel(algos, 
				actualProblemGeneratorConfigValue.getProblemGenerator(),
				startEleSlider.valueProperty().intValue(), stepSizeSlider.valueProperty().intValue(), 
				nrOfStepsSlider.valueProperty().intValue(), repSlider.valueProperty().intValue());
		ResultController rController = new ResultController(configurationModel);
		tab.setContent(rController.getContentNode());
		
		
	}

	@Override
	public Node getContentNode() {
		return root;
	}

}
