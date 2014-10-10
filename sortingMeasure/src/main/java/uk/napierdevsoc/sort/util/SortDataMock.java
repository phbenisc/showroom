package uk.napierdevsoc.sort.util;

import uk.napierdevsoc.sort.interfaces.ISortData;

public class SortDataMock implements ISortData{

	@Override
	public void addComparison() {}

	@Override
	public void addComparisons(int comparisons) {}

	@Override
	public void addShift() {}

	@Override
	public void addShifts(int shifts) {}

	@Override
	public void setTime(long time) {}

	@Override
	public long getComparisons() {return 0;}

	@Override
	public long getShifts() {return 0;}

	@Override
	public long getTime() {return 0;}

}
