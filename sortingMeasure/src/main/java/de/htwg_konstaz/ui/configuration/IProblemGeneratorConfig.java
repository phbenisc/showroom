package de.htwg_konstaz.ui.configuration;

import de.htwg_konstaz.ui.main.IController;
import uk.napierdevsoc.sortingAlgorithms.IProblemGenerator;

public interface IProblemGeneratorConfig extends IController{
	
	IProblemGenerator getProblemGenerator();

}
