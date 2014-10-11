package de.htwg_konstanz.ui.configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.AESCounterRNG;
import org.uncommons.maths.random.CMWC4096RNG;
import org.uncommons.maths.random.CellularAutomatonRNG;
import org.uncommons.maths.random.ContinuousUniformGenerator;
import org.uncommons.maths.random.JavaRNG;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.RepeatableRNG;
import org.uncommons.maths.random.XORShiftRNG;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
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
    private ChoiceBox<NumberGenerator<Double>> distChoiceBox;

    @FXML
    private ChoiceBox<RepeatableRNG> rngChoiceBox;

    @FXML
    private VBox distAddOpt;

	@Override
	public Node getContentNode() {
		logger.debug("getContentNode() -> {}",root);
		return root;
	}

	@Override
	public IProblemGenerator getProblemGenerator() {
		return new RandomProblemGenerator(distChoiceBox.getValue());
	}
	
    @FXML
    void initialize() {
    	logger.debug("connect fxml and Controller");
    	ObservableList<RepeatableRNG> rngList = FXCollections.observableArrayList();
    	logger.debug("1");
    	MersenneTwisterRNG mersenneTwisterRNG = new MersenneTwisterRNG();
    	logger.debug("11");
		JavaRNG javaRNG = new JavaRNG();
		logger.debug("12");
		CellularAutomatonRNG cellularAutomatonRNG = new  CellularAutomatonRNG();
		logger.debug("13");
//		CMWC4096RNG cmwc4096rng = new CMWC4096RNG();
		logger.debug("14");
		XORShiftRNG xorShiftRNG = new XORShiftRNG();
		logger.debug("15");
		rngList.addAll(mersenneTwisterRNG,javaRNG, cellularAutomatonRNG, /*cmwc4096rng,*/ xorShiftRNG);
    	logger.debug("2");
    	try {
			rngList.add(new AESCounterRNG());
		} catch (GeneralSecurityException e) {
			logger.debug("Not able to use AESCounterRNG", e);
		}
    	logger.debug("3");
    	rngChoiceBox.itemsProperty().set(rngList);
    	logger.debug("4");
    	distChoiceBox.itemsProperty().get().add(new ContinuousUniformGenerator(0, 1, mersenneTwisterRNG));
    	logger.debug("connection complete");
    }

}
