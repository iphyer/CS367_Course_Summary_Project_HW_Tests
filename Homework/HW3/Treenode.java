import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <tt>BSTnode</tt> implements a node for a binary search tree (BST).  Each 
 * <tt>BSTnode</tt> keeps track of a key as well as its children.
 * 
 * DO NOT CHANGE THIS FILE
 * 
 * @author CS 367
 * @param <K> class representing the key, should implement the Comparable<K> 
 *            interface 
 */
class Treenode<T> {
    // Data members
    private T data;                   // the key for this node
    private List<Treenode<T>> children;  // the left and right children
 
    //Default constructor
    public Treenode()
    {
       super();
       children = new ArrayList<Treenode<T>>();
       //System.out.println( children.size() );
    }

    public Treenode(T data)
    {
       this();
       this.data =data;
    }
    
    // methods
    public T getData() { return data; }
    
    public List<Treenode<T>> getChildren() { return children; }
    
    // isBinary
    public boolean isBinary( Treenode<T> n) 
    {
    	     /* Base case */
    		// An empty tree is a binary tree
    		if ( n == null) 
    		{
    			return true;
    		}

    		// A leaf node is a binary tree
    		if ( n.getChildren().isEmpty() ) 
    		{
    			return true;
    		}
    		
    		/*General case */
    		// A node with 1 or 2 Children is binary only when each child is binary
    		if ( n.getChildren().size() == 1 || n.getChildren().size() == 2) 
    		{
        		// recursively use isBinary for each children tree
        		Iterator<Treenode<T>> iter = n.getChildren().iterator();
        		while ( iter.hasNext() ) 
        		{
        			// if this child is not binary
        			if ( !isBinary(iter.next())) 
        			{
        				return false;
        			}
        		}
        		// all children are binary
        		return true;
    		}
    		// size is 3 or larger 
		return false;
    }


}


