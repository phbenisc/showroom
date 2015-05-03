package de.htwg_konstanz.ui.main;

import javafx.beans.value.ObservableValue;

public interface IMainWindowController extends IController{
	
	public void status(ObservableValue<? extends Number> observable);
	public void status(String text);
	public void closeApllicationWithError(String message);

}
