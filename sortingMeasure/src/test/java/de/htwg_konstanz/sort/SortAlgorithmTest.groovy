package de.htwg_konstanz.sort

import de.htwg_konstanz.sort.interfaces.*;
import de.htwg_konstanz.sort.bubbleSort.*;
import de.htwg_konstanz.sort.quickSort.*;
import spock.lang.*

class SortAlgorithmTest extends Specification{
	
	
	
	def "array is sorted"(SortAlgorithm algo){
		double[] doubleArray
		
		setup:
			assert doubleArray == null
			doubleArray = (double[])[2.0 , 1.2 , 54.36 , 135 , 47.7 ,  -43.1 , 6 , 31 , 4 , -68]
			assert doubleArray != null
			assert doubleArray.length != 0
		
		expect:
			"is measuered"(algo.sortWithMeasure(doubleArray))
			isSorted(doubleArray)
			
		cleanup:
			doubleArray = null
		
		where:
			algo << [new BubbleSort(), new SimpleQuickSort()]					
	}
	

	
	def "is measuered"(ISortData data){
		assert data != null
		assert data.comparisons > 0
		assert data.shifts > 0
		assert data.time > -1
		return true
	}
	
	
	
	def isSorted(double[] array){
		
		//no array
		if(array == null)
			return false
		//no element -> is sorted
		//one element -> is sorted
		if(array.length == 0 || array.length == 1)
			return true
		//get predecessor
		double predecessor = array[0]		
		for(int i = 1; i < array.length; i++){
			//if predecessor is bigger it is not sorted
			if(predecessor>array[i])
				return false
			//set new predecessor
			predecessor = array[i]
		}
		//if every element is in the right order return true
		return true
	}
	

}
