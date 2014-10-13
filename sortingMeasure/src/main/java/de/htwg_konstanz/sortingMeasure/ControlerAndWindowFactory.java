package de.htwg_konstanz.sortingMeasure;

import java.util.Properties;

import javafx.scene.control.Tab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwg_konstanz.ui.configuration.ConfigurationController;
import de.htwg_konstanz.ui.configuration.ConfigurationModel;
import de.htwg_konstanz.ui.main.IMainWindowController;
import de.htwg_konstanz.ui.result.ResultController;

public class ControlerAndWindowFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
	private static ControlerAndWindowFactory me;
	
	private  Properties props = new Properties();
	private  IMainWindowController iMainWindowController;
	
	void setiMainWindowController(IMainWindowController iMainWindowController) {
		this.iMainWindowController = iMainWindowController;
	}


	private ControlerAndWindowFactory() {	
//		try(FileInputStream in = new FileInputStream("/fxml.properties")) {
		
		props.setProperty("mainWindow", "/fxml/MainWindow.fxml");
		props.setProperty("configurationWindow", "/fxml/Configuration.fxml");
		props.setProperty("resultWindow", "/fxml/Result.fxml");
		props.setProperty("orderedProblemGenWindow", "/fxml/OrderedProblemGenerator.fxml");
		props.setProperty("randomProblemGenWindow", "/fxml/RandomProblemGenerator.fxml");
		
//		try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/fxml.properties")) {
//			props.load(in);
//		} catch (FileNotFoundException e) {
//			LOGGER.error("loading properties",e);
//		} catch (IOException e) {
//			LOGGER.error("IOException while loading properties, file was found",e);
//		}	
	}
	
	
	public static ControlerAndWindowFactory getInstance() {
		if(me==null)
			me = new ControlerAndWindowFactory();
		return me;		
	}
	
	public IMainWindowController getMainWindowController(){
		return iMainWindowController;
	}
	
	public ResultController getNewResultController(ConfigurationModel model){		
		LOGGER.debug("getNewResultController(model={})",model);
		return new ResultController(props.getProperty("resultWindow"), model);
	}

	
	public ConfigurationController getNewConfigurationController(Tab tab) {
		return new ConfigurationController(props.getProperty("configurationWindow"),tab);
	}


	
	

}
