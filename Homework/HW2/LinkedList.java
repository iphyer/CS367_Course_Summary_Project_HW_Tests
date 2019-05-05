
public class LinkedList<E> implements ListADT<E>
{
	private DblListnode<E> items;  // pointer to the header node
	private int numItems;
	
	   public LinkedList() {
		    numItems = 0;
			items = new DblListnode<E>(null);
		}




	@Override
	public void add( E item )
	{
		// TODO Auto-generated method stub
		
	}




	@Override
	public void add( int pos, E item )
	{

	}




	@Override
	public boolean contains( E item )
	{
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return numItems;
	}




	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return (size() == 0);
	}




	@Override
	public E get( int pos )
	{
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public E remove( int pos )
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	// revesere V1
	
	   public void reverse(int pos1, int pos2){
		   // If pos1 == pos2, reversing a single list element does nothing
		   // If pos1 > pos2, reversing an expty sublist does nothing
		   if (pos1 >= pos2)
		      return;
		 									
		   // We swap the 1st and last items in the sublist,
		   //  then recursively reverse the remaining sublist
		   // We stop when the remaining sublist has size 0 or 1

		   // Swap list items at pos1 and pos2
		   E temp = remove(pos2);
		   add(pos2, get(pos1));
		   remove(pos1);
		   add(pos1, temp);

		   // Now recursively reverse remainer of sublist (if any)
		   // The remaining sublist is from pos1+1 to pos2-1
		   reverse(pos1+1, pos2-1);
		 }
	
	   public void reverse2(int pos1, int pos2) 
	   {
		   // If pos1 == pos2, reversing a single list element does nothing
		   // If pos1 > pos2, reversing an empty sublist does nothing
		   if (pos1 >= pos2)
		      return;
		   
		   // Now pos1 must be smaller than pos2, then
		   // check whether pos1 and pos2 in the allowed range of index (0 ~ numItems-1)
		   if (pos1 >= numItems || pos1 < 0 || pos2 >= numItems || pos2 < 0 )
			   return;
		   
		   // edge cases
		   // (1) empty lists
		   if (numItems == 0) {
			   return;
		   }
		   // (2) single nodes list
		   //     reversing a single list element does nothing
		   if ( numItems == 1) {
			   return;
		   }
		   
		   // (3) double nodes list 
		   //     only need to adjust two nodes
		   if ( numItems == 2) {
			   // use two pointer points to two nodes
			   DblListnode<E> tmp1 = items.getNext();
			   DblListnode<E> tmp2 = tmp1.getNext();
			   // from dummy head node adjust the two nodes
			   items.setNext( tmp2 );
			   tmp2.setNext( tmp1 );
			   tmp2.setPrev(items);
			   tmp1.setPrev( tmp2 );
			   tmp1.setNext( null );
			   return;
		   }
		   
		   // (3) more than 2 items list
		   // move through the list and adjust the pointers accordingly
		   // we will process the last node of sublist individually to avoid null 
		   // problems of next nodes
		   
		   // pointer points to the starting nodes of the sublist to be reversed
		   // also the last nodes of the sublist after reversing
		   DblListnode<E> sublistHeadNode = items;

		   // Internal counter
		   int i = 0;
		   // get head node of sublist
		   while( i <= pos1 ) 
		   {
			   sublistHeadNode = sublistHeadNode.getNext();
			   i++;
		   } 
		   
		   // reverse sublist
		   int j = pos1;
		   int k = pos2;
		   // the ending nodes of the lists that do not needed to be reversed
		   DblListnode<E> origialListHeadTail = sublistHeadNode.getPrev();	
		   // pointer to current node that needed to be reversed
		   // now is the nodes after headed nodes of sublist
		   DblListnode<E> reverseIter = sublistHeadNode.getNext();
		   // reverse head nodes of sublist to the nodes after it before reversing
		   sublistHeadNode.setPrev( reverseIter );

		   while ( j < k - 1) // reverseIter points to next nodes of head nodes of sublist
		   {                  
			   // prev and next nodes of current nodes in sublist
			   DblListnode<E> tmp_prev = reverseIter.getPrev();
			   DblListnode<E> tmp_next = reverseIter.getNext();
			   // reverse pointers of current nodes
			   reverseIter.setPrev( tmp_next );
			   reverseIter.setNext( tmp_prev );
			   // move current nodes to the next nodes of sublist
			   reverseIter = tmp_next;
			   // increase counter of the number of nodes processed 
			   j++;
		   }
		   // reverseIter points to last nodes of sublist now and nothing done on it now
		   // now linking the ending node of first part of main list to sublist
		   origialListHeadTail.setNext( reverseIter );
		   // now linking the beginning node of second part of main list to sublist
		   sublistHeadNode.setNext( reverseIter.getNext() );
		   // reverse pointers of last nodes of sublist, 
		   // also the beginning node of the reversed sublist 
		   reverseIter.setNext( reverseIter.getPrev() );
		   reverseIter.setPrev( origialListHeadTail );
	   }
	   




	public static void main( String[] args )
	{
		// TODO Auto-generated method stub
		// test examples
		// Generating new nodes for the list
		LinkedList<Integer> ll = new LinkedList<Integer> ();
		//DblListnode<Integer> dummyHead = new DblListnode(0);
		ll.numItems = 0;
		DblListnode<Integer> tmp_prev = ll.items;
		//DblListnode<Integer> tmp_next = null;
		for( int num = 1; num < 6; num++) {
			DblListnode<Integer> tmp = new DblListnode<Integer>( tmp_prev , num, null);
			tmp_prev.setNext( tmp );
			tmp_prev = tmp;
			ll.numItems++;
		} 
		//Printxs
		 //System.out.println( ll );
		 //System.out.println( ll.items );
		 //System.out.println( ll.items.getNext().getData() );
		 ///** 
		 DblListnode<Integer> tmp = ll.items.getNext() ;
		   for (int i = 0; i < ll.numItems; i++) 
		   {
			   //System.out.println( ll );
			   System.out.print( tmp.getData() + ",");
			   tmp = tmp.getNext();
		   }
		// test examples
		   System.out.println("");
		   System.out.println("after");
		   ll.reverse2(1,3);
		   
			 DblListnode<Integer> tmp1 = ll.items.getNext() ;
			   for (int i = 0; i < ll.numItems; i++) 
			   {
				   //System.out.println( ll );
				   System.out.print( tmp1.getData() + ",");
				   tmp1 = tmp1.getNext();
			   }
	}

}
