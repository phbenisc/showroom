package de.htwg_konstanz.ui.configuration.distribution;

import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.ContinuousUniformGenerator;

import de.htwg_konstanz.ui.configuration.IDistributionConfiguration;

public class ContinuousUniformController implements IDistributionConfiguration{
	private static final Logger logger = LoggerFactory.getLogger(ContinuousUniformController.class);
	
	ContinuousUniformController(String fxmlName) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			logger.error("Error while loading MainWindow", e);
		}
	}
	
    @FXML
    private VBox root;

    @FXML
    private TextField minField;

    @FXML
    private TextField maxField;


	@Override
	public Node getContentNode() {
		return root;
	}

	@Override
	public NumberGenerator<Double> getProblemGenerator(Random rng) {
		String minText = minField.textProperty().get();
		String maxText = maxField.textProperty().get();
		logger.debug("parse '{}' to double",minText);
		double minDouble = Double.parseDouble(minText);
		logger.debug("parse '{}' to double",maxText);
		double maxDouble = Double.parseDouble(maxText);
		return new ContinuousUniformGenerator(minDouble, maxDouble, rng);
	}

}
