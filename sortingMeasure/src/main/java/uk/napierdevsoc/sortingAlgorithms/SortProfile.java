package uk.napierdevsoc.sortingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.napierdevsoc.sort.interfaces.SortAlgorithm;
import uk.napierdevsoc.sort.interfaces.ISortData;

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
