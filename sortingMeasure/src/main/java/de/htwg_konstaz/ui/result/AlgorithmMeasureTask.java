package de.htwg_konstaz.ui.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.LongStream;

import javafx.concurrent.Task;
import javafx.scene.chart.XYChart.Data;
import uk.napierdevsoc.sort.interfaces.ISortAlgorithm;
import uk.napierdevsoc.sort.interfaces.ISortData;
import uk.napierdevsoc.sortingAlgorithms.IAlgorithmDataCallback;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;
import uk.napierdevsoc.sortingAlgorithms.SortProfile;
import uk.napierdevsoc.sortingAlgorithms.SortingManager;
import de.htwg_konstaz.ui.configuration.ConfigurationModel;

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
		Map<ISortAlgorithm, SortProfile> algos = sortingManager.initAlgoData(algorithms);

		for (int step = 1; step < numberOfSteps + 1; step++) {
			updateProgress(step, numberOfSteps + 1);
			// in every step the problem size will be increased
			double[][] problems = problemGenerator.createProblems(repetitions, numberOfStartElements);
			// all algorithms solve the same problems
			// iterate over algorithms
			for (ISortAlgorithm algorithm : algos.keySet()) {
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

	public AlgorithmMeasureTask(ConfigurationModel model) {
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
