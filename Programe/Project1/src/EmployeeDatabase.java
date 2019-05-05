///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (CS367 Programming Assignment 1)
// Files:            (EmployeeDatabase.java)
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
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*; // import needed library for the program

/**
 * 
 *
 * The EmployeeDatabase class stores the employees and the user can add, view,
 * update, delete, edit both the employees and their destinations.
 *
 * @author Mingren Shen Copyright (2018)
 * @version 1.0
 * @see also Employee, InteractiveDBTest
 */

public class EmployeeDatabase 
	{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	// The list of employees
	ArrayList<Employee> employeeList;

	///////////////////////////
	// Constructors
	///////////////////////////

	/**
	 * Construct the object of EmployeeDatabase
	 * 
	 * No parameter needed
	 */
	public EmployeeDatabase() {
		employeeList = new ArrayList<Employee>();
	}

	///////////////////////////
	// Methods
	///////////////////////////

	/**
	 * Add an employee with the given username e to the end of the database. 
	 * 
	 * If an employee with username e is already in the database, just return.
	 * 
	 * If username e is null then throw an IllegalArgumentException
	 * 
	 * @param e a string that is a username of the employee
	 *         
	 * @return none
	 */
	void addEmployee(String e) 
	{
		// Test whether e is null
		if ( e == null ) {
			throw new IllegalArgumentException();
		}
		else if( this.containsEmployee(e) )
		{
			// employee of username e already exists
			return;
		}
		else {
			// employee of username e is new to the database
			Employee newEmployee = new Employee(e);
			this.employeeList.add(newEmployee);
		}

	}
	
	
	/**
	 * Add the given destination d to the wish list for employee e in the database. 
	 * 
	 * If employee e is not in the database throw a java.lang.IllegalArgumentException. 
	 * 
	 * If d is already in the wish list for employee e, just return.
	 * 
	 * If username e or destination d is null then throw an IllegalArgumentException
	 * 
	 * @param e a string that is a username of the employee
	 *        d a string that is the destination of employee of username e
	 * @return none
	 */
	void addDestination(String e, String d) 
	{
		// Test whether d or e is null	
		if ( e == null || d == null ) {
			throw new IllegalArgumentException();
		}
		else if( !this.containsEmployee(e) ) 
		{
			// employee of username e does not exist
			throw new IllegalArgumentException();
		} // After this, e, d are not null and the employee exists
		else if( this.getDestinations(e).contains(d) )
		{
			// d is already in employee e's destination list
			return;
		}
		else {
			// destination d is new to the existing user e, so add it
			Employee tmp = this.accessEmployee(e);
			tmp.getWishlist().add(d);
		}

	}
	
	/**
	 * Return true if and only if employee e is in the database.
	 * 
	 * @param e a string that is a username of the employee
	 *         
	 * @return true user e exists
	 *         false user e does not exist
	 */
	boolean containsEmployee(String e) 
	{
		// Test whether e is null
		if ( e == null )
		{
			throw new IllegalArgumentException();
		}
		// create the iterator of the employee list
		Iterator<Employee> employIter = this.iterator();
		while ( employIter.hasNext() ) {
			Employee empTmp = employIter.next();
			if ( empTmp.getUsername().equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return true if and only if destination d appears 
	 * in at least one employee's wish list in the database.
	 * 
	 * @param e a string that is a username of the employee
	 *         
	 * @return true destination d exists
	 *         false destination d does not exist
	 */
	boolean containsDestination(String d)
    {
		// Test whether d is null
		if ( d == null )
		{
			throw new IllegalArgumentException();
		}
		// create the iterator of the employee list
		Iterator<Employee> employIter = this.iterator();
		while ( employIter.hasNext() ) 
		{
			Employee empTmp = employIter.next();
			// for each employee test whether they have destination d
			// using the hasDestination method of EmplpyeeDatabase
			if (this.hasDestination(empTmp.getUsername(), d)) 
			{
				return true;
			}
		}
		return false;
    }
	
	/**
	 * Returns true if and only if destination d is in the wish list for employee e. 
	 * If employee e is not in the database, return false.
	 * 
	 * @param e a string that is a username of the employee
	 * @param d a string that is the destination of employee of username e
	 *         
	 * @return true if and only if destination d is in the wish list for employee e.
	 *         false if employee not found or d is not in e's wishlist
	 */
	boolean hasDestination(String e, String d)
	{
		// Test whether e or d is null
		if ( d == null || e == null )
		{
			throw new IllegalArgumentException();
		}
		// create the iterator of the employee list
		Iterator<Employee> employIter = this.iterator();
		while ( employIter.hasNext() ) 
		{
			Employee empTmp = employIter.next();
			// for each employee get its wish list
			ArrayList<String> empWishlist = (ArrayList<String>) empTmp.getWishlist();
			// create iterator for wish list
			Iterator<String> wishlistIter = empWishlist.iterator();
			if (wishlistIter.hasNext()) 
			{
				// get each destination 
				String tmpDestination = wishlistIter.next();
				// test whether has the destination equal d
				if ( tmpDestination.equals(d) )
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Return the list of employees who have destination d in their wish list. 
	 * If destination d is not in the database, return a null list. 
	 *
	 * @param d destination
	 * @return List of employees with destination d.
	 * 
	 */
	public List<String> getEmployees(String d) {
		// Test whether d is null
		if ( d == null )
		{
			throw new IllegalArgumentException();
		}
		// create the iterator of the employee list
		Iterator<Employee> employIter = this.iterator();
		ArrayList<String> list_same_d_Employees = new ArrayList<String>();
		
		while (employIter.hasNext()) {
			Employee empTmp = employIter.next();
			// for each employee get its wish list
			ArrayList<String> empWishlist = (ArrayList<String>) empTmp.getWishlist();
			// test whether empWishlist contains d
			if (empWishlist.contains(d)) {
				// contains d
				// adding this employee to final result list
				list_same_d_Employees.add(empTmp.getUsername());
				}
			}
		
		if (list_same_d_Employees.size() == 0) 
		{
			return null;
		}
		else
		{
			return list_same_d_Employees;
		}
		
	}
	
	/**
	 * Return the wish list for the employee e.
	 * If an employee e is not in the database, return null.
	 * 
	 * @param e a string that is a username of the employee
	 *         
	 * @return eWishlist the wish list of employee e
	 */
	List<String> getDestinations(String e)
	{
		// Test whether e is null
		if ( e == null )
		{
			throw new IllegalArgumentException();
		}
		// testing whether employee e exists
		if (this.containsEmployee(e)) {
			// using private helping methods to get the employee
			Employee empTmp = this.accessEmployee(e);
			return empTmp.getWishlist();
		}
		else 
		{
			return null;
		}
	}
	
	/**
	 * Return an Iterator over the Employee objects in the database. 
	 * 
	 * @param None
	 *         
	 * @return Iterator of Employee
	 * 
	 */
	public Iterator<Employee> iterator()
	{
		// using EmplpyeeListIterator class to create the iterator
		return new EmployeeListIterator(this);
	}
	
	/**
	 * Remove employee e from the database. 
	 * If employee e is not in the database, return false; 
	 * otherwise (i.e., the removal is successful) return true.
	 * 
	 * @param e a string that is a username of the employee
	 *         
	 * @return true removing succeed
	 *         false employee e is not in the database, return false;
	 */
	boolean removeEmployee(String e)
	{
		// Test whether e is null
		if ( e == null )
		{
			throw new IllegalArgumentException();
		}
		// testing whether employee e exists
		if (this.containsEmployee(e)) {
			// removes the first occurrence of e from this list
			// because employee usernames and airport id codes are unique
			this.employeeList.remove(this.accessEmployee(e));
			return true;
		}
		else 
		{
			return false;
		}
	}

	/**
	 * Remove destination d from the database, 
	 * i.e., remove destination d from every wish list in which it appears. 
	 * 
	 * @param d destination
	 *         
	 * @return true the removal is successful
	 *         false destination d is not in the database, return false; 
	 */
	boolean removeDestination(String d)
	{
		// Test whether d is null
		if ( d == null )
		{
			throw new IllegalArgumentException();
		}
		// testing whether employee e exists
		if (this.containsDestination(d)) {
			// create the iterator of the employee list
			Iterator<Employee> employIter = this.iterator();
			while (employIter.hasNext()) 
			{
				// get the next employee
				Employee empTmp = employIter.next();
				// for each employee test whether this employee contains d
				if ( this.hasDestination(empTmp.getUsername(), d))
				{
					// this employee contains d
					// get his or her wish list					
					ArrayList<String> empWishlist = (ArrayList<String>) empTmp.getWishlist();
					// Because there are no duplicate destinations within a single 
					// employee's wish list. We can just remove d.
					empWishlist.remove(d);
				}
			}
			// after the while loop, all employees' wish list have been updated
			return true;
		}
		else 
		{
			// d is not in any user's wish list
			return false;
		}
	}
	
	/**
	 * Return the number of employees in this database..
	 * 
	 * @param none
	 *         
	 * @return the number of employees in the database
	 */
	int size()
	{
		return this.employeeList.size();
	}

	// helper methods
	
	/**
	 * private access methods that help get the employee according to their
	 * username
	 * 
	 * If an employee with username e is already in the database,
	 * return that user 
	 * 
	 * If no username e is found then return null
	 * 
	 * @param e a string that is a username of the employee
	 *         
	 * @return none
	 */
	
	private Employee accessEmployee(String e)
	{
		// Test whether e is null
		if ( e == null )
		{
			throw new IllegalArgumentException();
		}
		Iterator<Employee> employIter = this.iterator();
		while ( employIter.hasNext() ) {
			Employee empTmp = employIter.next();
			if ( empTmp.getUsername().equals(e)) {
				return empTmp;
			}
		}
		return null;
	}

}
