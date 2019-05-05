
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: ( TestHash.java )
// Files: ( HashTable.java)
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

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class implements a hashtable that using chaining for collision handling.
 * Any non-<tt>null</tt> item may be added to a hashtable. Chains are
 * implemented using <tt>LinkedList</tt>s. When a hashtable is created, its
 * initial size, maximum load factor, and (optionally) maximum chain length are
 * specified. The hashtable can hold arbitrarily many items and resizes itself
 * whenever it reaches its maximum load factor or whenever it reaches its
 * maximum chain length (if a maximum chain length has been specified).
 * 
 * Note that the hashtable allows duplicate entries.
 */
public class HashTable< T >
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	// length of the array before expanding
	private int initSize;

	// the array-based implementation of the HashTable
	private LinkedList<T>[] HashArray;

	// the loader factor of the HashTable
	private double max_Load_Factor;
	// current load factor of the hashTable
	private double curr_Load_Factor = 0;

	// the max chain length of the HashTable
	// set the default to be the largest possible integer if not assigned in
	// constructor which means no need to worry about Chain Length
	private int max_Chain_Length = Integer.MAX_VALUE;
	// current chain length of the hashTable
	private int curr_Chain_Length = 0;

	// the number of items in the HashTable
	private int numItems;

	// the max number allowed resizing for too long Chains
	// choosing 5 as discussed in Piazza
	// https://piazza.com/class/jci5yx2vtew7gw?cid=194
	private final int MAX_RESIZING_TOO_LONG_CHAINS = 5;

	///////////////////////////
	// Constructors
	///////////////////////////

	/**
	 * Constructs an empty hashtable with the given initial size, maximum load
	 * factor, and no maximum chain length. The load factor should be a real
	 * number greater than 0.0 (not a percentage). For example, to create a hash
	 * table with an initial size of 10 and a load factor of 0.85, one would
	 * use:
	 * 
	 * <dir><tt>HashTable ht = new HashTable(10, 0.85);</tt></dir>
	 *
	 * @param initSize
	 *            the initial size of the hashtable.
	 * @param loadFactor
	 *            the load factor expressed as a real number.
	 * @throws IllegalArgumentException
	 *             if <tt>initSize</tt> is less than or equal to 0 or if
	 *             <tt>loadFactor</tt> is less than or equal to 0.0
	 **/
	public HashTable( int initSize, double loadFactor )
	{
		// check the range of initSize
		if ( initSize <= 0 || loadFactor <= 0.0 )
		{
			throw new IllegalArgumentException();
		}
		// assign the size of the array
		this.initSize = initSize;
		// create the an array of LinkedLists and initialize each index to be a
		// new LinkedList
		this.HashArray = ( LinkedList<T>[] ) ( new LinkedList[this.initSize] );
		// initialize new array
		for ( int i = 0; i < this.initSize; i++ )
		{
			this.HashArray[i] = new LinkedList<T>();
		}
		// assign a loadFactor
		this.max_Load_Factor = loadFactor;
		// assign the number of items in the HashTable
		this.numItems = 0;
	}

	/**
	 * Constructs an empty hashtable with the given initial size, maximum load
	 * factor, and maximum chain length. The load factor should be a real number
	 * greater than 0.0 (and not a percentage). For example, to create a hash
	 * table with an initial size of 10, a load factor of 0.85, and a maximum
	 * chain length of 20, one would use:
	 * 
	 * <dir><tt>HashTable ht = new HashTable(10, 0.85, 20);</tt></dir>
	 *
	 * @param initSize
	 *            the initial size of the hashtable.
	 * @param loadFactor
	 *            the load factor expressed as a real number.
	 * @param maxChainLength
	 *            the maximum chain length.
	 * @throws IllegalArgumentException
	 *             if <tt>initSize</tt> is less than or equal to 0 or if
	 *             <tt>loadFactor</tt> is less than or equal to 0.0 or if
	 *             <tt>maxChainLength</tt> is less than or equal to 0.
	 **/
	public HashTable( int initSize, double loadFactor, int maxChainLength )
	{
		// check the range of initSize
		if ( initSize <= 0 || loadFactor <= 0.0 || maxChainLength <= 0 )
		{
			throw new IllegalArgumentException();
		}
		// assign the size of the array
		this.initSize = initSize;
		// create the an array of LinkedLists and initialize each index to be a
		// new LinkedList
		this.HashArray = ( LinkedList<T>[] ) ( new LinkedList[this.initSize] );
		// initialize new array
		for ( int i = 0; i < this.initSize; i++ )
		{
			this.HashArray[i] = new LinkedList<T>();
		}
		// assign a loadFactor
		this.max_Load_Factor = loadFactor;
		// assign maxChainLength
		this.max_Chain_Length = maxChainLength;
		// assign the number of items in the HashTable
		this.numItems = 0;
	}

	///////////////////////////
	// Methods
	///////////////////////////

	/**
	 * Determines if the given item is in the hashtable and returns it if
	 * present. If more than one copy of the item is in the hashtable, the first
	 * copy encountered is returned.
	 *
	 * @param item
	 *            the item to search for in the hashtable.
	 * @return the item if it is found and <tt>null</tt> if not found.
	 **/
	public T lookup( T item )
	{
		// check whether item is null or not
		if ( item == null )
		{
			return null;
		}
		// check whether this is an empty HashTable
		if ( this.numItems == 0 )
		{
			return null;
		}
		// get the index of this item
		int tmpIndex = returnHash( item );
		// get the value in that index
		LinkedList<T> valueLinkedList = this.HashArray[tmpIndex];
		// if the valueLinkedList is null, the item is not in HashTable, return
		// null
		if ( valueLinkedList == null )
		{
			return null;
		}
		else // the valueLinkedList is not null
		{
			// check whether the valueLinkedList contains the item
			if ( valueLinkedList.contains( item ) )
			{
				// contain item
				return item;
			}
			else // not contain item
			{
				return null;
			}
		}

	}

	/**
	 * Inserts the given item into the hashtable. The item cannot be
	 * <tt>null</tt>. If there is a collision, the item is added to the end of
	 * the chain.
	 * <p>
	 * If the load factor of the hashtable after the insert would exceed (not
	 * equal) the maximum load factor (given in the constructor), then the
	 * hashtable is resized.
	 * 
	 * If the maximum chain length of the hashtable after insert would exceed
	 * (not equal) the maximum chain length (given in the constructor), then the
	 * hashtable is resized.
	 * 
	 * When resizing, to make sure the size of the table is reasonable, the new
	 * size is always 2 x <i>old size</i> + 1. For example, size 101 would
	 * become 203. (This guarantees that it will be an odd size.)
	 * </p>
	 * <p>
	 * Note that duplicates <b>are</b> allowed.
	 * </p>
	 *
	 * @param item
	 *            the item to add to the hashtable.
	 * @throws NullPointerException
	 *             if <tt>item</tt> is <tt>null</tt>.
	 **/
	public void insert( T item )
	{
		// item is null
		if ( item == null )
		{
			throw new NullPointerException();
		}
		// insert the item into the Hash array
		int tmpIndex = returnHash( item );
		LinkedList<T> tmpLL = this.HashArray[tmpIndex];
		// Appends item to the end of this list.
		tmpLL.add( item );
		// update status of the HashTable after inserting the item
		this.updateStatus( tmpLL );

		// check whether the HashTable is valid now

		// load factor is too large
		if ( this.curr_Load_Factor > this.max_Load_Factor )
		{
			this.resizeHashTable();
		}
		// the Chain length is too long
		int numReSizingTooLongChain = 0;
		while ( this.curr_Chain_Length > this.max_Chain_Length
				&& numReSizingTooLongChain <= this.MAX_RESIZING_TOO_LONG_CHAINS )
		{
			this.resizeHashTable();
			numReSizingTooLongChain++;
		}
	}

	/**
	 * Removes and returns the given item from the hashtable. If the item is not
	 * in the hashtable, <tt>null</tt> is returned. If more than one copy of the
	 * item is in the hashtable, only the first copy encountered is removed and
	 * returned.
	 *
	 * @param item
	 *            the item to delete in the hashtable.
	 * @return the removed item if it was found and <tt>null</tt> if not found.
	 **/
	public T delete( T item )
	{
		// check whether item is null
		if ( item == null )
		{
			return null;
		}

		// check whether the HashTable is empty
		if ( this.numItems == 0 )
		{
			return null;
		}

		// get the index of this item
		int tmpIndex = returnHash( item );
		LinkedList<T> tmpLL = this.HashArray[tmpIndex];
		// check whether the array element is null
		if ( tmpLL == null )
		{
			return null;
		}
		else
		{
			// found the item
			if ( tmpLL.contains( item ) )
			{
				// Removes the first occurrence of item from this list
				tmpLL.remove( item );
				return item;
			}
			else // not found the item
			{
				return null;
			}
		}

	}

	/**
	 * Prints all the items in the hashtable to the <tt>PrintStream</tt>
	 * supplied. The items are printed in the order determined by the index of
	 * the hashtable where they are stored (starting at 0 and going to (table
	 * size - 1)). The values at each index are printed according to the order
	 * in the <tt>LinkedList</tt> starting from the beginning.
	 *
	 * @param out
	 *            the place to print all the output.
	 **/
	public void dump( PrintStream out )
	{
		out.println( "Hashtable contents:" );
		// loop over the HashTable array
		for ( int i = 0; i < this.HashArray.length; i++ )
		{
			LinkedList<T> currList = this.HashArray[i];

			// currList is not null and empty
			if ( ( currList != null ) && ( currList.size() > 0 ) )
			{
				// Beginning of this linked list
				out.print( i + ": [" );
				// create iterator
				Iterator<T> iterLL = currList.iterator();
				while ( iterLL.hasNext() )
				{
					out.print( iterLL.next() );
					// if this linked list contains more than 1 item
					if ( iterLL.hasNext() )
					{
						out.print( ", " );
					}
				}
				// Ending of this linked list
				out.println( "]" );
			}
		}
	}

	/**
	 * Prints statistics about the hashtable to the <tt>PrintStream</tt>
	 * supplied. The statistics displayed are:
	 * <ul>
	 * <li>the current table size
	 * <li>the number of items currently in the table
	 * <li>the current load factor
	 * <li>the length of the largest chain
	 * <li>the number of chains of length 0
	 * <li>the average length of the chains of length > 0
	 * </ul>
	 *
	 * @param out
	 *            the place to print all the output.
	 **/
	public void displayStats( PrintStream out )
	{
		// calculate average length and 0-length chain
		int numZEROChain = 0;
		int numNONZEROChain = 0;
		int totalLengthNONZEROChain = 0;
		// loop over the HashTable array
		for ( int i = 0; i < this.HashArray.length; i++ )
		{
			LinkedList<T> currList = this.HashArray[i];
			// currList is null
			if ( ( currList == null ) )
			{
				numZEROChain++;
			}
			else // currList is not null
			{
				// empty list
				if ( currList.size() == 0 )
				{
					numZEROChain++;
				}
				else // non-empty list
				{
					numNONZEROChain++;
					totalLengthNONZEROChain += currList.size();
				}

			}
		}

		// format out using left-justify all string outputs with a minimum width
		// of 28 spaces
		out.println( "Hashtable statistics:" );
		out.printf( "%-28s", "  current table size:" );
		out.println( this.HashArray.length );
		out.printf( "%-28s", "  # items in table:" );
		out.println( this.numItems );
		out.printf( "%-28s", "  current load factor:" );
		out.println( this.curr_Load_Factor );
		out.printf( "%-28s", "  longest chain length:" );
		out.println( this.curr_Chain_Length );
		out.printf( "%-28s", "  # 0-length chains:" );
		out.println( numZEROChain );
		out.printf( "%-28s", "  avg (non-0) chain length:" );
		out.println( totalLengthNONZEROChain * 1.0 / numNONZEROChain );
	}

	///////////////////////////
	// Helper Methods
	///////////////////////////

	/**
	 * Determines the hashCode of the item which is index to store this item in
	 * the array
	 * 
	 * Call the hashCode() method on the item and modulo this value by the table
	 * size. If hashCode() returns a negative value. For example, when you take
	 * a modulo of a negative value, the result is a negative value. An easy way
	 * to deal with this is to check the value of hashCode() modulo table size.
	 * If it is negative, simply add table size to the result. This will give
	 * you a positive value between 0 and table size-1.
	 *
	 * @param item
	 *            the item to be inserted or looked up
	 * @return the index the item will be in the HashTable array.
	 **/
	private int returnHash( T item )
	{
		// check whether the table size is valid
		if ( this.initSize <= 0 )
		{
			// something in the constructor of the HashTable
			throw new IllegalArgumentException();
		}
		// get the hashCode of the item
		// Call the hashCode() method on the item and modulo this value by the
		// table size.
		int tmpHash = ( item.hashCode() ) % ( this.initSize );
		// if negative hashCode, simply add table size to the result.
		if ( tmpHash < 0 )
		{
			tmpHash = tmpHash + this.initSize;
		}
		return tmpHash;
	}

	/**
	 * Update Status of the HashTable when an item is inserted
	 * 
	 * @param tmpLL
	 *            the linked list the item is inserted
	 * @return None
	 * 
	 **/
	private void updateStatus( LinkedList<T> LL )
	{
		// update current chain length if needed
		if ( LL.size() > this.curr_Chain_Length )
		{
			this.curr_Chain_Length = LL.size();
		}
		// update number of items
		this.numItems++;
		// update current load factor
		this.curr_Load_Factor = this.numItems * 1.0 / this.initSize;
	}

	/**
	 * Resize the HashTable
	 * 
	 * When resizing, to make sure the size of the table is reasonable, the new
	 * size is always 2 x <i>old size</i> + 1. For example, size 101 would
	 * become 203. (This guarantees that it will be an odd size.)
	 *
	 * @param None
	 * @return None
	 **/
	private void resizeHashTable()
	{
		// get new size of the HashTable
		int newSize = this.initSize * 2 + 1;

		// create the new array of HashTable
		LinkedList<T>[] newArray = ( LinkedList<T>[] ) new LinkedList[newSize];
		// initialize new array
		for ( int i = 0; i < newSize; i++ )
		{
			newArray[i] = new LinkedList<T>();
		}
		// assign the new size to the initSize of the array
		this.initSize = newSize;

		// reset current Chain length, numItems and load factor
		this.curr_Chain_Length = 0;
		this.curr_Load_Factor = 0.0;
		this.numItems = 0;

		// Loop through all the elements in the old array
		for ( int i = 0; i < this.HashArray.length; i++ )
		{
			// get the current item in the array
			LinkedList<T> tmpLL = this.HashArray[i];
			// if tmpLL is not null or empty
			// iterate all the elements in the LinkedList to the new array

			if ( tmpLL != null && tmpLL.size() != 0 )
			{

				Iterator<T> iter = tmpLL.iterator();
				while ( iter.hasNext() )
				{
					// get next item in the linked list
					T currItem = iter.next();
					// the items index
					int newtmpIndex = returnHash( currItem );
					// get the linked list in the index
					LinkedList<T> newTmpLL = newArray[newtmpIndex];
					// if the linked list is null( not used before)
					// Initialize a new linked list
					if ( newTmpLL == null )
					{
						newTmpLL = new LinkedList<T>();
					}
					// add the value
					newTmpLL.add( currItem );
					// update status of the HashTable after inserting the item
					this.updateStatus( newTmpLL );
				}
			}
		}

		// change the HashTable array to new array
		this.HashArray = newArray;

	}

}
