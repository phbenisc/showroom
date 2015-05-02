package de.htwg_konstanz.sortingAlgorithms;

import java.util.List;

import de.htwg_konstanz.sort.interfaces.ISortData;

public interface IAlgorithmDataCallback {
	
	void callback(String name, int problemSize, List<ISortData> list);
}
