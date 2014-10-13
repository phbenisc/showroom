package de.htwg_konstanz.ui.configuration;

/**
 * Sample Skeleton for 'Configuration.fxml' Controller Class
 */

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.sortingAlgorithms.SortingManager;
import de.htwg_konstanz.ui.configuration.problemGenerator.ProblemGeneratorManager;
import de.htwg_konstanz.ui.main.ControlerAndWindowFactory;
import de.htwg_konstanz.ui.main.IController;
import de.htwg_konstanz.ui.main.TabController;
import de.htwg_konstanz.ui.result.ResultController;

public class ConfigurationController implements IController {

	private SortingManager manager = new SortingManager();
	private ProblemGeneratorManager problemManager = new ProblemGeneratorManager();

	private TabController tab;

	protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

	/**
	 * @param fxmlName
	 *            the name of the file, e.g. MainWindow.fxml
	 * @throws IOException
	 *             when the file is loadable
	 */
	public ConfigurationController(String fxmlName, TabController tab) {
		this.tab = tab;
		tab.setTabName("new Configuration");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			LOGGER.error("Error while loading MainWindow", e);
		}

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
	private ChoiceBox<String> generatorChoiceBox; // Value injected by FXMLLoader

	// Algorithms
	@FXML
	// fx:id="algorithmsFlow"
	private FlowPane algorithmsFlow; // Value injected by FXMLLoader

	private StringConverter<Number> converter = new StringConverter<Number>() {
		@Override
		public String toString(Number object) {
			return object.intValue() + "";
		}

		@Override
		public Integer fromString(String string) {
			return Integer.parseInt(string);
		}
	};

	private ChangeListener<String> generatorListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue,
				String newValue) {
			ObservableList<Node> children = generatorPane.getChildren();
			children.clear();
			children.add(problemManager.getProblemConfig(newValue).getContentNode());
			// actualProblemGeneratorConfigValue = newValue;
		}
	};



	@FXML
	// This method is called by the FXMLLoader when initialisation is complete
	void initialize() {
		// General
		repField.textProperty().bindBidirectional(repSlider.valueProperty(), converter);
		startEleField.textProperty().bindBidirectional(startEleSlider.valueProperty(), converter);
		stepSizeField.textProperty().bindBidirectional(stepSizeSlider.valueProperty(), converter);
		nrOfStepsField.textProperty().bindBidirectional(nrOfStepsSlider.valueProperty(), converter);



		ObservableList<String> observableArrayList = FXCollections.observableArrayList(problemManager.getAllProblemConfigNames());
		LOGGER.debug("choiceBox items to display: {}", observableArrayList);
		generatorChoiceBox.itemsProperty().set(observableArrayList);
		generatorChoiceBox.valueProperty().addListener(generatorListener);
		generatorChoiceBox.valueProperty().set(
				observableArrayList.stream().findFirst()
						.orElse("Nor Problem Generator found"));

		for (String algo : manager.getAlgorithms()) {
			CheckBox checkbox = new CheckBox(algo);
			checkbox.setAllowIndeterminate(false);
			checkbox.setSelected(true);
			algorithmsFlow.getChildren().add(checkbox);
		}
	}

	@FXML
	void loadResultPage(ActionEvent event) {
		Set<String> algos = new HashSet<>();
		for (Node node : algorithmsFlow.getChildren()) {
			if (node instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) node;
				if (checkBox.isSelected())
					algos.add(checkBox.getText());
			}
		}

		ConfigurationModel configurationModel = new ConfigurationModel(algos,
				problemManager.getProblemConfig(generatorChoiceBox.getValue()).getProblemGenerator(), startEleSlider.valueProperty().intValue(), stepSizeSlider
						.valueProperty().intValue(), nrOfStepsSlider.valueProperty().intValue(), repSlider.valueProperty().intValue());
		tab.toResultScreen(configurationModel);

	}

	@Override
	public Node getContentNode() {
		return root;
	}

}
