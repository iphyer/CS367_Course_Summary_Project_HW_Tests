
public class Q3
{

	public static void main( String[] args )
	{
		// Tree 1 for test
		/*
		BSTnode<Integer> root = new BSTnode<Integer>(6);
		
		root.setLeft( new BSTnode<Integer>(4) );
		root.setRight( new BSTnode<Integer>(9) );
		
		root.getLeft().setLeft( new BSTnode<Integer>(2) );
		root.getRight().setLeft( new BSTnode<Integer>(7) );
		*/
		
		// Tree 2 for test
		BSTnode<Integer> root = new BSTnode<Integer>(4);

		root.setLeft( new BSTnode<Integer>(9999) );
		//root.setRight( new BSTnode<Integer>(7) );
		
		// root.getLeft().setLeft( new BSTnode<Integer>(1) );
		root.getLeft().setRight( new BSTnode<Integer>(3) );
		//root.getRight().setLeft( new BSTnode<Integer>(5) );
		//root.getRight().setRight( new BSTnode<Integer>(9) );

		System.out.println( root.secondSmallest(root) );
	}
	


}
