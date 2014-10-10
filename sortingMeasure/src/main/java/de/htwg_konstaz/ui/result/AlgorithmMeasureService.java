package de.htwg_konstaz.ui.result;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import de.htwg_konstaz.ui.configuration.ConfigurationModel;

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
