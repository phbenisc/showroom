package uk.napierdevsoc.sort.interfaces;

import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.napierdevsoc.sort.util.SortData;
import uk.napierdevsoc.sort.util.SortDataMock;

public interface ISortAlgorithm {
	
	static final  Logger logger = LoggerFactory.getLogger(ISortAlgorithm.class);
	
	default void sort(double[] array){
		sortWithComparisonAndShiftMeasure(array, new SortDataMock());
	}
	
	/**
	 * sort an double array
	 * @param array array to Sort. the order of the array will be altered.
	 * @return SortData to profile the efficiency of the used algorithm
	 */
	default ISortData sortWithComparisonAndShiftMeasure(double[] array){
		return sortWithComparisonAndShiftMeasure(array, new SortData());
	}
	ISortData sortWithComparisonAndShiftMeasure(double[] array, ISortData sortData);
	
	default ISortData sortWithMeasure(double[] array){
		StopWatch stopWatch = new Slf4JStopWatch(logger, Slf4JStopWatch.TRACE_LEVEL);
		ISortData data = sortWithComparisonAndShiftMeasure(array);
		stopWatch.stop();
		data.setTime(stopWatch.getElapsedTime());
		return data;
	}

}
