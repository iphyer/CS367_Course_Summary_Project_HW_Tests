
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: ( WordCloudGenerator.java )
// Files: ( BSTDictionary.java)
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
// Online sources:
// code given in the on-line reading on Binary Search Trees,
// http://pages.cs.wisc.edu/~hasti/cs367-common/readings/Binary-Search-Trees/
//
//////////////////////////// 80 columns wide ////////////////////////////////

import java.util.Iterator;

/**
 * 
 * A BSTDictionary Class implements the Dictionary ADT to store unique keys.
 * Operations are provided to insert, lookup, and delete information as well as
 * to go through the keys in order from smallest to largest (based on the
 * ordering given by compareTo).
 * 
 * Additionally, the BSTDictionary Class provides some methods that give insight
 * about the relative efficiency of a particular map: the size (# of keys) and
 * the total path length.
 * 
 * The total path length is the sum of the lengths of the paths to each key.
 * Thus, one measure of the average lookup time is (total path length)/size.
 * 
 * The Dictionary does not allow null keys to be added.
 *
 * Bugs: None
 *
 * @author Mingren Shen
 */

public class BSTDictionary< K extends Comparable<K> >
		implements DictionaryADT<K>
{
	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	private BSTnode<K>	root;		// the root node
	private int			numItems;	// the number of items in the dictionary

	///////////////////////////
	// Constructors
	///////////////////////////

	// no-argument constructor

	public BSTDictionary()
	{
		this.root = null;
		this.numItems = 0;
	}

	///////////////////////////
	// Methods
	///////////////////////////

	/**
	 * Inserts the given key into the Dictionary if the key is not already in
	 * the Dictionary.
	 * 
	 * If the key is already in the Dictionary, a DuplicatException is thrown.
	 * If the key is null, a IllegalArgumentException is thrown.
	 * 
	 * @param key
	 *            the key to be inserted
	 * @throws DuplicatException
	 *             if the key is already in the Dictionary
	 * @throws IllegalArgumentException
	 *             if the key is null
	 * @return None
	 * 
	 */
	@Override
	public void insert( K key ) throws DuplicateException
	{
		// if key is null
		if ( key == null )
		{
			throw new IllegalArgumentException();
		}

		// call the recursive helper function
		this.root = insert( this.root, key );
	}

	/**
	 * The recursive helper function of insert()
	 * 
	 * This function refers to the code given in online reading
	 * 
	 * @param key,
	 *            the key to insert into the Dictionary
	 * @param n,
	 *            the root node of certain the subtree
	 * @throws DuplicatException
	 *             if the key is already in the Dictionary
	 * @return the node to add the key
	 * 
	 */
	private BSTnode<K> insert( BSTnode<K> n, K key ) throws DuplicateException
	{
		// if n is a null BSTnode
		// return a new BSTnode as root for the tree
		if ( n == null )
		{
			// update the number of tree nodes
			this.numItems++;
			return new BSTnode<K>( key, null, null );
		}

		// if find the key already in the tree
		// throws
		if ( n.getKey().equals( key ) )
		{
			throw new DuplicateException();
		}

		// if Key is smaller than node n's key
		// go to left subtree
		if ( key.compareTo( n.getKey() ) < 0 )
		{
			// add key to the left subtree
			// and recursively call insert to use leftChild of n as new node to
			// search
			n.setLeft( insert( n.getLeft(), key ) );
			return n;
		}
		else
		{
			// if Key is larger than node n's key
			// add key to the right subtree
			// and recursively call insert to use rightChild of n as new node to
			// search
			n.setRight( insert( n.getRight(), key ) );
			return n;
		}

	}

	/**
	 * Deletes the given key from the Dictionary.
	 * 
	 * If the key is in the Dictionary, the key is deleted and true is returned.
	 * If the key is not in the Dictionary, the Dictionary is unchanged and
	 * false is returned.
	 * 
	 * @param key
	 *            the key to delete from the Dictionary
	 * @throws IllegalArgumentException
	 *             if the key is null
	 * @return true if the deletion is successful, (i.e., the key was in the
	 *         Dictionary and has been removed) false otherwise (i.e., the key
	 *         was not in the Dictionary)
	 * 
	 */
	@Override
	public boolean delete( K key )
	{
		// if key is null
		if ( key == null )
		{
			throw new IllegalArgumentException();
		}

		// test whether key exists in Dictionary or not
		if ( lookup( key ) == null )
		{
			// the key was not in the Dictionary
			// return false
			return false;
		}
		else
		{
			// key is in the dictionary so try to delete it
			// call the recursive helper function
			this.root = delete( this.root, key );
			// Decrease the number of nodes in the dictionary
			this.numItems--;
			// the key was in the Dictionary and has been removed
			return true;
		}

	}

	/**
	 * The recursive helper function of delete()
	 * 
	 * If the key is in the Dictionary, the key is deleted and true is returned.
	 * If the key is not in the Dictionary, the Dictionary is unchanged and
	 * false is returned.
	 * 
	 * @param key,the
	 *            key to delete from the Dictionary
	 * @param n,
	 *            the root node of certain the subtree
	 * @return true if the deletion is successful, (i.e., the key was in the
	 *         Dictionary and has been removed) false otherwise (i.e., the key
	 *         was not in the Dictionary)
	 * 
	 */
	private BSTnode<K> delete( BSTnode<K> n, K key )
	{
		// for null tree, no need to delete
		if ( n == null )
		{
			return null;
		}

		// node n has the key
		if ( key.equals( n.getKey() ) )
		{
			// n is the node to be removed

			// n is leaf node
			if ( isLeafNode( n ) )
			{
				return null;
			}

			// n has only single child
			// only child is right child
			if ( n.getLeft() == null )
			{
				return n.getRight();
			}
			// only child is left child
			if ( n.getRight() == null )
			{
				return n.getLeft();
			}

			// n has 2 children
			// get the smallest value of the right sub tree
			K smallVal = smallest( n.getRight() );
			// set the found node with the smallest value of right sub tree
			n.setKey( smallVal );
			// recursively the right sub tree to delete smallest value
			// this value is used in the found node
			n.setRight( delete( n.getRight(), smallVal ) );
			return n;
		}
		else if ( key.compareTo( n.getKey() ) < 0 )
		{
			// if Key is smaller than node n's key
			// go to leftChild of n
			n.setLeft( delete( n.getLeft(), key ) );
			return n;
		}
		else
		{
			// if Key is larger than node n's key
			// go to rightChild of n
			n.setRight( delete( n.getRight(), key ) );
			return n;
		}
	}

	/**
	 * The private helper function of testing whether a node is leaf node
	 * 
	 * @param n,the
	 *            BSTnode to test whether it is leaf node
	 * @return true if n is leaf node, false if n is not leaf node
	 * 
	 */
	private boolean isLeafNode( BSTnode<K> n )
	{
		// leaf node has no leftChild and rightChild
		if ( n.getLeft() == null && n.getRight() == null )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * The private helper function of returning the smallest value in the
	 * subtree rooted at n
	 * 
	 * @param n,the
	 *            BSTnode to start searching and must be not null
	 * @return true if n is leaf node, false if n is not leaf node
	 * 
	 */
	private K smallest( BSTnode<K> n )
	{
		// go to the left most node
		if ( n.getLeft() == null )
		{
			return n.getKey();
		}
		else
		{
			return smallest( n.getLeft() );
		}
	}

	/**
	 * Searches for the given key in the Dictionary and returns the key stored
	 * in the Dictionary.
	 * 
	 * If the key is not in the Dictionary, null is returned.
	 * 
	 * @param key,
	 *            the key to search for
	 * @throws IllegalArgumentException
	 *             if the key is null
	 * @return the key from the Dictionary, if the key is in the Dictionary;
	 *         null, if the key is not in the Dictionary
	 * 
	 */
	@Override
	public K lookup( K key )
	{
		// if key is null
		if ( key == null )
		{
			throw new IllegalArgumentException();
		}

		// key is not null
		return lookup( this.root, key );
	}

	/**
	 * The recursive helper function of lookup()
	 * 
	 * If the key is not in the Dictionary, null is returned.
	 * 
	 * @param key,
	 *            the key to search for
	 * @param n,
	 *            the root node of certain the subtree
	 * @return the key from the Dictionary, if the key is in the Dictionary;
	 *         null, if the key is not in the Dictionary
	 * 
	 */
	private K lookup( BSTnode<K> n, K key )
	{
		// if the tree is null, retrun null
		if ( n == null )
		{
			return null;
		}

		// found the node with the given key
		if ( n.getKey().equals( key ) )
		{
			return n.getKey();
		}

		// now n is the node we look for
		// key < this node's key; go to left subtree
		if ( key.compareTo( n.getKey() ) < 0 )
		{
			return lookup( n.getLeft(), key );
		}
		else
		{
			// key > this node's key; go to right subtree
			return lookup( n.getRight(), key );
		}
	}

	/**
	 * Test whether the dictionary is empty
	 *
	 * 
	 * @param None
	 * @return true if and only if the Dictionary is empty. false, if the
	 *         Dictionary is not empty.
	 * 
	 */
	@Override
	public boolean isEmpty()
	{
		return ( this.numItems == 0 );
	}

	/**
	 * Returns the number of keys in the Dictionary.
	 * 
	 * @param None
	 * @return the number of keys in the Dictionary.
	 * 
	 */
	@Override
	public int size()
	{
		return this.numItems;
	}

	/**
	 * Returns the total path length. The total path length is the sum of the
	 * lengths of the paths to each (key, value) pair.
	 * 
	 * @param None
	 * @return The total path length
	 * 
	 */
	@Override
	public int totalPathLength()
	{
		// for root the depth is 1
		// call the recursive helper function of totalPathLength()
		return totalPathLength( root, 1 );
	}

	/**
	 * Private helper function for totalPathLength The total path length is the
	 * sum of the lengths of the paths to each (key, value) pair.
	 * 
	 * the recursive definition for the total path length for a binary tree
	 * starting at a node N that is at a depth D: (1)The total path length is 0
	 * if N is null. (2)The total path length is D if N is a leaf. (3)Otherwise,
	 * the total path length is D plus the total path lengths of the right and
	 * left subtrees (each of which have their root at depth D+1)
	 *
	 * @param n,
	 *            the root node of certain the subtree
	 * @param depth,
	 *            depth of current node n
	 * 
	 * @return The total path length
	 * 
	 */
	private int totalPathLength( BSTnode<K> n, int depth )
	{
		// two base cases
		// N is null
		if ( n == null )
		{
			return 0;
		}

		// N is a leaf
		if ( isLeafNode( n ) )
		{
			return depth;
		}

		// general cases
		return ( depth + totalPathLength( n.getLeft(), depth + 1 )
				+ totalPathLength( n.getRight(), depth + 1 ) );
	}

	/**
	 * Returns an iterator over the Dictionary that iterates over the keys in
	 * the Dictionary in order from smallest to largest.
	 *
	 * @param None
	 * @return an iterator over the keys in the Dictionary in order
	 * 
	 */
	@Override
	public Iterator<K> iterator()
	{
		return new BSTDictionaryIterator<K>( this.root );
	}
}
