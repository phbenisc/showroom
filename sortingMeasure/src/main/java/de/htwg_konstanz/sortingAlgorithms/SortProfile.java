package de.htwg_konstanz.sortingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.htwg_konstanz.sort.interfaces.ISortData;
import de.htwg_konstanz.sort.interfaces.SortAlgorithm;

public class SortProfile{
	SortProfile(SortAlgorithm algo) {
		nameOfAlgorithm = algo.getClass().getName();
	}
	public final String nameOfAlgorithm;
	public final Map<Integer,List<ISortData>> sortingData = new HashMap<>();
	
	void addSortData(int step, ISortData sortData){
		if(sortingData.containsKey(step) && sortingData.get(step) != null)
			sortingData.get(step).add(sortData);
		else{
			List<ISortData> arrayList = new ArrayList<>();
			arrayList.add(sortData);
			sortingData.put(step, arrayList);
		}
	}
	
	List<ISortData> getSortDataFromIteration(int iteration){
		return sortingData.get(iteration);
	}
	
}
