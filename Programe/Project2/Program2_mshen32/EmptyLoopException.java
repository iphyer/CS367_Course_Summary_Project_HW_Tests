
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: (ImageLoopEditor.java OR TextImageLoopEditor.java)
// Files: (EmptyLoopException.java)
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

/**
 * 
 * The EmptyLoopException is a checked exception and is thrown by some methods
 * (as described in the LoopADT<E> interface documentation).
 *
 * @author Mingren Shen Copyright (2018)
 * @version 1.0
 * @see also LoopADT, LinkedLoop
 */

public class EmptyLoopException extends Exception
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	/**
	 * According to Java Doc,
	 * 
	 * serialVersionUID to help deserialization to verify that the sender and
	 * receiver of a serialized object have loaded classes for that object that
	 * are compatible with respect to serialization.
	 * 
	 * @see also
	 *      https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = -7843691892373743947L;

	///////////////////////////
	// Constructors
	///////////////////////////

	/**
	 * constructor from super class Exception without message
	 * 
	 * @param None
	 * 
	 */

	public EmptyLoopException()
	{
		super();
	}

	/**
	 * constructor with message
	 * 
	 * @param message
	 *            error information message to help diagnose
	 */

	public EmptyLoopException( String message )
	{
		super( message );
	}

}
