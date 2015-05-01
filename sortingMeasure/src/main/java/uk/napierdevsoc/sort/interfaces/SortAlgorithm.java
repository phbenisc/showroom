package uk.napierdevsoc.sort.interfaces;

import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.sort.util.SortData;
import uk.napierdevsoc.sort.util.SortDataMock;

public abstract class SortAlgorithm {
	
	static final  Logger logger = LoggerFactory.getLogger(SortAlgorithm.class);
	
	public void sort(double[] array){
		sortWithComparisonAndShiftMeasure(array, new SortDataMock());
	}
	
	/**
	 * sort an double array
	 * @param array array to Sort. the order of the array will be altered.
	 * @return SortData to profile the efficiency of the used algorithm
	 */
	public ISortData sortWithComparisonAndShiftMeasure(double[] array){
		return sortWithComparisonAndShiftMeasure(array, new SortData());
	}
	/**
	 * sort an double array
	 * @param array to Sort. the order of the array will be altered.
	 * @param sortData to profile the efficiency of the used algorithm
	 * @return the some sortData Object witch was inserted but filled with data
	 */
	public abstract ISortData sortWithComparisonAndShiftMeasure(double[] array, ISortData sortData);
	
	public ISortData sortWithMeasure(double[] array){
		//get Stopwatch
		StopWatch stopWatch = new Slf4JStopWatch(logger, Slf4JStopWatch.TRACE_LEVEL);
		//sort 
		ISortData data = sortWithComparisonAndShiftMeasure(array);
		//stop 
		stopWatch.stop();
		//add time to data
		data.setTime(stopWatch.getElapsedTime());
		return data;
	}

}
