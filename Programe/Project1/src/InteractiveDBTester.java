
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class InteractiveDBTester {

	// make the Employee database a static data member so it does not have to
	// be passed to each help method
	// It is protected so that subclasses representing particular testers can
	// access it

	protected static EmployeeDatabase employeeDB = new EmployeeDatabase();

	// Initialize DB from external data file
	protected static void populateDB(String[] args) {

		// Step 1: check whether exactly one command-line argument is given
		// if not given, display "Please provide input file as command-line argument"
		// and quit.
		if (!(args.length == 1)) {
			System.out.println("Please provide input file as command-line argument");
			return;
		}

		// Step 2: check whether the input file exists and is readable
		File inputFile = new File(args[0]);
		Scanner inputFileScanner = null;
		try {
			// Create a Scanner from the file.
			// This statement can cause a FileNotFoundException.
			inputFileScanner = new Scanner(inputFile);
			// test whether the file is readable
			// simply test whether the hasNext is ture
			if (!inputFileScanner.hasNext()) {
				System.out.println("Error: Cannot access input file");
				inputFileScanner.close(); // close file before exists
				return;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: Cannot access input file");
			return; // not successfully created inputFileScanner
					// no need to close file
		}

		// Step 3: load the data from the input file and use it to construct a
		// Employee database
		try {
			// process the information in EmployeeData.txt line by line
    		while (inputFileScanner.hasNextLine()) {
    			// current lines
        		String currentLine = inputFileScanner.nextLine();
        		// split current into string arrays
        		String[] currentLineArray = currentLine.split(",");
        		
        		// create new Employee instances from current line data
        		// we need the name of the employee and their wish list
        		
        		// get employee's name and turn it into lower case
        		String strName = currentLineArray[0].toLowerCase();
        		// add into employee list
        		try
        		{
        			employeeDB.addEmployee(strName);	  			
        		}
        		catch (IllegalArgumentException e) {
        			System.out.println("e == null" + e);
        		}
        		
        		// Add destination into this employ's wish list
        		// the destination starting form the index 1 of the String array
        		for ( int i = 1; i < currentLineArray.length; ++i) 
        		{
        			// get the destination at index i and turn it into lower case
        			String strDestination = currentLineArray[i].toLowerCase();
        			try 
        			{
            			// add current destination into current employee's wish list
            			employeeDB.addDestination(strName ,strDestination);
        			}
        			catch (IllegalArgumentException e) {
        				System.out.println("e == null || d == null \n OR \n" 
        							       + "employee e is not in the database" + e);
        			}

        		}

    		}
    	}
    	catch (Exception e) {
    		System.out.println("Error when creating the database from the txt file");
    		
    	}
    	inputFileScanner.close(); // close the opened files

	}

	static boolean GUIactive; // is GUI tester active?

	// Methods that implement GUI buttons or testing actions
	
	
	/**
	 * Find certain employee
	 * 
	 * If employee is not in the database, return "employee not found".
	 * Otherwise, find employee and return the employee (on one line) in the format:
	 * employee:destination1,destination2,destination3
	 * 
	 * @param employee a string that is a username of the employee
	 *         
	 * @return a string that shows the content of this employee's wish list or not found
	 */
	protected static String pushFind(String employee) {
		// test whether employee is null
		if ( employee == null ) 
		{
			throw new IllegalArgumentException();
		}
		
		// test whether employee is in the database
		if ( employeeDB.containsEmployee( employee.toLowerCase() ) ) 
		{
			// create the returning string
			String strResult = employee.toLowerCase() + ":";
			// get wish list
			ArrayList<String> destination_list = (ArrayList<String>) employeeDB.getDestinations(employee.toLowerCase());
			// create iterator of the list
			Iterator<String> listIter = destination_list.iterator();
			while ( listIter.hasNext() )
			{
				// add the destination together
				strResult += listIter.next();
				// the last destination needs no comma after it
    			if (listIter.hasNext()) {
    				strResult += ",";
    			}
			}
			strResult += "\n" + "" ;
			return strResult;
		}
		else
		{
			return new String( "employee not found\n" + "");
		}
	}

	/**
	 * Delete specific destination in all employees' wish list
	 * 
	 * If destination is not in the database, return "destination not found". 
	 * Otherwise, discontinue destination (i.e., remove the destination from 
	 * all the wish lists in which it appears) and return "destination discontinued".
	 * 
	 * @param destination a destination that wanted to remove
	 *         
	 * @return "destination not found" if not find the destination
	 *         "destination discontinued" if successfully removed
	 * 
	 */
	protected static String pushDiscontinue(String destination)
	{
		// test whether destination is null
		if (destination == null) {
			throw new IllegalArgumentException();
		}

		// Remove destination using removeDestination method of EmployeeDatabse
		// true : destination discontinued
		// false : destination not found
		if (employeeDB.removeDestination(destination.toLowerCase())) {
			return new String("destination discontinued\n" + "");
		} else {
			return new String("destination not found\n" + "");
		}
	}
	
	/**
	 * List all the employees have the same destination in their wish list
	 * 
	 * If destination is not in the database, return "destination not found." 
	 * Otherwise, search for destination and return the destination along with 
	 * the employees who have that destination in their wish list (on one line) 
	 * in the format:
	 * 
	 * destination:employee1,employee2,employee3
	 * 
	 * @param destination 
	 *         
	 * @return String to display the information as described above
	 */
	protected static String pushSearch(String destination) {
		// test whether destination is null
		if (destination == null) {
			throw new IllegalArgumentException();
		}
		
		// We can use the getEmployee method of EmployeeDatabse to get the list
		ArrayList<String> emp_sameDest_list = ( ArrayList<String> ) employeeDB.getEmployees( destination.toLowerCase() );
		
		if ( emp_sameDest_list == null )
		{
			return new String("destination not found\n" + "");
		}
		else
		{
			String strResult = destination.toLowerCase() + ":";
			Iterator<String> emp_sameDest_listIter = emp_sameDest_list.iterator();
			while (emp_sameDest_listIter.hasNext()) 
			{
				strResult += emp_sameDest_listIter.next();
				if (emp_sameDest_listIter.hasNext()) 
				{
					strResult += ",";
				}
			}
			strResult += "\n" + "";
			return strResult;
		}
	}
	
	
	/**
	 * 
	 * Remove employee from database
	 * 
	 * If employee is not in the database, return "employee not found". 
	 * Otherwise, remove employee and return "employee removed".
	 * 
	 * @param employee a string that is a username of the employee
	 *         
	 * @return a string displaying whether the employee have been removed successfully
	 * 
	 */
	protected static String pushRemove(String employee) {
		// test whether employee is null
		if ( employee == null ) 
		{
			throw new IllegalArgumentException();
		}
		
		// Remove employee using removeEmployee method of EmployeeDatabse
		// true  : employee removed
		// false : employee not found 
		 if (employeeDB.removeEmployee( employee.toLowerCase() ) ) 
		 {
			 return new String("employee removed\n" + "");
		 }
		 else
		 {
			 return new String("employee not found\n" + "");
		 }
	}

	/**
	 * Return the summary information about this database.
	 * 
	 * The displayed information contains four parts:
	 * (1) Return a line: "Employees: integer, Destinations: integer"
	 *     This is the number of employees followed by the total number of unique destinations.
	 * (2) Return a line: "# of destinations/employee: most integer, least integer, average decimal fraction"
	 *     most is the largest number of destinations that any employee has in their wish list, 
	 *     least is the fewest, 
	 *     and average is the arithmetic mean number of destinations per employee 
	 *     rounded to the nearest tenth (e.g., 1.2 or 0.7).
	 * (3) Return a line: "# of employees/destination: most integer, least integer, average decimal fraction"
	 *     most is the largest number of employee wish lists in which any destination appears, 
	 *     least is the fewest, 
	 *     and average is the arithmetic mean number of employees per destination
	 *      rounded to the nearest tenth (e.g., 1.2 or 0.7).
	 * (4) Return a line: "Most popular destination: destination(s) [integer]"
	 *     This is the destination that shows up in the greatest number of wish lists followed 
	 *     by the number of wish lists containing that destination in square brackets. 
	 *     If there is a tie for most popular destination, display all those tying in 
	 *     the order they appear in the database separated by commas.
	 * 
	 * @param nonn
	 *         
	 * @return multiple information as described above
	 */
	protected static String pushInformation() {
		// data fields needed
		
        // the returning string that contains all the needed information
		String strResult = "Employees: " + employeeDB.size() + ", ";
		// the unique destination list
		ArrayList<String> total_unique_Destination_List = new ArrayList<String>();
		// number array to store the frequency of each employee's destination number
		ArrayList<Integer> numDestination_of_Employee = new ArrayList<Integer>();
		// number array to store the frequency of each destination's employee number
		ArrayList<Integer> numEmployee_of_destination = new ArrayList<Integer>();
		// the decimal format 
		DecimalFormat myFormatter = new DecimalFormat(".#");
		
		// create the iterator of the employee list
		Iterator<Employee> employIter = employeeDB.iterator();
		while ( employIter.hasNext() ) {
			Employee empTmp = employIter.next();
            // create iterator of the wish list
			ArrayList<String> destination_list = (ArrayList<String>) employeeDB.getDestinations( empTmp.getUsername());
			Iterator<String> listIter = destination_list.iterator();
			
			// get the size of this employee's wish list
			numDestination_of_Employee.add( destination_list.size());

			while ( listIter.hasNext() )
			{
				// test whether this destination already exists
				String currDestination = listIter.next();
				if ( ! (total_unique_Destination_List.contains( currDestination )))
				{
					total_unique_Destination_List.add(currDestination);
				}
			}
		}
		// Loop all the designation list of all employee
		strResult += "Destinations: " + total_unique_Destination_List.size() + "\n";
		
		// iterator through the unique destination list to get the frequency of 
		// each destination's employee number
		Iterator<String> uniqueDesitnationlistIter = total_unique_Destination_List.iterator();
		
		while ( uniqueDesitnationlistIter.hasNext() ) {
			String strDestinationName = uniqueDesitnationlistIter.next();
			// create employee list of this desination
			ArrayList<String> employee_currDestination_list = (ArrayList<String>) employeeDB.getEmployees( strDestinationName );
			numEmployee_of_destination.add( employee_currDestination_list.size());
		}
		
		// Using private helper methods to prepare the output information
		
		//part (2), destinations/employee
		strResult += "# of destinations/employee: most " + numMax(numDestination_of_Employee)
		             + ", least " + numMin(numDestination_of_Employee) + 
		             ", average " + myFormatter.format(numAvg(numDestination_of_Employee)) + "\n";
		// part (3), employees/destination
		strResult += "# of employees/destination: most " + numMax(numEmployee_of_destination)
                     + ", least " + numMin(numEmployee_of_destination) + 
                     ", average " + myFormatter.format(numAvg(numEmployee_of_destination)) + "\n";
		
		// part (4), "Most popular destination: destination(s) [integer]"
		// 1. find the most popular destination number
		// 2. loop the numEmployee_of_destination list to find that number and index
		// 3. using the index to get the name of the destination from the database
		// 4. add into the result string 
		
		strResult += "Most popular destination: ";
		// get the max number of the most popular destination
		int numMostEmoloyeeDestination = numMax(numEmployee_of_destination);
		// index number of the most popular destination
		int indexDestination = 0;
		// ArrayList to store the final results of destination
		ArrayList<String> popularDestinationList = new ArrayList<String>();
		// create the iterator
		Iterator<Integer> numDestlistIter = numEmployee_of_destination.iterator();
		while ( numDestlistIter.hasNext() ) 
		{
			// compare the number of destinations in wish list
			if ( numDestlistIter.next() == numMostEmoloyeeDestination ) 
			{
				popularDestinationList.add(total_unique_Destination_List.get(indexDestination));
			}
			indexDestination++;
		}
		
		// create iterator to loop the final popularDestinationList
		Iterator<String> popularDestlistIter = popularDestinationList.iterator();
		
		// only 1 most popular destination
		if ( popularDestinationList.size() == 1) 
		{
			strResult += popularDestinationList.get(0) + " [" + numMostEmoloyeeDestination + "]\n";
		}
		else 
		{
			while ( popularDestlistIter.hasNext() ) 
			{
				strResult += popularDestlistIter.next() ;
				if  ( popularDestlistIter.hasNext() ) 
				{
					strResult += ",";
				}
			}
			strResult += " [" + numMostEmoloyeeDestination + "]\n";
		}
		return strResult;
	}
	

	/**
	 * Return a list of the contents of the entire employee database, 
	 * one employee per line in the format: 
	 * employee:destination1,destination2,destination3
	 * 
	 * @param none
	 *         
	 * @return a string display information as described above
	 */
	protected static String pushList() {
		// the returning string that contains all the needed information
		String strResult = "";
		// create the iterator of the employee list
		Iterator<Employee> employIter = employeeDB.iterator();
		while ( employIter.hasNext() ) {
			Employee empTmp = employIter.next();
			strResult += empTmp.getUsername() + ":";
			// create iterator of the wish list
			ArrayList<String> destination_list = (ArrayList<String>) employeeDB.getDestinations( empTmp.getUsername());
			Iterator<String> listIter = destination_list.iterator();
			// loop all the destination in the wish list
			while ( listIter.hasNext() )
			{
				// add destination into the resulting strings
				strResult += listIter.next();
				// the last destination needs no comma after it
    			if (listIter.hasNext()) {
    				strResult += ",";
    			}
			}
			// adding  new-line character
			strResult += "\n";
		}
		return strResult;
	}

	// The pushHelp method may be used as is:

	protected static String pushHelp() {
		String cmds = "";
		if (GUIactive) {
			cmds += "Available commands:\n" + "\tFind employee\n" + "\tDiscontinue destination\n"
					+ "\tSearch destination\n" + "\tRemove employee\n" + "\tInformation on database\n"
					+ "\tList contents of database\n" + "\tText interface activated\n"
					+ "\tHelp on available commands\n" + "\tQuit database testing\n";
		} else {
			cmds += ("discontinue/d <destination> - discontinue the given <destination>\n")
					+ ("find/f <Employee> - find the given <Employee>\n") + ("gui/g Switch to GUI testing interface\n")
					+ ("help/h - display this help menu\n")
					+ ("information/i - display information about this Employee database\n")
					+ ("list/l - list contents of Employee database\n")
					+ ("search/s <destination> - search for the given <destination>\n") + ("quit/q - quit\n")
					+ ("remove/r <Employee> - remove the given <Employee>\n");

		}
		return cmds;
	}

	// The pushQuit method may be used as is:
	
	protected static String pushQuit() {
		System.exit(0);
		return "";
	}
	
	// private helper methods
	
	/**
	 * Return max value of a ArrayList 
	 * 
	 * @param ArraryList<Integer> the data needed to find the max value
	 *         
	 * @return max value of a ArrayList 
	 */
	private static int numMax(ArrayList<Integer> alist) 
	{
		// create the iterator
		Iterator<Integer> listIter = alist.iterator();
		int currMax;
		int currValue;
		if (listIter.hasNext()) 
		{
			// get the first value of the ArrayList
			currMax = listIter.next( );
			
			while ( listIter.hasNext() ) 
			{
				currValue = listIter.next( );
				// test whether currValue is larger than currMax
				if ( currValue > currMax ) 
				{
					currMax = currValue;
				}
			} 
		
			return currMax;
		}
		else 
		{
			// empty ArrayList, Illegal Argument
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Return min value of a ArrayList 
	 * 
	 * @param ArraryList<Integer> the data needed to find the min value
	 *         
	 * @return min value of a ArrayList 
	 */
	private static int numMin(ArrayList<Integer> alist) 
	{
		// create the iterator
		Iterator<Integer> listIter = alist.iterator();
		// Because each item must occur at least once to be included in the database
		int currMin = Integer.MAX_VALUE;
		int currValue;
		if (listIter.hasNext()) 
		{
			// get the first value of the ArrayList
			currMin = listIter.next( );
			
			while ( listIter.hasNext() ) 
			{
				currValue = listIter.next( );
				// test whether currValue is smaller than currMin
				if ( currValue < currMin ) 
				{
					currMin = currValue;
				}
			} 
		
			return currMin;
		}
		else 
		{
			// empty ArrayList, Illegal Argument
			throw new IllegalArgumentException();
		}

	}
	
	/**
	 * Return average value of a ArrayList 
	 * 
	 * @param ArraryList<Integer> the data needed to find the min value
	 *         
	 * @return average value of a ArrayList 
	 */
	private static double numAvg (ArrayList<Integer> alist) 
	{
		// create the iterator
		Iterator<Integer> listIter = alist.iterator();
		// total sum of the frequency
		int totalSum = 0;
		// length of the ArraryList
		int len = alist.size();
		// final average
		double num_avg_arrayList = 0.0;
		if (listIter.hasNext()) 
		{
			while ( listIter.hasNext() ) 
			{
				totalSum += listIter.next( );
			}
			
			num_avg_arrayList = ( 1.0 * totalSum ) / ( 1.0 * len );
			return num_avg_arrayList;
		
		}
		else 
		{
			// empty ArrayList, Illegal Argument
			throw new IllegalArgumentException();
		}

	}
}
