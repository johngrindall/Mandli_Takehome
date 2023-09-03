//Package
package com.rangecontainer;

//Imports
import java.util.*;

/**
 * Class that defines a container for whole number ranges. 
 * Utilizes a self balancing BST via TreeSet Class for storage of ranges.
 * 
 * @author John McCue Grindal
 * */
public class RangeContainer {
	
	//Class variables
	private TreeSet<Range> ranges;
	
	/**
	 * Constructor that initializes a RangeContainer object, creates
	 * a TreeSet object to store ranges.
	 * 
	 * */
	RangeContainer(){
		this.ranges = new TreeSet<>();
	}
	
	/**
	 * Print method, prints a string representation if ranges.
	 * 
	 * */
	public void print(){
		System.out.println(this.ranges);
	}
	
	/**
	 * Checks whether a given int is contained within the range container.
	 * Since TreeSet is a self balancing BST implementation, searching occurs in 
	 * O(log(n)) runtime, where n is the # of ranges contained in the container. 
	 * 
	 * @param search: int to be searched for.
	 * @return Returns true if int contained in ranges, false if not.
	 * */
	public boolean contains(int search) {
		
		//Retreive floor and ceiling
		Range searchRange = new Range(search, search);
		Range floor = ranges.floor(searchRange);
		Range ceiling = ranges.ceiling(searchRange);
		
		//Check to see if search value contained in floor/ceiling
		if(floor.inRange(search)) {
			return true;
		}else if(ceiling.inRange(search)){
			return true;
		}else {
			return false;
		}
		
	}	
	
	/**
	 * Adds an integer to the ranges, also performs merging operations 
	 * 
	 * @param input: integer to be added to ranges.
	 * @return Returns true if int added, false if not.
	 * */
	public boolean addInt(int input) {
	
		//Locate ranges either containing or abutting integer incoming
		Range floor = ranges.floor(new Range(input, input));
		Range ceiling = ranges.ceiling(new Range(input, input));
		
		//Return if int contained in floor or ceiling range
		if(floor != null && floor.inRange(input)) return false;
		if(ceiling != null && floor.inRange(input)) return false;
		
		//Calculate whether new int can merge with floor or ceiling range (within 1)
		boolean ceilingMerge = ceiling != null && input + 1 == ceiling.start; 
		boolean floorMerge = floor != null && input - 1 == floor.end;
		
		//Determine which merge operation should occur and 
		if(floorMerge && ceilingMerge) {
			
			//Can merge with floor and ceiling
			
			//Remove floor and ceiling range
			ranges.remove(floor);
			ranges.remove(ceiling);
			
			//Add new merged range
			Range merge = new Range(floor.start, ceiling.end);
			ranges.add(merge);
			return true;
			
		}else if(floorMerge && !ceilingMerge) {
			
			//Can merge with floor, but not with ceiling
			
			//Remove floor range
			ranges.remove(floor);
			
			//Create and add new merged range
			Range merge = new Range(floor.start, input);
			ranges.add(merge);
			return true;
			
			
		}else if(ceilingMerge && !floorMerge){
			
			//Can merge with ceiling, but not with floor
			
			//Remove ceiling range
			ranges.remove(ceiling);
			
			//Create and add merged range
			Range merge = new Range(input, ceiling.end);
			ranges.add(merge);
			return true;
			
		}else {
			
			//No floor/ceiling, adding base range
			Range baseRange = new Range(input, input);
			ranges.add(baseRange);
			return true;
			
		}
			
	}
	
	/**
	 * Removes an integer from ranges.
	 * 
	 * @param remove: integer to be removed from ranges.
	 * @return Returns true if int removed, false if not.
	 * 
	 * */
	public boolean removeRange(int remove) {
		
		//Check if removal int is present, return if not
		
		Range floor = ranges.floor(new Range(remove, remove));
		Range ceiling = ranges.ceiling(new Range(remove, remove));
		
		boolean floorPresent = floor != null;
		boolean ceilingPresent = ceiling != null;
		
		//Check to see that the number is present, if not return
		if(!floorPresent && !ceilingPresent) return false;
		
		
		if(floorPresent && floor.inRange(remove)) {
			
			//In range of floor
			
			//Form new ranges 
			Range split1 = new Range(floor.start, remove - 1);
			Range split2 = new Range(remove + 1, floor.end);
			
			//Remove old range, add new one
			this.ranges.remove(floor);
			this.ranges.add(split1);
			this.ranges.add(split2);
			
			return true; 
			
		}else if(ceilingPresent && ceiling.inRange(remove)) {
			
			//In range of ceiling 
			
			//Form new ranges 
			if(ceiling.start == remove) {
				Range split = new Range(remove + 1, ceiling.end);
				this.ranges.remove(ceiling);
				this.ranges.add(split);
				return true;
			}else if(ceiling.end == remove) {
				Range split = new Range(ceiling.start, remove - 1);
				this.ranges.remove(ceiling);
				this.ranges.add(split);
				return true;
			}else if(ceiling.end == remove && ceiling.start == remove) {
				this.ranges.remove(ceiling);
				return true;
			}else {
				Range split1 = new Range(ceiling.start, remove - 1);
				Range split2 = new Range(remove + 1, ceiling.end);
				//Remove old range, add new ones
				this.ranges.remove(ceiling);
				this.ranges.add(split1);
				this.ranges.add(split2);
				return true; 
			}
			
		}
		 
		return false;
		
	}
		
		
}
	
	
	
