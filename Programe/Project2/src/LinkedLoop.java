
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: (ImageLoopEditor.java OR TextImageLoopEditor.java)
// Files: (LinkedLoop.java)
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

import java.util.Iterator;

/**
 * 
 * The EmptyLoopException is a checked exception and is thrown by some methods
 * (as described in the LoopADT<E> interface documentation).
 *
 * @author Mingren Shen Copyright (2018)
 * @version 1.0
 * @see also LoopADT, LinkedLoop
 */

public class LinkedLoop< E > implements LoopADT<E>
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	/** current node of LinkedLoop */
	private DblListnode<E>	currentNode;

	/** number of items in LinkedLoop */
	private int				itemCount;

	///////////////////////////
	// Constructors
	///////////////////////////

	/**
	 * Constructor : takes no arguments and creates an empty loop.
	 * 
	 * @param None
	 * @return empty loop
	 * 
	 */

	public LinkedLoop()
	{
		// create first(current) node that is empty
		currentNode = new DblListnode<E>( null, null, null );
		itemCount = 0;
	}

	///////////////////////////
	// Methods
	///////////////////////////

	/**
	 * Adds the given item immediately before the current item.
	 * 
	 * Adds the given item immediately before the current item. After the new
	 * item has been added, the new item becomes the current item.
	 * 
	 * @param item
	 *            the item to be added
	 * @return None
	 * 
	 */
	@Override
	public void add( E item )
	{

		// null parameters
		if ( item == null )
		{
			throw new java.lang.IllegalArgumentException();
		}
		// create to-be-added node no links to neighboring nodes
		DblListnode<E> tmp = new DblListnode<E>( item );

		// Brief descriptions of how to add new item node
		// if the Loop is empty:
		// (1) move currentNode to tmp node
		// (2) set prev and next points to itself
		// if the Loop is not empty:
		// (1) move tmp node's prev to currentNode's prev
		// (tmp.prev => currentNode.prev)
		// (2) move tmp node's next to currentNode
		// (tmp.next => current)
		// (3) move currentNode's previous node's next to tmp
		// (currentNode.prev.next => tmp)
		// (4) move currentNode's prev to tmp node
		// (currentNode.prev => tmp)
		// (5) move currentNode to tmp node
		// (currentNode => tmp)
		// Here => denotes pointing to
		// increase the count of items in the Loop

		// empty Loop
		if ( this.itemCount == 0 )
		{
			// (1) currentNode => tmp
			this.currentNode = tmp;
			// (2) prev and next points to itself
			this.currentNode.setNext( this.currentNode );
			this.currentNode.setPrev( this.currentNode );
		}
		else // non-empty Loop
		{
			// (1) tmp.prev => currentNode.prev
			tmp.setPrev( this.currentNode.getPrev() );
			// (2) tmp.next => current
			tmp.setNext( this.currentNode );
			// (3) currentNode.prev.next => tmp
			this.currentNode.getPrev().setNext( tmp );
			// (4) currentNode.prev => tmp
			this.currentNode.setPrev( tmp );
			// (5) currentNode => tmp
			this.currentNode = tmp;
		}
		// updating the number of items in the Loop
		this.itemCount++;
	}

	/**
	 * Returns the current item.
	 * 
	 * If the Loop is empty, an EmptyLoopException is thrown.
	 * 
	 * @param None
	 * @return item current item node
	 * @throws EmptyLoopException
	 *             if the Loop is empty
	 * 
	 */
	@Override
	public E getCurrent() throws EmptyLoopException
	{
		// test empty Loop or not
		if ( this.itemCount == 0 )
		{
			throw new EmptyLoopException();
		}
		else // not an empty Loop
		{
			return this.currentNode.getData();
		}
	}

	/**
	 * Removes and returns the current item.
	 * 
	 * The item immediately after the removed item then becomes the current
	 * item. If the Loop is empty initially, an EmptyLoopException is thrown.
	 * 
	 * @param None
	 * @return item current item node's data
	 * @throws EmptyLoopException
	 *             if the Loop is empty
	 * 
	 */
	@Override
	public E removeCurrent() throws EmptyLoopException
	{

		// test empty Loop or not
		if ( this.itemCount == 0 )
		{
			throw new EmptyLoopException();
		}
		else // not an empty Loop
		{
			// Brief descriptions of how to remove current node
			// get the data in current node
			// if the Loop contains only 1 node
			// (1) directly set currentNode = null, the Loop become empty after
			// remove()
			// (2) return the date of currentNode
			// if the Loop contains >= 2 nodes
			// (1) create temporary nodes tmp and set tmp = currentNode
			// (2) move currentNode to tmp.next node
			// (3) move tmp node's previous node's next to currentNode
			// (tmp.prev.next => currentNode)
			// (4) move currentNode's prev to tmp node's previous node
			// (currentNode.prev => tmp.prev)
			// Finally decreases item count and return the data

			// get the data in current node
			E currData = this.currentNode.getData();

			if ( this.itemCount == 1 )
			{
				// One item Loop
				// remove the only node
				this.currentNode = null;
			}
			else
			{
				// the number of items is >= 2
				DblListnode<E> tmp = this.currentNode;
				// move currentNode to next node
				this.currentNode = tmp.getNext();
				// move tmp node's previous node's next to currentNode
				tmp.getPrev().setNext( this.currentNode );
				// move currentNode's prev to tmp node's previous node
				this.currentNode.setPrev( tmp.getPrev() );
			}

			// reduce the number of items in the loop
			this.itemCount--;
			return currData;
		}
	}

	/**
	 * Advances current forward one item resulting in the item that is
	 * immediately <em>after</em> the current item becoming the current item.
	 * 
	 * 
	 * @param None
	 * @return None
	 * 
	 */
	@Override
	public void next()
	{
		// move currentNode to next Node
		this.currentNode = this.currentNode.getNext();
	}

	/**
	 * Moves current backwards one item resulting in the item that is
	 * immediately <em>before</em> the current item becoming the current item.
	 * 
	 * @param None
	 * @return None
	 * 
	 */
	@Override
	public void previous()
	{
		// move currentNode to previous Node
		this.currentNode = this.currentNode.getPrev();
	}

	/**
	 * Determines if this Loop is empty, i.e., contains no items.
	 * 
	 * @param None
	 * @return true if the Loop is empty; false if the Loop is not empty
	 */
	@Override
	public boolean isEmpty()
	{
		// test whether the number of nodes is 0 or not
		return ( this.itemCount == 0 );
	}

	/**
	 * Return the number of items in the Loop
	 * 
	 * @param None
	 * @return the number of items in the Loop
	 * 
	 */
	@Override
	public int size()
	{
		return this.itemCount;
	}

	/**
	 * Return the iterator of LinkedLoop.
	 * 
	 * @param None
	 * @return an iterator for this Loop
	 * 
	 */
	@Override
	public Iterator<E> iterator()
	{
		return new LinkedLoopIterator<E>( this.currentNode, this.itemCount );
	}

}
