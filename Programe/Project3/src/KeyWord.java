
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: ( WordCloudGenerator.java )
// Files: ( KeyWord.java)
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
//////////////////////////// 80 columns wide ////////////////////////////////

/**
 * 
 * A KeyWord consists of a word and an integer (representing the number of
 * occurrences of the word). A word is a non-empty sequence of characters whose
 * letters are all lower-case.
 *
 * Bugs: None
 *
 * @author Mingren Shen
 */

public class KeyWord implements Comparable<KeyWord>, Prioritizable
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	private String	word;	// fields of the given words
	private int		occur;	// fields of the occurrence of given words

	///////////////////////////
	// Constructors
	///////////////////////////

	/**
	 * Constructs a KeyWord with the given word (converted to lower-case) and
	 * zero occurrences.
	 * 
	 * If the word is null or an empty string, an IllegalArgumentException is
	 * thrown.
	 * 
	 * @param w
	 *            the given word
	 * @return None
	 * @throws IllegalArgumentException
	 *             if w is null or an empty string
	 */

	public KeyWord( String w )
	{
		// test w is null or an empty string
		if ( w == null || w.isEmpty() )
		{
			throw new IllegalArgumentException();
		}
		else
		{
			word = w.toLowerCase();
			occur = 0;
		}
	}

	///////////////////////////
	// Methods
	///////////////////////////

	/**
	 * Returns the word for this KeyWord.
	 * 
	 * @param None
	 * @return the word for this KeyWord
	 * 
	 */

	public String getWord()
	{
		return this.word;
	}

	/**
	 * Returns the number of occurrences for this KeyWord.
	 * 
	 * @param None
	 * @return the number of occurrences for this KeyWord
	 * 
	 */
	public int getOccurrences()
	{
		return this.occur;
	}

	/**
	 * Adds one to the number of occurrences for this KeyWord.
	 * 
	 * @param None
	 * @return None
	 * 
	 */
	public void increment()
	{
		this.occur++;
	}

	/**
	 * Returns the priority for this KeyWord.
	 * 
	 * The priority of a KeyWord is the number of occurrences it has.
	 * 
	 * @param None
	 * @return the priority for this item.
	 * 
	 */
	@Override
	public int getPriority()
	{
		return this.getOccurrences();
	}

	/**
	 * Compares the KeyWord with the one given.
	 * 
	 * Two KeyWords are compared by comparing the word associated with the two
	 * KeyWords, ignoring case differences in the names.
	 * 
	 * 
	 * @param other
	 *            the KeyWord with which to compare this KeyWord
	 * @return negative integer number, this KeyWord object is less than other
	 *         zero, this KeyWord object equal to other a positive integer
	 *         number, this KeyWord object greater than other
	 * 
	 * 
	 */
	@Override
	public int compareTo( KeyWord other )
	{
		return this.word.toLowerCase().compareTo( other.word.toLowerCase() );
	}

	/**
	 * Compares this KeyWord to the specified object.
	 * 
	 * The result is true if and only if the argument is not null and is a
	 * KeyWord object whose word is the same as the word of this KeyWord,
	 * ignoring case differences.
	 * 
	 * @param other
	 *            the object with which to compare this KeyWord
	 * @return true other equals to this KeyWord false other does not equal to
	 *         this KeyWord
	 * 
	 */
	@Override
	public boolean equals( Object other )
	{
		// if other and the this point to the same reference, true
		// an object equals to itself
		if ( this == other )
		{
			return true;
		}

		// test whether other is null
		if ( other == null )
		{
			return false;
		}

		// test whether other is KeyWord class
		// using getClass() method from Object
		if ( this.getClass() != other.getClass() )
		{
			return false;
		}

		// Now other is not null and other is the KeyWord class
		// convert other to KeyWord type
		KeyWord other_converted = ( KeyWord ) other;

		// test whether word is the same
		if ( !( this.word.equalsIgnoreCase( other_converted.word ) ) )
		{
			return false;
		}

		// pass all equal to conditions, return true
		return true;
	}

}
