package de.htwg_konstanz.ui.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.LongStream;

import javafx.concurrent.Task;
import javafx.scene.chart.XYChart.Data;
import de.htwg_konstanz.sort.interfaces.ISortData;
import de.htwg_konstanz.sort.interfaces.SortAlgorithm;
import de.htwg_konstanz.sort.manager.IAlgorithmDataCallback;
import de.htwg_konstanz.sort.manager.IProblemGenerator;
import de.htwg_konstanz.sort.manager.SortProfile;
import de.htwg_konstanz.sort.manager.SortingManager;
import de.htwg_konstanz.ui.configuration.ConfigurationModel;

public class AlgorithmMeasureTask extends Task<Result> {
	
	

	@Override
	protected Result call() throws Exception {
		profileAlgorithms(model.getAlgorithms(), model.getProblemGenerator(), model.getNumberOfStartElements(), model.getStepSize(),
				model.getNumberOfSteps(), model.getRepetitions());
		return result;
	}

	public Collection<SortProfile> profileAlgorithms(Set<String> algorithms, IProblemGenerator problemGenerator, int numberOfStartElements,
			int stepSize, int numberOfSteps, int repetitions) {

		IAlgorithmDataCallback dataCallback = new  IAlgorithmDataCallback(){
			@Override
			public void callback(String name, int problemSize, List<ISortData> list) {
				return;
			}			
		};

		if (sortingManager.exceedsMaxArraySize(numberOfStartElements, numberOfSteps, stepSize))
			throw new IllegalArgumentException("the kombination of the parameters exceeds the max. array length!");

		// get all Algorithms and initData
		Map<SortAlgorithm, SortProfile> algos = sortingManager.initAlgoData(algorithms);

		for (int step = 1; step < numberOfSteps + 1; step++) {
			updateProgress(step, numberOfSteps + 1);
			// in every step the problem size will be increased
			double[][] problems = problemGenerator.createProblems(repetitions, numberOfStartElements);
			// all algorithms solve the same problems
			// iterate over algorithms
			for (SortAlgorithm algorithm : algos.keySet()) {
				addData(algorithm.getClass().getName(), problems[0].length, sortingManager.profileAlgorithm(dataCallback, algos, step, problems, algorithm));
			}
			numberOfStartElements += stepSize;
		}
		return algos.values();
	}

	private void addData(String algoName, int problemSize, List<ISortData> data) {
		result.getComparisions().getValue().stream().filter(e -> e.getName().equals(algoName)).findFirst().get().getData()
				.add(getAvarage(data, problemSize, e -> e.getComparisons()));
		result.getComputionTimes().getValue().stream().filter(e -> e.getName().equals(algoName)).findFirst().get().getData()
				.add(getAvarage(data, problemSize, e -> e.getTime()));
		result.getShifts().getValue().stream().filter(e -> e.getName().equals(algoName)).findFirst().get().getData()
				.add(getAvarage(data, problemSize, e -> e.getShifts()));
	}



	private ConfigurationModel model;
	private Result result;
	private final SortingManager sortingManager = new SortingManager();

	AlgorithmMeasureTask(ConfigurationModel model) {
		this.model = model;
		result = new Result(model.getAlgorithms());
	}

	private interface ILongValues {
		long getValue(ISortData object);
	}

	private Data<Number, Number> getAvarage(List<ISortData> list, int problemSize, ILongValues getLongValues) {
		List<Long> list2 = new ArrayList<>();
		for (ISortData object : list) {
			list2.add(getLongValues.getValue(object));
		}
		double result = list2.stream().flatMapToLong(n -> LongStream.of(n)).average().orElse(0.0);

		return new Data<Number, Number>(problemSize, (long) result);
	}

}
