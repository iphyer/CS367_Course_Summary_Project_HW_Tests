///////////////////////////////////////////////////////////////////////////////
// Main Class File:  InteractiveDBTester
// File:             EmployeeDatabase.java
// Semester:         CS 367 - Fall 2016
//
// Author:           Justin High high@wisc.edu
// CS Login:         high
// Author 2:		 Aaron Gordner - gordner@wisc.edu
// CS Login:         gordner
// Lecturer's Name:  Charles Fischer

package written;
import java.util.*;
import given.Employee;

/**
 * Stores Employee objects into a database with their name and destination wishlists.
 * Is able to add and remove employees as well as destinations. 
 *
 * @author Justin High & Aaron Gordner
 */
public class EmployeeDatabase {
	
	ArrayList<Employee> empList;
	
	/**
	 * Constructs an empty employee database
	 *
	 * @param (parameter name) (Describe the first parameter here)
	 */
	public EmployeeDatabase() {
		empList = new ArrayList<Employee>();
	}
	
	// public methods required by assignment
	/**
	 * Adds employee e to the end of the database.
	 * If no user is specified then an
	 * IllegalArgumentException is thrown
	 *
	 * @param e - Employee's username
	 * @return (description of the return value)
	 */
	public void addEmployee(String e) throws IllegalArgumentException {
		// Adds a new given.Employee to the database
		Employee newEmployee = null;
		if (e == null) {
			throw new IllegalArgumentException();
		}
		else {
			newEmployee = new Employee(e);
			empList.add(newEmployee);
		}
	}
	
	/**
	 * Adds destination d to the wish list for employee e
	 * If the employee is not in the database then an
	 * IllegalArgumentException is thrown.
	 *
	 * @param e - Employee's username
	 * @param d - Destination to be added to wish list
	 * @return (description of the return value)
	 */
	public void addDestination(String e, String d) throws IllegalArgumentException {
		if (this.containsEmployee(e) == false) {
			throw new IllegalArgumentException();
		}
		else {
			Employee tempEmployee = this.retreiveEmployee(e);
			ArrayList<String> tempDests = (ArrayList<String>) tempEmployee.getWishlist();
			//perhaps need to check if the destination is already in the wishlist before the add?
			tempDests.add(d);
		}
	}
	
	/**
	 * Determines if an employee e is in the database.
	 *
	 * @param e - Employee's username
	 * @return true if employee is found
	 */
	public boolean containsEmployee(String e) throws IllegalArgumentException {
		// 	TODO: review - agree on checking null. probably throw this exc
		if (e == null)
		{
			throw new IllegalArgumentException();
		}
		Employee tempEmployee = this.retreiveEmployee(e);
		if (tempEmployee == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if destination d is in at least one wish list
	 *
	 * @param d - Destination
	 * @return true if destination is in at least one wish list
	 */
	public boolean containsDestination(String d) throws IllegalArgumentException {
		// 	TODO: review; probably throw this exception
		if (d == null)
		{
			throw new IllegalArgumentException();
		}
		Iterator<Employee> empIter = this.iterator();
		while (empIter.hasNext()) {
			Employee tempEmp = empIter.next();
			if (this.hasDestination(tempEmp.getUsername(), d)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determines if destination d is on the wish list for
	 * employee e.
	 *
	 * @param e - Employee's username
	 * @param d - Destination
	 * @return true if destination is on employee's with list
	 * 			false if employee not found
	 */
	public boolean hasDestination(String e, String d) {
		// Returns true if and only if destination d is in the wish list for employee e.
		// If employee e is not in the database, return false.
		if (this.containsEmployee(e)) {
			Employee tempEmp = this.retreiveEmployee(e);
			ArrayList<String> empDests = (ArrayList<String>) tempEmp.getWishlist();
			Iterator<String> destIter = empDests.iterator();
			while (destIter.hasNext()) {
				String dest= destIter.next();
				if (dest.equals(d)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Finds list of employees who have destination d in their wish list 
	 *
	 * @param d - Destination
	 * @return List of employees with destination. If no one has the
	 * 			destination then a null list is returned
	 */
	public List<String> getEmployees(String d) {
		ArrayList<String> listEmployees = new ArrayList<String>();
		Iterator<Employee> empIter = this.iterator();
		while (empIter.hasNext()) {
			Employee tempEmp = empIter.next();
			ArrayList<String> empWl = (ArrayList<String>) tempEmp.getWishlist();
			if (empWl.contains(d)) {
				listEmployees.add(tempEmp.getUsername());
				}
			}
		return listEmployees;
	}
	
	/**
	 * Finds wishlist for employee e
	 *
	 * @param e - Employee's username
	 * @return Wish list for given employee
	 */
	public List<String> getDestinations(String e){
		Employee tempEmp = this.retreiveEmployee(e);
		if (tempEmp.getUsername().isEmpty()) {
			return null;
		}
		return tempEmp.getWishlist();
	}
	
	/**
	 * Iterates over employees in the database in the order they were added.
	 *
	 * @return Iterator over employees in the database.
	 */
	public Iterator<Employee> iterator(){
		return new EmployeeListIterator(this);
	}
	
	/**
	 * Removes employee e from the database
	 *
	 * @param e - Employee's username
	 * @return true if removal succeeded, false otherwise
	 */
	public boolean removeEmployee(String e) throws IllegalArgumentException {
		// TODO: review exception
		if (e == null)
		{
			throw new IllegalArgumentException();
		}
		
		return this.empList.remove(this.retreiveEmployee(e));
	}
	
	/**
	 * Removes destination d from all wish lists
	 *
	 * @param d - Destination
	 * @return true if removal succeeded
	 */
	public boolean removeDestination(String d){
		Boolean destInDb = false;
		Iterator<Employee> empIter = this.iterator();
		while (empIter.hasNext()) {
			Employee tempEmp = empIter.next();
			if (this.removeWlForEmp(tempEmp, d)) {
				destInDb = true;
			}
		}
		return destInDb;
	}
	
	/**
	 * Finds number of employees in the database
	 *
	 * @return number of employees in the database
	 */
	public int size(){
		return this.empList.size();
	}
	
	// private methods
	/**
	 * Iterates through the database to find given employee
	 *
	 * @param e - Employee's username
	 * @return employees username if they exist
	 */
	private Employee retreiveEmployee(String e) throws IllegalArgumentException {
		Iterator<Employee> empIter = this.iterator();
		while (empIter.hasNext()) {
			Employee tempEmp = empIter.next();
			if (tempEmp.getUsername().equals(e)) {
				return tempEmp;
			}
		}
		return null;
	}
	
	/**
	 * Removes destination d from employee e
	 *
	 * @param e - Employee's username
	 * @param d - Destination
	 * @return true if destination was removed
	 */
	private Boolean removeWlForEmp (Employee e, String d) {
		ArrayList<String> empWl = (ArrayList<String>) e.getWishlist();
		if (empWl.isEmpty() || !empWl.contains(d)) {
			return false;
		}
		Iterator<String> wlIter = empWl.iterator();
		while (wlIter.hasNext()) {
			String s = wlIter.next();
			if (s.equals(d)) {
				wlIter.remove();
			}
		}
		return true;
	}
}
