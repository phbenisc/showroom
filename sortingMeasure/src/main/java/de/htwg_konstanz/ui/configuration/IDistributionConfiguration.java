package de.htwg_konstanz.ui.configuration;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;

import de.htwg_konstanz.ui.main.IController;

public interface IDistributionConfiguration extends IController {
	
	public NumberGenerator<Double> getProblemGenerator(Random rng);

}
