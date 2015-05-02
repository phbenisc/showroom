package de.htwg_konstanz.sort.manager;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstanz.sort.bubbleSort.BubbleSort;
import de.htwg_konstanz.sort.interfaces.ISortData;
import de.htwg_konstanz.sort.interfaces.SortAlgorithm;
import de.htwg_konstanz.sort.quickSort.SimpleQuickSort;

public class SortingManager {

	private Map<String, SortAlgorithm> algorithms = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(SortingManager.class);

	public SortingManager() {
		addAlgorithm(new BubbleSort());
		addAlgorithm(new SimpleQuickSort());
	}

	public boolean exceedsMaxArraySize(int numberOfStartElements, int numberOfSteps, int stepSize) {
		logger.debug("exceedsIntegerRange(numberOfStartElements={}, numberOfSteps={}, stepSize={}) called", numberOfStartElements,
				numberOfSteps);

		double highestNumberOfElements = 0; // double because it is possible that a multiplication beetween int exceeds
											// Long.MAX_Value
		highestNumberOfElements = numberOfStartElements + (double) (numberOfSteps * 1.0 * stepSize);
		boolean result = highestNumberOfElements > Integer.MAX_VALUE;
		logger.debug("exceedsIntegerRange return={}", result);
		return result;
	}

	public Collection<SortProfile> profileAlgorithms(Set<String> algorithms, IAlgorithmDataCallback dataCallback,
			IProblemGenerator problemGenerator, int numberOfStartElements, int stepSize, int numberOfSteps, int repetitions) {
		if (exceedsMaxArraySize(numberOfStartElements, numberOfSteps, stepSize))
			throw new IllegalArgumentException("the kombination of the parameters exceeds the max. array length!");

		// get all Algorithms and initData
		Map<SortAlgorithm, SortProfile> algos = initAlgoData(algorithms);

		for (int step = 1; step < numberOfSteps+1; step++) {
			// in every step the problem size will be increased
			double[][] problems = problemGenerator.createProblems(repetitions, numberOfStartElements);
			// all algorithms solve the same problems
			// iterate over algorithms
			for (SortAlgorithm algorithm : algos.keySet()) {
				profileAlgorithm(dataCallback, algos, step, problems, algorithm);
			}
			numberOfStartElements += stepSize;
		}
		return algos.values();
	}

	/**
	 * @param dataCallback
	 * @param algos
	 * @param step
	 * @param problems
	 * @param algorithm
	 * @return 
	 */
	public List<ISortData> profileAlgorithm(IAlgorithmDataCallback dataCallback, Map<SortAlgorithm, SortProfile> algos, int step, double[][] problems,
			SortAlgorithm algorithm) {
		SortProfile sortProfile = null;
		// give them a copy of every problem to solve
		sortProfile = algos.get(algorithm);
		for (int j = 0; j < problems.length; j++) {
			// copy
			double[] problem = Arrays.copyOf(problems[j], problems[j].length);
			// sort array
			ISortData sortData = algorithm.sortWithMeasure(problem);
			// store Data
			sortProfile.addSortData(step, sortData);
		}
		dataCallback.callback(algorithm.getClass().getName(), problems[0].length, sortProfile.getSortDataFromIteration(step));
		return sortProfile.getSortDataFromIteration(step);
	}

	public Map<SortAlgorithm, SortProfile> initAlgoData(Set<String> algorithms) {
		Map<SortAlgorithm, SortProfile> algoData = new HashMap<>();
		Set<SortAlgorithm> algos = getAlgorithms(algorithms);
		for (SortAlgorithm iSortAlgorithm : algos) {
			algoData.put(iSortAlgorithm, new SortProfile(iSortAlgorithm));
		}
		return algoData;
	}

	/**
	 * @param algorithms
	 *            Name of the algorithms, see getAlgorithms()
	 * @throws IllegalArgumentException
	 *             if the algorithm was not found
	 */
	private Set<SortAlgorithm> getAlgorithms(Set<String> algorithms) throws IllegalArgumentException {
		Set<SortAlgorithm> algos = new HashSet<SortAlgorithm>();
		for (String string : algorithms) {
			SortAlgorithm algo = this.algorithms.get(string);
			if (algo == null)
				throw new IllegalArgumentException(String.format("Algorithm %s was not found", string));
			algos.add(algo);
		}
		return algos;
	}

	public Set<String> getAlgorithms() {
		Set<String> keySet = algorithms.keySet();
		logger.trace("getAlgorithms called. return={}", keySet);
		return keySet;
	}

	public void addAlgorithm(final SortAlgorithm algorithm) {
		logger.trace("addAlgorithm(algorithm={}) called", algorithm);
		if (algorithm == null)
			throw new IllegalArgumentException("argument was null!");
		this.algorithms.put(algorithm.getClass().getName(), algorithm);
	}

	public Collection<IProblemGenerator> getAllProblemGenerators() {
		// TODO Auto-generated method stub
		return null;
	}

}
