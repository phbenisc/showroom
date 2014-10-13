package de.htwg_konstanz.ui.configuration;

import de.htwg_konstanz.ui.main.IController;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;

public interface IProblemGeneratorConfig extends IController{
	
	IProblemGenerator getProblemGenerator();
	
	public static String getName(){
		return IProblemGeneratorConfig.class.getSimpleName();
	}

}
