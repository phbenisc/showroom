package de.htwg_konstanz.ui.configuration;

import java.io.IOException;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncommons.maths.number.NumberGenerator;

import uk.napierdevsoc.problemGenerators.RandomProblemGenerator;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;

public class RandomProblemGeneratorController implements IProblemGeneratorConfig {
	private static final Logger logger = LoggerFactory.getLogger(RandomProblemGeneratorController.class);
	
	public RandomProblemGeneratorController(String fxmlName) {
		logger.debug("Construct RandomProblemGeneratorController(fxmlName={})", fxmlName);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			logger.error("Error while loading MainWindow", e);
		}
		logger.debug("construction complete");
	}
	
    @FXML
    private VBox root;
	
    @FXML
    private ChoiceBox<String> distChoiceBox;

    @FXML
    private ChoiceBox<String> rngChoiceBox;

    @FXML
    private VBox distAddOpt;
    
    private RNGManager rngManager = new RNGManager();
    private DistributionManager distriManager = new DistributionManager();

	@Override
	public Node getContentNode() {
		logger.debug("getContentNode() -> {}",root);
		return root;
	}

	@Override
	public IProblemGenerator getProblemGenerator() {
		Random rng = rngManager.getRNG(rngChoiceBox.getValue());
		IDistributionConfiguration distri = distriManager.getDistri(distChoiceBox.getValue());
		NumberGenerator<Double> problemGenerator = distri.getProblemGenerator(rng);
		return new RandomProblemGenerator(problemGenerator);
	}
	
    @FXML
    void initialize() {
    	logger.debug("connect fxml and Controller");
    	ObservableList<String> rngList = FXCollections.observableArrayList(rngManager.getAllRngNames());
    	rngChoiceBox.itemsProperty().set(rngList);
    	
    	distChoiceBox.valueProperty().addListener(distriListener);
    	
    	ObservableList<String> distriList = FXCollections.observableArrayList(distriManager.getAllDistiNames());
    	distChoiceBox.itemsProperty().set(distriList);
    	logger.debug("connection complete");   	
    	
		
    }

    private ChangeListener<String> distriListener = new ChangeListener<String>() {
    	
    	@Override
    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    		IDistributionConfiguration distri = distriManager.getDistri(newValue);
    		
    		ObservableList<Node> children = distAddOpt.getChildren();
    		children.clear();
    		children.add(distri.getContentNode());				
    	}
    };
}
