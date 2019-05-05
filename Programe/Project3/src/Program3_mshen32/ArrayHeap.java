
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: ( WordCloudGenerator.java )
// Files: ( ArrayHeap.java)
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
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 
 * ArrayHeap class is an array-based implementation of a max heap that supports
 * PriorityQueueADT interface
 * 
 * items stored must implement the Prioritizable interface which provides a
 * getPriority method that returns an integer value representing the priority of
 * an item (where larger values correspond to higher priorities).
 * 
 * Bugs: None
 *
 * @author Mingren Shen
 */

public class ArrayHeap< E extends Prioritizable > implements PriorityQueueADT<E>
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	// default number of items the heap can hold before expanding
	private static final int	INIT_SIZE	= 100;

	// the array-based implementation of a max heap
	private E[]					maxHeap;

	// number of items in heap
	private int					numItems;

	///////////////////////////
	// Constructors
	///////////////////////////

	/**
	 * no-argument constructor of ArrayHeap
	 * 
	 * constructs a heap whose underlying array has enough space to store
	 * INIT_SIZE items before needing to expand.
	 * 
	 * @param None
	 * @return None
	 * 
	 */
	public ArrayHeap()
	{
		this.maxHeap = ( E[] ) ( new Prioritizable[INIT_SIZE] );
		this.numItems = 0;
	}

	/**
	 * 1-argument constructor of ArrayHeap
	 * 
	 * takes an integer parameter and constructs a heap whose underlying array
	 * has enough space to store the number of items given in the parameter
	 * before needing to expand.
	 * 
	 * If the parameter value is less 0, an IllegalArgumentException is thrown.
	 * 
	 * @param newSize,
	 *            the new size of the heap
	 * @throws IllegalArgumentException,
	 *             if the parameter value is less
	 * @return None
	 * 
	 */
	public ArrayHeap( int newSize )
	{
		// test whether newSize is less than 0
		if ( newSize < 0 )
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.maxHeap = ( E[] ) ( new Prioritizable[newSize] );
			this.numItems = 0;
		}
	}

	///////////////////////////
	// Methods
	///////////////////////////

	// Codes to implement the PriorityQueue ADT operations using a
	// heap whose underlying data structure is an array.

	/**
	 * Returns true if this priority queue contains no items.
	 *
	 * 
	 * @param None
	 * @return true if this priority queue contains no items, false otherwise
	 * 
	 */
	@Override
	public boolean isEmpty()
	{
		// test whether size is 0
		return ( this.size() == 0 );
	}

	/**
	 * Adds the given item to the priority queue.
	 *
	 * @param item
	 *            - the item to insert into the priority queue
	 * @return None
	 * 
	 */
	@Override
	public void insert( E item )
	{
		// test whether item is null
		if ( item == null )
		{
			throw new IllegalArgumentException();
		}

		// item is not null, so first increase the numItems
		this.numItems++;

		// test whether the array is full
		// if full, call expandArray() to double the size of the array
		if ( this.numItems == this.maxHeap.length )
		{
			this.maxHeap = expandArray( this.maxHeap );
		}

		// add item to the end of the array, numItems has been increased
		this.maxHeap[this.numItems] = item;
		// Comparing the new value to the value in its parent.
		// If the parent is smaller, we swap the values, and
		// we continue this check-and-swap procedure up the tree until
		// we find that the order property holds, or we get to the root.
		int tmpIndex = this.numItems;
		// goes up the tree until get to the root, which is at index 1
		while ( tmpIndex > 1 )
		{
			// if item is larger than its parent
			if ( item.getPriority() > this.maxHeap[tmpIndex / 2].getPriority())
			{
				// swap item and its parent
				this.maxHeap[tmpIndex] = this.maxHeap[tmpIndex / 2];
				this.maxHeap[tmpIndex / 2] = item;
			}
			// goes up the next layer of parents
			tmpIndex = tmpIndex / 2;
		}
	}

	/**
	 * Helper function to expand the array by doubling its size
	 *
	 * @param oldArray
	 *            - old array that needs to be expanded
	 * @return newArray - new array that has double size of oldArray
	 * 
	 */
	private E[] expandArray( E[] oldArray )
	{
		// create new array
		E[] newArray = ( E[] ) ( new Prioritizable[2 * oldArray.length] );
		// copy items to new array
		for ( int i = 1; i < oldArray.length; i++ )
		{
			newArray[i] = oldArray[i];
		}
		return newArray;
	}

	/**
	 * Removes and returns the item with the highest priority.
	 *
	 * @param None
	 * @throws NoSuchElementException
	 *             - if the priority queue is empty
	 * @return the item with the highest priority
	 * 
	 */
	@Override
	public E removeMax()
	{
		// if the priority queue is empty
		if ( this.isEmpty() )
		{
			throw new NoSuchElementException();
		}
		// get the highest priority which is at index 1
		E maxResult = this.maxHeap[1];
		// Replace the value in the root with the value at the end of the array
		// (which corresponds to the heap's rightmost leaf at depth d).
		this.maxHeap[1] = this.maxHeap[this.numItems];
		// Remove that leaf from the tree.
		this.numItems = this.numItems - 1; // now original end can be inserted

		// work your way down the tree, swapping values to restore the order
		// property:
		// each time, if the value in the current node is less than
		// one of its children, then swap its value with the larger child
		int currIndex = 1; // starting from index 1
		// no reach the end of the heap array
		while ( 2 * currIndex < this.numItems )
		{
			// if parent[currIndex] is smaller than its children [currIndx * 2]
			// and [currIndex * 2 + 1]
			if ( this.maxHeap[currIndex]
					.getPriority() < this.maxHeap[currIndex * 2].getPriority()
					|| this.maxHeap[currIndex]
							.getPriority() < this.maxHeap[currIndex * 2 + 1]
									.getPriority() )
			{
				E largerChild = null;
				// pick the larger child node
				if ( this.maxHeap[currIndex * 2]
						.getPriority() > this.maxHeap[currIndex * 2 + 1]
								.getPriority() )
				{
					largerChild = this.maxHeap[currIndex * 2];
					this.maxHeap[currIndex * 2] = this.maxHeap[currIndex];
					this.maxHeap[currIndex] = largerChild;
					// now currIndex changes to new position of last leaf
					currIndex = 2 * currIndex;
				}
				else
				{
					largerChild = this.maxHeap[currIndex * 2 + 1];
					this.maxHeap[currIndex * 2 + 1] = this.maxHeap[currIndex];
					this.maxHeap[currIndex] = largerChild;
					// now currIndex changes to new position of last leaf
					currIndex = 2 * currIndex + 1;
				}
			}
			else // the swapping of node is good
			{
				break;
			}
		}

		return maxResult;
	}

	/**
	 * Returns the item with the highest priority.
	 * 
	 * @param None
	 * @throws NoSuchElementException
	 *             - if the priority queue is empty
	 * @return the item with the highest priority
	 * 
	 */
	@Override
	public E getMax()
	{
		// if the priority queue is empty
		if ( this.isEmpty() )
		{
			throw new NoSuchElementException();
		}
		// return the elements at index 1
		return this.maxHeap[1];
	}

	/**
	 * Returns the number of items in this priority queue.
	 *
	 * 
	 * @param None
	 * @return the number of items in this priority queue
	 * 
	 */
	@Override
	public int size()
	{
		return ( this.numItems );
	}

}
