package com.rangecontainer;

/**
 * Simple class that defines a range via end and start point.
 * Implements the comparable class for easy comparison of ranges.
 * 
 * @author John McCue Grindal
 * */
public class Range implements Comparable<Range> {
	
	//Class variables
	public int start;
	public int end;
	
	/**
	 * Constructor which initializes a new Range object.
	 * 
	 * @param start: start for range
	 * @param end: end for range
	 * */
	Range(int start, int end){
		this.start = start;
		this.end = end;
	}

	/**
	 * Override of toString class.
	 * 
	 * @return Returns string implementation of range
	 * */
	@Override
	public String toString(){
		return "[" + this.start + ", " + this.end + "]";
	}
	
	/**
	 * Checks whether a given int is in range
	 * 
	 * @param input: int to be checked
	 * @return Returns true if in range, false if not
	 * */
	public boolean inRange(int input) {
		return input <= this.end && input >= this.start;
	}
	
	/**
	 * Override of comparison method, compares two ranges using 
	 * start value as primary measure, and end value as a tie breaker.
	 * 
	 * @param comparison: range being compared to this.
	 * @return Returns an integer 1 if greater, -1 if less, 0 if equal.
	 * */
	@Override
	public int compareTo(Range comparison) {
		
		//Compare range starting points
		if(this.start < comparison.start) {
			return -1;
		}else if(this.start > comparison.start){
			return 1;
		}  
		
		//Compare end points as secondary measure
		if(this.end < comparison.end) {
			return -1;
		}else if(this.end > comparison.end) {
			return 1;
		}else{
			return 0;
		}
		
	}
	
}