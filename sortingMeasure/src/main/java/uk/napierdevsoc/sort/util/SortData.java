package uk.napierdevsoc.sort.util;

import uk.napierdevsoc.sort.interfaces.ISortData;

public class SortData implements ISortData {
	
	@Override
	public String toString() {
		return "SortData [comparisons=" + comparisons + ", shifts=" + shifts + ", time=" + time + "]";
	}
	private long comparisons = 0;
	private long shifts = 0;
	private long time = 0;
	
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#addComparison()
	 */
	@Override
	public synchronized void addComparison(){
		addComparisons(1);
	}
	
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#addComparisons(int)
	 */
	@Override
	public synchronized void addComparisons(int comparisons){
		this.comparisons += comparisons;
	}
	
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#addShift()
	 */
	@Override
	public synchronized void addShift(){
		addShifts(1);
	}
	
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#addShifts(int)
	 */
	@Override
	public synchronized void addShifts(int shifts){
		this.shifts += shifts;
	}
	
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#setTime(long)
	 */
	@Override
	public synchronized void setTime(long time){
		this.time = time;
	}
	
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#getComparisons()
	 */
	@Override
	public long getComparisons() {
		return comparisons;
	}
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#getShifts()
	 */
	@Override
	public long getShifts() {
		return shifts;
	}
	/* (non-Javadoc)
	 * @see uk.napierdevsoc.sort.util.ISortData#getTime()
	 */
	@Override
	public long getTime() {
		return time;
	}

}
