package de.htwg_konstanz.sort.util;

import spock.lang.*;

/**
 * @author Philip Yannick Benischke
 *
 */
class SortDataTest extends Specification{
	
	def SortData data
	
	def setup(){
		data = new SortData()
	}
	def cleanup(){
		data = null
	}

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#addComparison()}.
	 */
	public void testAddComparison() {
		when: 
			data.addComparison()
		then:
			data.getComparisons() == 1
	}
	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#addComparison()}.
	 */
	public void testAddComparisonMoreTheOneTime() {
		when:
			data.addComparison()
			data.addComparison()
			data.addComparison()
		then:
			data.getComparisons() == 3
	}

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#addComparisons(int)}.
	 */
	@Unroll
	public void testAddComparisons() {
		expect:
		data.addComparisons(a)
		data.getComparisons() == a
		
		where:
		a | _
		0 | _
		5 | _
		3 | _
		1 | _
		4 | _
	}

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#addShift()}.
	 */
	public void testAddShift() {
		when: 
			data.addShift()
		then:
			data.getShifts() == 1		
	}
	
	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#addShift()}.
	 */
	public void testAddShiftMoreTheOneTime() {
		when:
			data.addShift()
			data.addShift()
			data.addShift()
		then:
			data.getShifts() == 3
	}

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#addShifts(int)}.
	 */
	@Unroll
	public void testAddShifts() {
		expect:
		data.addShifts(a)
		data.getShifts() == a
		
		where:
		a | _
		0 | _
		5 | _
		3 | _
		1 | _
		4 | _
	}

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#setTime(long)}.
	 */
	public void testSetTime() {
		when:
		data.setTime(Long.MAX_VALUE)
		then:
		data.getTime() == Long.MAX_VALUE
	}

	/**
	 * Test method for {@link de.htwg_konstanz.sort.util.SortData#toString()}.
	 */
	public void testToString() {
		when:
		data.addComparisons(30)
		data.addShifts(40)
		data.setTime(50)
		then:
		data.toString() == "SortData [comparisons=30, shifts=40, time=50]"
	}

}
