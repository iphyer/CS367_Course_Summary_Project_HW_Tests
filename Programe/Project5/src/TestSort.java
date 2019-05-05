
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Title: ( TestSort)
// Files: ( TestSort.java)
// Semester: (CS 367) Spring 2018
//
// Author: (Mingren Shen)
// Email: (mshen32@wisc.edu)
// CS Login: (mingren)
// Lecturer's Name: (Charles Fischer)
// Lab Section: (None)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// None
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//
// None
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Random;

/**
 * This program tests some of the functionality of the ComparisonSort class. It
 * does not completely test the ComparisonSort class. You should make sure that
 * you do completely test your ComparisonSort class, either by modifying this
 * file or by writing a different driver.
 */
public class TestSort
{

	/**
	 * Main method to run the ComparisonSort class.
	 * 
	 * @param args
	 *            a two-value array: first the number of items in the input
	 *            array, then the random number seed (integer)to use in
	 *            generating values
	 */
	public static void main( String[] args )
	{

		if ( args.length != 2 )
		{
			System.err.println( "Expected 2 but got " + args.length );
			System.err.println( "Arguments expected:" );
			System.err.println( "  # items in input array" );
			System.err.println( "  random # seed" );
			System.exit( 1 );
		}
		int arrSize = Integer.parseInt( args[0] );
		int seed = Integer.parseInt( args[1] );

		System.out.println( "Parameters used:" );
		System.out.println( "  # items in input array: " + arrSize );
		System.out.println( "  random # seed: " + seed );

		// Create the input array of unsorted objects.
		SortObject[] arr = new SortObject[arrSize];

		// It is important to give the seed so you can reproduce results.
		// Random random = new Random(seed);
		for ( int k = 0; k < arrSize; k++ )
		{
			arr[k] = new SortObject(arrSize - k); //run11
	         //  arr[k] = new SortObject(k); // run 10
			// arr[k] = new SortObject(random.nextInt());//runs 1 to 9
		}
		// Run all the sorts on the array of random integers.
		ComparisonSort.runAllSorts( arr );
	}
}
