package uk.napierdevsoc.sortingAlgorithms;

import java.util.List;

import uk.napierdevsoc.sort.interfaces.ISortData;

public interface IAlgorithmDataCallback {
	
	void callback(String name, int problemSize, List<ISortData> list);
}
