package uk.napierdevsoc.sort.util;

import uk.napierdevsoc.sort.interfaces.ISortData;


public class SortUtil {
	
	private SortUtil(){}
	
	
	public static void swap(double[] array, int first, int secound){
		synchronized (array) {
			double temp;
	        temp = array[first]; 
            array[first] = array[secound]; 
            array[secound] = temp;
			
		}
		
	}


	public static void swap(double[] array, int first, int secound, ISortData sortData) {
		sortData.addShifts(3);
		swap(array, first, secound);		
	}

}
