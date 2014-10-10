package de.htwg_konstaz.ui.result;

import de.htwg_konstaz.ui.configuration.ConfigurationModel;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

public class AlgorithmMeasureService extends Service<Result> {
	ConfigurationModel model;
	
	public AlgorithmMeasureService(ConfigurationModel model){
		this.model = model;
	}

	@Override
	protected Task<Result> createTask() {
		return new AlgorithmMeasureTask(model);
	}

}
