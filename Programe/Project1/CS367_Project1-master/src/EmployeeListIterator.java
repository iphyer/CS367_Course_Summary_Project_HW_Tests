///////////////////////////////////////////////////////////////////////////////
// Main Class File:  InteractiveDBTester
// File:             EmployeeListIterator
// Semester:         CS 367 - Fall 2016
//
// Author:           Justin High high@wisc.edu
// CS Login:         high
// Author 2:		 Aaron Gordner - gordner@wisc.edu
// CS Login:         gordner
// Lecturer's Name:  Charles Fischer

import java.util.*;


public class EmployeeListIterator implements Iterator<Employee> {
	
	// fields
	private List<Employee> empList;
	private int curPos;

	public EmployeeListIterator(EmployeeDatabase ed) {
		this.empList = ed.empList;
		curPos = 0;
	}

	@Override
	public boolean hasNext() {
		return curPos < empList.size();
	}

	@Override
	public Employee next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Employee result = empList.get(curPos);
		curPos++;
		return result;
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
