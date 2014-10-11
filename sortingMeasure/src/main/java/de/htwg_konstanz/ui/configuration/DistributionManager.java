package de.htwg_konstanz.ui.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class DistributionManager {
	
	private  Properties props = new Properties();
	
	public DistributionManager() {
		props.setProperty("ContinuousUniform", "/fxml/ContinuousUniform.fxml");
		distris.put(ContinuousUniformController.class.getSimpleName(),()-> new ContinuousUniformController(props.getProperty("ContinuousUniform")));
	}
	
	private Map<String, IDistris> distris = new HashMap<>();
	
	private interface IDistris{
		public IDistributionConfiguration getInstance();
	}
	
	public Set<String> getAllDistiNames(){
		return distris.keySet();
	}
	public IDistributionConfiguration getDistri(String name){
		return distris.get(name).getInstance();
	}

}
