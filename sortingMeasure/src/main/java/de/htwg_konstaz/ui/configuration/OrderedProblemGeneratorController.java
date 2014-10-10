package de.htwg_konstaz.ui.configuration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.problemGenerators.OrderedProblemGenerator;
import uk.napierdevsoc.problemGenerators.OrderedProblemGenerator.Order;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

public class OrderedProblemGeneratorController implements IProblemGeneratorConfig{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderedProblemGeneratorController.class);
	
	public OrderedProblemGeneratorController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/OrderedProblemGenerator.fxml"));
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
