package de.htwg_konstanz.ui.configuration;

import de.htwg_konstanz.sortingAlgorithms.IProblemGenerator;
import de.htwg_konstanz.ui.main.IController;

public interface IProblemGeneratorConfig extends IController{
	
	IProblemGenerator getProblemGenerator();
	
	public static String getName(){
		return IProblemGeneratorConfig.class.getSimpleName();
	}

}
