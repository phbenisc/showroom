package de.htwg_konstanz.ui.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;

public class Result {
	private ObjectProperty<ObservableList<XYChart.Series<Number,Number>>> comparisions ;
	private ObjectProperty<ObservableList<XYChart.Series<Number,Number>>> shifts;
	private ObjectProperty<ObservableList<XYChart.Series<Number,Number>>> computionTimes;
	
	public Result(Set<String> algos) {
		comparisions = new SimpleObjectProperty<>(FXCollections.observableArrayList(initSeries(algos)));
		shifts = new SimpleObjectProperty<>(FXCollections.observableArrayList(initSeries(algos)));
		computionTimes = new SimpleObjectProperty<>(FXCollections.observableArrayList(initSeries(algos)));
	}
	
	private List<Series<Number, Number>> initSeries(Set<String> algos){
		List<Series<Number, Number>> resultList = new ArrayList<>();
		for (String algo : algos) {
			Series<Number, Number> series = new XYChart.Series<Number,Number>();
			series.setName(algo);
			resultList.add(series);
		}
		return resultList;
	}
	
	ObjectProperty<ObservableList<XYChart.Series<Number, Number>>> getComparisions() {
		return comparisions;
	}
	void setComparisions(ObjectProperty<ObservableList<XYChart.Series<Number, Number>>> comparisions) {
		this.comparisions = comparisions;
	}
	ObjectProperty<ObservableList<XYChart.Series<Number, Number>>> getShifts() {
		return shifts;
	}
	void setShifts(ObjectProperty<ObservableList<XYChart.Series<Number, Number>>> shifts) {
		this.shifts = shifts;
	}
	ObjectProperty<ObservableList<XYChart.Series<Number, Number>>> getComputionTimes() {
		return computionTimes;
	}
	void setComputionTimes(ObjectProperty<ObservableList<XYChart.Series<Number, Number>>> computionTimes) {
		this.computionTimes = computionTimes;
	}
}
