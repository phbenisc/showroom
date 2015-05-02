package de.htwg_konstanz.sort.interfaces;

public interface ISortData {

	public abstract void addComparison();

	public abstract void addComparisons(int comparisons);

	public abstract void addShift();

	public abstract void addShifts(int shifts);

	public abstract void setTime(long time);

	public abstract long getComparisons();

	public abstract long getShifts();

	public abstract long getTime();

}