package uk.napierdevsoc.problemGenerators;

import org.uncommons.maths.number.NumberGenerator;

import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;

public class RandomProblemGenerator implements IProblemGenerator{
	
	public RandomProblemGenerator(NumberGenerator<Double> generator) {
		super();
		this.generator = generator;
	}

	private NumberGenerator<Double> generator;

	@Override
	public double[] createProblem(int size) {
		double[] problem = new double[size];
		for (int i = 0; i < problem.length; i++) {
			problem[i] = this.generator.nextValue();
		}
		return problem;
	}



}
