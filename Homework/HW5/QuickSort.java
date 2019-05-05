import java.util.ArrayList;
import java.util.Collections;

public class QuickSort
{
	private static <E extends Comparable<E>> int partition(E[] A, int low, int high) {
		    // precondition: A.length > 3

		    E pivot = medianOfThree(A, low, high); // this does step 1
		    int left = low+1; 
		    int right = high-2;
		    while ( left <= right ) {
		        while (A[left].compareTo(pivot) < 0) left++;
		        while (A[right].compareTo(pivot) > 0) right--;
		        if (left <= right) {
		            swap(A, left, right);
		            output(A);
		            left++;
		            right--;
		        }
		    }

		    swap(A, right+1, high-1); // step 4
		    output(A);
		    return right;
		}
	public static <E extends Comparable<E>> void swap ( E[] A, int left, int right ) 
	{
		E tmp = A[left];
		A[left] = A[right];
		A[right] = tmp;
	}
	
	public static <E extends Comparable<E>> void output ( E[] A ) 
	{
		for (int i = 0; i < A.length; i++) 
		{
			System.out.print( A[i] + "," );
		}
		System.out.println( "" );
	}
	
	public static <E extends Comparable<E>> E medianOfThree ( E[] A, int low, int high ) 
	{
		ArrayList<E> arr = new ArrayList<E>();
		arr.add( A[low] );
		arr.add( A[high] );
		arr.add( A[ (low + high) /2 ] );
		Collections.sort( arr );
		A[low] = arr.get(  0 );
		A[high] = arr.get( 2 );
		A[ (low + high) /2 ] = A[high -1];
		A[high -1] = arr.get( 1 );
		return arr.get( 1 );
	}
	
	
 
	public static void main( String[] args )
	{
		// TODO Auto-generated method stub
		Integer[] AA = {80,	90,	50,	10,	80,	70,	30,	40,	70,	50,	40,	20,	60};
		partition(AA, 0, 12);

	}

}
