package de.htwg_konstanz.ui.configuration;

import java.util.Set;

import uk.napierdevsoc.problemGenerators.OrderedProblemGenerator;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;
import uk.napierdevsoc.sortingAlgorithms.SortingManager;

public class ConfigurationModel {
	
	public ConfigurationModel(){}
	
	public ConfigurationModel(Set<String> algorithms, IProblemGenerator problemGenerator,
			int numberOfStartElements, int stepSize, int numberOfSteps, int repetitions) {
		super();
		this.algorithms = algorithms;
		this.problemGenerator = problemGenerator;
		this.numberOfStartElements = numberOfStartElements;
		this.stepSize = stepSize;
		this.numberOfSteps = numberOfSteps;
		this.repetitions = repetitions;
	}
	private SortingManager sortingManager = new SortingManager();
	private Set<String> algorithms = sortingManager.getAlgorithms();
	private IProblemGenerator problemGenerator = new OrderedProblemGenerator(OrderedProblemGenerator.Order.ascending);
	private int numberOfStartElements = 200;
	private int stepSize = 200;
	private int numberOfSteps = 20;
	private int repetitions = 10;
	

	public Set<String> getAlgorithms() {
		return algorithms;
	}
	public IProblemGenerator getProblemGenerator() {
		return problemGenerator;
	}
	public int getNumberOfStartElements() {
		return numberOfStartElements;
	}
	public int getStepSize() {
		return stepSize;
	}
	public int getNumberOfSteps() {
		return numberOfSteps;
	}
	public int getRepetitions() {
		return repetitions;
	}
	


}
