package uk.napierdevsoc.sort.bubbleSort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.sort.interfaces.SortAlgorithm;
import uk.napierdevsoc.sort.interfaces.ISortData;
import uk.napierdevsoc.sort.util.SortUtil;

public class BubbleSort extends SortAlgorithm {

	private static final Logger logger = LoggerFactory.getLogger(BubbleSort.class);

	@Override
	public ISortData sortWithComparisonAndShiftMeasure(double[] array, ISortData sortData) {
		logger.debug("sort(array={}, sortData={}) called",array==null?"null":"notNull",sortData);
		logger.trace("array={}",array);
		
		//sorting algorithm
		for (int i = 1; i < array.length; i++){
			for (int j = 0; j < array.length - i; j++){
				sortData.addComparison();
				if (array[j] > array[j + 1]) {
					sortData.addShifts(3);
					SortUtil.swap(array, j, j+1);
				}
			}
		}
		logger.debug("sortWithComparisonAndShiftMeasure(...) -> {}", sortData);
		return sortData;
	}

	


}
