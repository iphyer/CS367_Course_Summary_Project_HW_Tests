import java.util.ArrayList;
import java.util.List;

public class Q2
{

	public static void main( String[] args )
	{
		// TODO Auto-generated method stub
		BSTnode<Integer> root = new BSTnode<Integer>(-6);
		// layer 1
		/*
		root.setLeft( new BSTnode<Integer>(-6) );
		root.setRight( new BSTnode<Integer>(-6) );
		// layer 2
		root.getLeft().setLeft( new BSTnode<Integer>(-9) );
		root.getRight().setRight( new BSTnode<Integer>(-1) );
		root.getRight().setLeft( new BSTnode<Integer>(-8) );
		// layer 3
		root.getLeft().getLeft().setLeft( new BSTnode<Integer>(-7) );
		root.getLeft().getLeft().setRight( new BSTnode<Integer>(-3) );
		root.getRight().getRight().setRight( new BSTnode<Integer>(-2) );
		root.getRight().getRight().setLeft( new BSTnode<Integer>(-2999) );
		root.getRight().getLeft().setRight( new BSTnode<Integer>(-12) );
		root.getRight().getLeft().setLeft( new BSTnode<Integer>(-1299) );
		// Layer 4
		*/
		System.out.println( findNegatives(root));

	}
	
    
    public static List<Integer> findNegatives( BSTnode<Integer> n)
    {
      	 ArrayList<Integer> result = new ArrayList<Integer>();
    		// empty tree and leaf node return empty list
    		if ( ( n == null ) )
    		{
    			return new ArrayList<Integer>();
    		}
    		
    		//  leaf child node 
    		if ( n.getLeft() == null && n.getRight() == null) 
    		{ 
    			ArrayList<Integer> tmp = new ArrayList<Integer>();
    			
    			if ( n.getData() < 0) 
    			{	
    				tmp.add( n.getData() );			
    			}
    			return ( tmp );
 
    		}
    		
    		// general cases
    		if( n.getData() < 0) 
    		{
    			result.add( n.getData() ) ;
    		}
		result.addAll( findNegatives( n.getLeft()) );
		result.addAll( findNegatives( n.getRight()) );
    	    return result;
    }

}
