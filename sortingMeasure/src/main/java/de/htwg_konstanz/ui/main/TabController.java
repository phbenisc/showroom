package de.htwg_konstanz.ui.main;

import de.htwg_konstanz.ui.configuration.ConfigurationController;
import de.htwg_konstanz.ui.configuration.ConfigurationModel;
import de.htwg_konstanz.ui.result.ResultController;
import javafx.scene.control.Tab;

public class TabController extends Tab{
	
	public TabController() {
		toConfigurationScreen();
		this.setClosable(true);
//		this.getTabPane().getSelectionModel().select(this);
	}	
	
	public void toConfigurationScreen(){
		ConfigurationController newConfigurationController = ControlerAndWindowFactory.getInstance().getNewConfigurationController(this);
		this.setContent(newConfigurationController.getContentNode());
	}
	
	public void toResultScreen(ConfigurationModel config){
		ResultController newResultController = ControlerAndWindowFactory.getInstance().getNewResultController(this, config);
		this.setContent(newResultController.getContentNode());
	}
	
	public void setTabName(String tabName){
		this.setText(tabName);
	}


	

}
