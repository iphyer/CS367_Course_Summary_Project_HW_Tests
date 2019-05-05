
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: (ImageLoopEditor.java OR TextImageLoopEditor.java)
// Files: (LinkedLoopIterator.java)
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * The LinkedLoopIterator class iterates the linked Loop
 *
 * @author Mingren Shen Copyright (2018)
 * @version 1.0
 * @see also LinkedLoop, ImageLoopEditor
 */

public class LinkedLoopIterator< E > implements Iterator<E>
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	// position of current Node
	private DblListnode<E>	currNode;

	// number of item nodes in the Linked Loop
	private int				itemNum;

	// current position in the Linked Loop
	private int				currentPos;

	///////////////////////////
	// Constructors
	///////////////////////////
	/**
	 * Construct the object of LinkedLoopIterator
	 * 
	 * @parameter DblListnode<E> curr of the LinkedLoop int totNum the number of
	 *            item nodes in the LinkedLoop
	 */
	public LinkedLoopIterator( DblListnode<E> curr, int totNum )
	{
		this.currNode = curr;
		this.itemNum = totNum;
		this.currentPos = 0;

	}

	/**
	 * Whether LinkedLoopIterator has visited all nodes in the Linked Loop to
	 * avoid the infinite loop problems
	 * 
	 * @parameter none
	 * 
	 * @return true not all nodes have been visited once false all nods have
	 *         been visited
	 * 
	 */
	@Override
	public boolean hasNext()
	{
		return this.currentPos < this.itemNum;
	}

	/**
	 * Return the next DblListnode in the LinkedList Loop
	 * 
	 * @parameter none
	 * 
	 * @return the data in current Node
	 * 
	 */
	@Override
	public E next()
	{
		if ( !this.hasNext() )
		{
			throw new NoSuchElementException();
		}
		// get the data in current node
		E currDat = this.currNode.getData();
		// update current node and position
		this.currNode = this.currNode.getNext();
		this.currentPos++;
		return currDat;
	}

	/**
	 * Remove object method which is not implemented
	 * 
	 * @parameter none
	 * 
	 * @return UnsupportedOperationException
	 * 
	 */
	public void remove()
	{
		throw new UnsupportedOperationException();
	}

}
