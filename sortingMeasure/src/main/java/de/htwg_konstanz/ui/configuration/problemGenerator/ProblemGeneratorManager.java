package de.htwg_konstanz.ui.configuration.problemGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import de.htwg_konstanz.ui.configuration.IProblemGeneratorConfig;



public class ProblemGeneratorManager {
	private  Properties props = new Properties();
	
	public ProblemGeneratorManager() {
		props.setProperty("orderedProblemGenWindow", "/fxml/OrderedProblemGenerator.fxml");
		props.setProperty("randomProblemGenWindow", "/fxml/RandomProblemGenerator.fxml");
		problemGens.put(OrderedProblemGeneratorController.getName(),new OrderedProblemGeneratorController(props.getProperty("orderedProblemGenWindow")));
		problemGens.put(RandomProblemGeneratorController.getName(),new RandomProblemGeneratorController(props.getProperty("randomProblemGenWindow")));
	}
	
	private Map<String, IProblemGeneratorConfig> problemGens = new HashMap<>();
	
	
	public Set<String> getAllProblemConfigNames(){
		return problemGens.keySet();
	}
	public IProblemGeneratorConfig getProblemConfig(String name){
		return problemGens.get(name);
	}
}
