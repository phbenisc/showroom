package de.htwg_konstanz.sort.problemGenerators;

import de.htwg_konstanz.sort.manager.IProblemGenerator;

public class OrderedProblemGenerator implements IProblemGenerator {

	public enum Order {
		ascending(+1), descending(-1);

		private double differenceToNextValue;

		Order(double differenceToNextValue) {
			this.differenceToNextValue = differenceToNextValue;
		}

		public double getNextValue(double oldValue) {
			return oldValue + differenceToNextValue;
		}
	}

	private Order order;

	public OrderedProblemGenerator(Order order) {
		this.order = order;
	}

	@Override
	public double[] createProblem(int size) {
		double start = 0;

		double[] problem = new double[size];
		for (int i = 0; i < problem.length; i++) {
			start = order.getNextValue(start);
			problem[i] = start;
		}

		return problem;
	}



}
