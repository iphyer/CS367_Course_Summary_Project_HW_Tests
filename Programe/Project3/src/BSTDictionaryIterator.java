
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: ( WordCloudGenerator.java )
// Files: ( BSTDictionaryIterator.java)
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
import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary. The iterator iterates over the tree in order
 * of the key values (from smallest to largest).
 * 
 */
public class BSTDictionaryIterator< K > implements Iterator<K>
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	// stacks to store nodes of BSTDictionary
	private Stack<BSTnode<K>> iterStack = new Stack<BSTnode<K>>();

	///////////////////////////
	// Constructors
	///////////////////////////
	/**
	 * Constructor for the iterator of BSTDictionary.
	 * 
	 * a Stack and push/pop nodes as you iterate through the BST. The
	 * constructor only push all the nodes needed so the *first* call to next()
	 * returns the value in the node with the smallest key.
	 * 
	 * @param root,
	 *            the root of BSTDictionary
	 * @return None
	 * 
	 */
	public BSTDictionaryIterator( BSTnode<K> root )
	{
		// find the smallest( left most ) nodes in the dictionary
		while ( root != null )
		{
			this.iterStack.push( root );
			root = root.getLeft();
		}
	}

	///////////////////////////
	// Methods
	///////////////////////////

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @param None
	 * @return true if the BSTDictionaryIteratorStack is not empty. false, if
	 *         the BSTDictionaryIteratorStack is not empty.
	 * 
	 */
	@Override
	public boolean hasNext()
	{
		return ( !this.iterStack.isEmpty() );
	}

	/**
	 * get next element and return the key value
	 * 
	 * @param None
	 * @throws NoSuchElementException
	 *             if there is no more elements
	 * @return return the key value
	 * 
	 */
	@Override
	public K next()
	{
		// has more elements to output or not
		if ( !this.hasNext() )
		{
			throw new NoSuchElementException();
		}

		// get current node and its key value
		BSTnode<K> currNode = this.iterStack.pop();
		K resultKeyVal = currNode.getKey();

		// Then we need to push the in-order successor of this node into the
		// stack
		// which is also finding the one just larger than currNode's key
		if ( currNode.getRight() != null )
		{
			BSTnode<K> tmpNode = currNode.getRight();
			// find the left most nodes of currNode
			while ( tmpNode != null )
			{
				this.iterStack.push( tmpNode );
				tmpNode = tmpNode.getLeft();
			}
		}

		return resultKeyVal;
	}

	@Override
	public void remove()
	{
		// DO NOT CHANGE: you do not need to implement this method
		throw new UnsupportedOperationException();
	}
}
