package uk.napierdevsoc.sort.quickSort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.sort.interfaces.SortAlgorithm;
import uk.napierdevsoc.sort.interfaces.ISortData;
import uk.napierdevsoc.sort.util.SortUtil;

/**
 * @author Philip Yannick Benischke
 * @author www.java-uni.de
 * @see http://www.java-uni.de/index.php?Seite=86
 *
 */
public class SimpleQuickSort extends SortAlgorithm {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleQuickSort.class);

	private void qSort(double x[], int left, int right, ISortData sortData) {
		if (left < right) {
			int i = partition(x, left, right, sortData);
			qSort(x, left, i - 1, sortData);
			qSort(x, i + 1, right, sortData);
		}
	}

	private int partition(double x[], int left, int right, ISortData sortData) {
		int i, j;
		sortData.addShift();
		double pivot = x[right];
		i = left;
		j = right - 1;
		while (i <= j) {
			sortData.addComparison();
			if (x[i] > pivot) {
				// swap x[i] and x[j]
				SortUtil.swap(x, i, j, sortData);
				j--;
			} else
				i++;
		}
		// swap x[i] and x[right]
		SortUtil.swap(x, i, right, sortData);

		return i;
	}

	@Override
	public ISortData sortWithComparisonAndShiftMeasure(double[] array, ISortData sortData) {
		qSort(array, 0, array.length - 1, sortData);
		LOGGER.debug("sortWithComparisonAndShiftMeasure(...) -> {}",sortData);
		return sortData;
	}

}
