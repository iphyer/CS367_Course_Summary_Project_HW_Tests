
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (EmployeeDatabase.java)
// Files:            (EmployeeListIterator.java)
// Semester:         (CS 367) Spring 2018
//
// Author:           (Mingren Shen)
// Email:            (mshen32@wisc.edu)
// CS Login:         (mingren)
// Lecturer's Name:  (Charles Fischer)
// Lab Section:      (None)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// None
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//
// None
//
//////////////////////////// 80 columns wide ////////////////////////////////

import java.util.*;

/**
 * 
 *
 * The EmployeeListIterator class iterates the employees list
 *
 * @author Mingren Shen Copyright (2018)
 * @version 1.0
 * @see also Employee, EmployeeDatabase,InteractiveDBTest
 */

public class EmployeeListIterator implements Iterator<Employee> {
	
	///////////////////////////
	// Data Members (fields)
	///////////////////////////
	private ArrayList<Employee> eList;
	private int curPos;
	
	///////////////////////////
	// Constructors
	///////////////////////////
	/**
	 * Construct the object of EmployeeListIterator
	 * 
	 * @parameter EmployeeDatabase eDB
	 *            the EmployeeDatabase that calls this class to generate iterator
	 */
	public EmployeeListIterator(EmployeeDatabase eDB)
	{
		this.eList = eDB.employeeList;
		curPos = 0;
	}
	
	/**
	 * Whether EmployeeListIterator reaches the end of the employee list
	 * 
	 * @parameter none
	 * 
	 * @return true not reach the end of the employee list
	 *         false reach the end of the employee list
	 */
	@Override
	public boolean hasNext() {
		return curPos < this.eList.size();
	}
	
	/**
	 * Return the next employee object from employee list if not reach the end 
	 * of the employee list; throw NoSuchElementExcetption if reach the end of
	 * the employee list
	 * 
	 * @parameter none
	 * 
	 * @return the next employee of the list
	 *         
	 */
	@Override
	public Employee next() {
		if ( !hasNext() ) {
			throw new NoSuchElementException();
		}
		Employee curr = eList.get(curPos);
		curPos++;
		return curr;
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
		}

}
