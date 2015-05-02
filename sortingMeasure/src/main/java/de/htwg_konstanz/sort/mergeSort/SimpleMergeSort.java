package de.htwg_konstanz.sort.mergeSort;

import de.htwg_konstanz.sort.interfaces.ISortData;
import de.htwg_konstanz.sort.interfaces.SortAlgorithm;

public class SimpleMergeSort extends SortAlgorithm {

	@Override
	public ISortData sortWithComparisonAndShiftMeasure(double[] array, ISortData sortData) {
		double[] tmp = new double[array.length];
		mergeSort(array, tmp,  0,  array.length - 1, sortData);
		return sortData;
	}

	private void mergeSort(double[] array, double[] tmp, int left, int right, ISortData sortData) {
		if( left < right ){
			int center = (left + right) / 2;
			mergeSort(array, tmp, left, center, sortData);
			mergeSort(array, tmp, center + 1, right, sortData);
			merge(array, tmp, left, center + 1, right, sortData);
		}
		
	}

	private void merge(double[] array, double[] tmp, int left, int right, int rightEnd, ISortData sortData) {
		int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(array[left] <= array[right])
                tmp[k++] = array[left++];
            else
                tmp[k++] = array[right++];
        	sortData.addComparison();
        while(left <= leftEnd){    // Copy rest of first half
            tmp[k++] = array[left++];
            sortData.addShift();
        }
        while(right <= rightEnd){  // Copy rest of right half
            tmp[k++] = array[right++];
            sortData.addShift();
        }
        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--){
            array[rightEnd] = tmp[rightEnd];	
            sortData.addShift();
        }
	}

}
