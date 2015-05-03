package de.htwg_konstanz.sort.util;

import de.htwg_konstanz.sort.interfaces.ISortData
import spock.lang.*;

/**
 * @author Philip Yannick Benischke
 *
 */
class SortUtilTest extends Specification{

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortUtil#swap(double[], int, int)}.
	 */	
	@Unroll
	def testSwapDoubleArrayIntInt() {
		expect:
			SortUtil.swap(doubleArray, a, b)
			c == doubleArray[b]
			d == doubleArray[a]
	
		where:
			doubleArray = (double[])[-10 , -8 , -6 , -4 , -2 ,  0 , 2 , 4 , 6 , 8]
		
			a | b 
			0 | 2 
			5 | 2 
			3 | 3 
			3 | 6 
			1 | 4
			4 | 8
			
			c = doubleArray[a]
			d = doubleArray[b]
	}

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortUtil#swap(double[], int, int, de.htwg_konstanz.sort.interfaces.ISortData)}.
	 */	
	def testSwapDoubleArrayIntIntISortData() {
		expect:
			data.shifts == 0
			SortUtil.swap(doubleArray, 0, 1, data)
			data.shifts == 3
	
		where:
			data = new SortData();
			doubleArray = (double[])[-10 , -8 , -6 , -4 , -2 ,  0 , 2 , 4 , 6 , 8]
	}
}
