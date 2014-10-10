package uk.napierdevsoc.sortingAlgorithms;

public interface IProblemGenerator {
	
	double[] createProblem(int size);
	
	default double[][] createProblems(int numberOfProblems, int sizeOfTheProblems){
		double[][] problems = new double[numberOfProblems][];
		for (int i = 0; i < problems.length; i++) {
			problems[i] = createProblem(sizeOfTheProblems);
		}
		return problems;
	}

}
