package de.htwg_konstanz.sort.util;

import de.htwg_konstanz.sort.interfaces.ISortData;


public class SortUtil {
	/**
	 * private constructor to avoid instantiation of the class (static methods only)
	 */
	private SortUtil(){}
	
	/**
	 * swaps two elements in a array and adds three shifts to the sortData
	 * @param array the array were the elements are located
	 * @param first the index of the first element
	 * @param secound the index of the second element
	 */
	public static void swap(double[] array, int first, int secound){
		synchronized (array) {
			double temp;
	        temp = array[first]; 
            array[first] = array[secound]; 
            array[secound] = temp;
		}		
	}

	/**
	 * swaps two elements in a array and adds three shifts to the sortData
	 * @param array the array were the elements are located
	 * @param first the index of the first element
	 * @param secound the index of the second element
	 * @param sortData the sortData element 
	 */
	public static void swap(double[] array, int first, int secound, ISortData sortData) {
		sortData.addShifts(3);
		swap(array, first, secound);		
	}

}
