package de.htwg_konstanz.ui.configuration;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.problemGenerators.OrderedProblemGenerator;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;

public class OrderedProblemGeneratorController implements IProblemGeneratorConfig{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderedProblemGeneratorController.class);
	
	public OrderedProblemGeneratorController(String name) {
		LOGGER.debug("Construct OrderedProblemGeneratorController(fileName={})",name);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			LOGGER.error("Error while loading MainWindow", e);
		}

	}
	

    @FXML
    private HBox root;

    @FXML
    private ChoiceBox<OrderedProblemGenerator.Order> choiceBox;
    
    @FXML
    void initialize() {
    	choiceBox.itemsProperty().set(FXCollections.observableArrayList(OrderedProblemGenerator.Order.values()));
    	choiceBox.valueProperty().set(OrderedProblemGenerator.Order.ascending);
    }

	@Override
	public Node getContentNode() {
		return root;
	}

	@Override
	public IProblemGenerator getProblemGenerator() {
		return new OrderedProblemGenerator(choiceBox.getValue());
	}

}
