import java.util.NoSuchElementException;

///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: ( TestSort.java )
// Files: ( ComparisonSort.java)
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
// on-line reading of class
// http://pages.cs.wisc.edu/~hasti/cs367-common/readings/Sorting/index.html
//
//////////////////////////// 80 columns wide ////////////////////////////////

/**
 * This class implements six different comparison sorts (and an optional seventh
 * sort for extra credit):
 * <ul>
 * <li>selection sort</li>
 * <li>insertion sort</li>
 * <li>merge sort</li>
 * <li>quick sort</li>
 * <li>heap sort</li>
 * <li>selection2 sort</li>
 * <li>(extra credit) insertion2 sort</li>
 * </ul>
 * It also has a method that runs all the sorts on the same input array and
 * prints out statistics.
 */

public class ComparisonSort
{

	///////////////////////////
	// Data Members (fields)
	///////////////////////////

	// number of data
	private static int datamoves = 0;

	///////////////////////////
	// Constructors
	///////////////////////////

	// NONE

	///////////////////////////
	// Methods
	///////////////////////////

	/**
	 * Sorts the given array using the selection sort algorithm. You may use
	 * either the algorithm discussed in the on-line reading or the algorithm
	 * discussed in lecture (which does fewer data moves than the one from the
	 * on-line reading). Note: after this method finishes the array is in sorted
	 * order.
	 * 
	 * @param <E>
	 *            the type of values to be sorted
	 * @param A
	 *            the array to sort
	 * @return None
	 */
	public static < E extends Comparable<E> > void selectionSort( E[] A )
	{
		int i, j, minIndex;
		E min;
		int N = A.length;

		for ( i = 0; i < N - 1; i++ )
		{
			min = A[i];
			// Any assignment to an item from a class
			// that implements the Comparable interface (such as SortObject)
			// counts as a data move.

			// add dataMoves()
			ComparisonSort.increaseDataMoves( 1 );
			minIndex = i;

			for ( j = i + 1; j < N; j++ )
			{
				if ( A[j].compareTo( min ) < 0 )
				{
					min = A[j];
					// add dataMoves()
					ComparisonSort.increaseDataMoves( 1 );
					minIndex = j;
				}
			}
			A[minIndex] = A[i];
			A[i] = min;
			// add dataMoves()
			ComparisonSort.increaseDataMoves( 2 );
		}
	}

	/**
	 * Sorts the given array using the insertion sort algorithm. Note: after
	 * this method finishes the array is in sorted order.
	 * 
	 * @param <E>
	 *            the type of values to be sorted
	 * @param A
	 *            the array to sort
	 * @return None
	 */
	public static < E extends Comparable<E> > void insertionSort( E[] A )
	{
		int i, j;
		E tmp;
		int N = A.length;

		for ( i = 1; i < N; i++ )
		{
			tmp = A[i];
			// add dataMoves()
			ComparisonSort.increaseDataMoves( 1 );
			j = i - 1;
			// check array already sorted where is the right position for
			// current item
			while ( ( j >= 0 ) && ( A[j].compareTo( tmp ) > 0 ) )
			{
				// move one value over one place to the right
				A[j + 1] = A[j];
				// add dataMoves()
				ComparisonSort.increaseDataMoves( 1 );
				j--;
			}
			// insert i th value in correct place relative
			A[j + 1] = tmp;
			// add dataMoves()
			ComparisonSort.increaseDataMoves( 1 );
		}
	}

	/**
	 * Sorts the given array using the merge sort algorithm. Note: after this
	 * method finishes the array is in sorted order.
	 * 
	 * @param <E>
	 *            the type of values to be sorted
	 * @param A
	 *            the array to sort
	 * @return None
	 */
	public static < E extends Comparable<E> > void mergeSort( E[] A )
	{
		mergeAux( A, 0, A.length - 1 );
	}

	/**
	 * mergeAux, the helper method for mergesort
	 * 
	 * @param A
	 *            array to be sorted
	 * @param low
	 *            starting sorting index
	 * @param high
	 *            ending sorting index
	 * @return None
	 */
	private static < E extends Comparable<E> > void mergeAux( E[] A, int low,
			int high )
	{
		// base case
		if ( low == high )
			return;
		// find the middle of the array
		int mid = ( low + high ) / 2;
		// sort two halves of the array
		mergeAux( A, low, mid );
		mergeAux( A, mid + 1, high );
		// Merge sorted halves into a new array
		E[] tmp = ( E[] ) ( new Comparable[high - low + 1] );
		int left = low;
		int right = mid + 1;
		int pos = 0;
		while ( ( left <= mid ) && ( right <= high ) )
		{
			// always chose the smaller one in two halves into the result array
			if ( A[left].compareTo( A[right] ) <= 0 )
			{
				tmp[pos] = A[left];
				// add dataMoves()
				ComparisonSort.increaseDataMoves( 1 );
				left++;
			}
			else
			{
				tmp[pos] = A[right];
				// add dataMoves()
				ComparisonSort.increaseDataMoves( 1 );
				right++;
			}
			pos++;
		}
		// when one of the two halves runs out of elements
		// but another half still has elements, copy all elements in that half
		// left half
		while ( left <= mid )
		{
			tmp[pos] = A[left];
			// add dataMoves()
			ComparisonSort.increaseDataMoves( 1 );
			left++;
			pos++;
		}
		// right half
		while ( right <= high )
		{
			tmp[pos] = A[right];
			// add dataMoves()
			ComparisonSort.increaseDataMoves( 1 );
			right++;
			pos++;
		}
		// copy all values in tmp to the input array
		System.arraycopy( tmp, 0, A, low, tmp.length );
		// add dataMoves()
		ComparisonSort.increaseDataMoves( tmp.length );
	}

	/**
	 * Sorts the given array using the quick sort algorithm, using the median of
	 * the first, last, and middle values in each segment of the array as the
	 * pivot value. Note: after this method finishes the array is in sorted
	 * order.
	 * 
	 * @param <E>
	 *            the type of values to be sorted
	 * @param A
	 *            the array to sort
	 * @return None
	 */
	public static < E extends Comparable<E> > void quickSort( E[] A )
	{
		// check the length of A make sure A is not null
		if ( A == null || A.length == 0 )
		{
			return;
		}
		quickSortAux( A, 0, A.length - 1 );
	}

	/**
	 * quickSortAux, the helper method for quickSort
	 * 
	 * @param A
	 *            array to be sorted
	 * @param low
	 *            starting sorting index
	 * @param high
	 *            ending sorting index
	 * @return None
	 */
	private static < E extends Comparable<E> > void quickSortAux( E[] A,
			int low, int high )
	{
		// if we can call quickSortAux then A must not be null or empty
		// calculate arrayLengh
		int arrayLength = high - low + 1;
		// base case 1
		// arrayLengh is 1
		// do nothing
		if ( arrayLength <= 1 )
		{
			return;
		}
		// base case 2
		// arrayLengh is 2
		// swap if necessary
		if ( arrayLength == 2 )
		{
			if ( A[low].compareTo( A[high] ) > 0 )
			{
				// not right order, so swap the two elements
				swap( A, low, high );
			}
			return;
		}
		// base case 3
		// arrayLengh is 3
		if ( arrayLength == 3 )
		{
			sort3elements( A, low, low + 1, high );
			return;
		}
		// General case the length of array is great than 3
		int right = partition( A, low, high );
		quickSortAux( A, low, right );
		quickSortAux( A, right + 2, high );
	}

	/**
	 * helper function for quickSort
	 * 
	 * for base case where array length is 3
	 * 
	 * We only need to compare three elements to know how to put the elements in
	 * right positions
	 * 
	 * @param A
	 *            array to be sorted
	 * @param pos1
	 *            position of the first element of swapping
	 * @param pos2
	 *            position of the second element of swapping
	 * @param pos3
	 *            position of the third element of swapping
	 * @return None
	 * 
	 */
	private static < E extends Comparable<E> > void sort3elements( E[] A,
			int pos1, int pos2, int pos3 )
	{

		// if A[pos1] > A[pos2], swap
		if ( A[pos1].compareTo( A[pos2] ) > 0 )
		{
			swap( A, pos1, pos2 );
		}

		// if A[pos1] > A[pos3], swap
		if ( A[pos1].compareTo( A[pos3] ) > 0 )
		{
			swap( A, pos1, pos3 );
		}

		// Now A[pos1] must be the smallest element

		// Just need to check the A[pos2] and A[pos3] elements
		// if A[pos3] is bigger, do nothing
		// if A[pos2] is bigger, swap
		if ( A[pos2].compareTo( A[pos3] ) > 0 )
		{
			swap( A, pos2, pos3 );
		}
		return;
	}

	/**
	 * partition, the helper method for partitioning the array
	 * 
	 * Partition the array (put all value less than the pivot in the left part
	 * of the array, then the pivot itself, then all values greater than the
	 * pivot). Copies of the pivot value can go in either part of the array.
	 * 
	 * @param A
	 *            array to be sorted
	 * @param low
	 *            starting sorting index
	 * @param high
	 *            ending sorting index
	 * @return right the index of the right pointer after partition
	 */
	private static < E extends Comparable<E> > int partition( E[] A, int low,
			int high )
	{
		// Choose a pivot value.
		E pivot = medianOfThree( A, low, high );
		// add dataMoves()
		ComparisonSort.increaseDataMoves( 1 );
		// Initialize
		int left = low + 1;
		int right = high - 2;
		// loop if left and right have not crossed each other
		while ( left <= right )
		{
			// left is incremented until it "points" to a value > the pivot
			while ( A[left].compareTo( pivot ) < 0 )
				left++;
			// right is decremented until it "points" to a value < the pivot
			while ( A[right].compareTo( pivot ) > 0 )
				right--;
			// if left and right have not crossed each other
			if ( left <= right )
			{
				swap( A, left, right );
				left++;
				right--;
			}
		}
		// Put the pivot into its final place.
		swap( A, right + 1, high - 1 );
		return right;
	}

	/**
	 * medianOfThree, the helper method for quickSort
	 * 
	 * choose the median of the values in A[low], A[high], and A[(low+high)/2]
	 * 
	 * put the smallest of the 3 values in A[low], put the largest of the 3
	 * values in A[high], and swap the pivot with the value in A[high-1].
	 * 
	 * @param A
	 *            array to be sorted
	 * @param low
	 *            starting sorting index
	 * @param high
	 *            ending sorting index
	 * @return pivot the chosen pivot element
	 * 
	 */
	private static < E extends Comparable<E> > E medianOfThree( E[] A, int low,
			int high )
	{
		// the position of middle element
		int mid = ( low + high ) / 2;
		// sort the 3 elements using previous helper method sort3elements()
		sort3elements( A, low, mid, high );
		// pivot is in the middle of the array
		E pivot = A[mid];
		// swap the pivot with the value in A[high-1].
		swap( A, mid, high - 1 );
		return pivot;
	}

	/**
	 * Sorts the given array using the heap sort algorithm outlined below. Note:
	 * after this method finishes the array is in sorted order.
	 * <p>
	 * The heap sort algorithm is:
	 * </p>
	 * 
	 * <pre>
	 * for each i from 1 to the end of the array
	 *     insert A[i] into the heap (contained in A[0]...A[i-1])
	 *     
	 * for each i from the end of the array up to 1
	 *     remove the max element from the heap and put it in A[i]
	 * </pre>
	 * 
	 * @param <E>
	 *            the type of values to be sorted
	 * @param A
	 *            the array to sort
	 */
	public static < E extends Comparable<E> > void heapSort( E[] A )
	{
		// check the length of A make sure A is not null
		if ( A == null || A.length == 0 )
		{
			return;
		}
		// heapify
		E[] maxHeapArr = heapify( A );
		// remove the max elements in heap and insert it into the end of array A
		for ( int i = A.length; i > 0; i-- )
		{
			A[i - 1] = heapRemoveMax( maxHeapArr, i );
		}
	}

	/**
	 * Removes and returns the item with the highest priority.
	 *
	 * @param None
	 * @throws NoSuchElementException
	 *             - if the heap is empty
	 * @return item the largest item in heap now
	 * 
	 */
	private static < E extends Comparable<E> > E heapRemoveMax( E[] maxHeapArr,
			int numItems )
	{
		// get the largest item which is at index 1
		E maxResult = maxHeapArr[1];
		// Replace the value in the root with the value at the end of the array
		// (which corresponds to the heap's rightmost leaf at depth d).
		maxHeapArr[1] = maxHeapArr[numItems];
		// Remove that leaf from the tree.
		numItems = numItems - 1;

		// work your way down the tree, swapping values to restore the order
		// property:
		// each time, if the value in the current node is less than
		// one of its children, then swap its value with the larger child
		int currIndex = 1; // starting from index 1
		// no reach the end of the heap array
		while ( 2 * currIndex < numItems )
		{
			// if parent[currIndex] is smaller than its children [currIndx * 2]
			// and [currIndex * 2 + 1]
			if ( maxHeapArr[currIndex]
					.compareTo( maxHeapArr[currIndex * 2] ) < 0
					|| maxHeapArr[currIndex]
							.compareTo( maxHeapArr[currIndex * 2 + 1] ) < 0 )
			{
				// pick the larger child node
				if ( maxHeapArr[currIndex * 2]
						.compareTo( maxHeapArr[currIndex * 2 + 1] ) > 0 )
				{
					swap( maxHeapArr, currIndex * 2, currIndex );
					// now currIndex changes to new position of last leaf
					currIndex = 2 * currIndex;
				}
				else
				{
					swap( maxHeapArr, currIndex * 2 + 1, currIndex );
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
	 * heapify, create the array for the heap
	 * 
	 * @param A
	 *            array to be sorted
	 * @return arr the array for heap
	 * 
	 */
	private static < E extends Comparable<E> > E[] heapify( E[] A )
	{
		// create heap array
		// add 1 because the first item in maxHeapArr should be empty
		E[] maxHeapArr = ( E[] ) ( new Comparable[A.length + 1] );
		int currentIndex = 1;
		// loop all elements in input array A
		for ( E item : A )
		{
			// add item to the end of the array
			maxHeapArr[currentIndex] = item;
			// Comparing the new value to the value in its parent.
			// If the parent is smaller, we swap the values, and
			// we continue this check-and-swap procedure up the tree until
			// we find that the order property holds, or we get to the root.
			int tmpIndex = currentIndex;
			// goes up the tree until get to the root, which is at index 1
			while ( tmpIndex > 1 )
			{
				// if item is larger than its parent
				if ( item.compareTo( maxHeapArr[tmpIndex / 2] ) > 0 )
				{
					// swap item and its parent
					swap( maxHeapArr, tmpIndex, tmpIndex / 2 );

				}
				// goes up the next layer of parents
				tmpIndex = tmpIndex / 2;
			}
			// increase currentIndex
			currentIndex++;
		}
		return maxHeapArr;
	}

	/**
	 * Sorts the given array using the selection2 sort algorithm outlined below.
	 * Note: after this method finishes the array is in sorted order.
	 * <p>
	 * The selection2 sort is a bi-directional selection sort that sorts the
	 * array from the two ends towards the center. The selection2 sort algorithm
	 * is:
	 * </p>
	 * 
	 * <pre>
	 * begin = 0, end = A.length-1
	 * 
	 * // At the beginning of every iteration of this loop, we know that the 
	 * // elements in A are in their final sorted positions from A[0] to A[begin-1]
	 * // and from A[end+1] to the end of A.  That means that A[begin] to A[end] are
	 * // still to be sorted.
	 * do
	 *     use the MinMax algorithm (described below) to find the minimum and maximum 
	 *     values between A[begin] and A[end]
	 *     
	 *     swap the maximum value and A[end]
	 *     swap the minimum value and A[begin]
	 *     
	 *     ++begin, --end
	 * until the middle of the array is reached
	 * </pre>
	 * <p>
	 * The MinMax algorithm allows you to find the minimum and maximum of N
	 * elements in 3N/2 comparisons (instead of 2N comparisons). The way to do
	 * this is to keep the current min and max; then
	 * </p>
	 * <ul>
	 * <li>take two more elements and compare them against each other</li>
	 * <li>compare the current max and the larger of the two elements</li>
	 * <li>compare the current min and the smaller of the two elements</li>
	 * </ul>
	 * 
	 * @param <E>
	 *            the type of values to be sorted
	 * @param A
	 *            the array to sort
	 */
	public static < E extends Comparable<E> > void selection2Sort( E[] A )
	{
		// loop array
		// to avoid back-going, only need first half for i
		for ( int i = 0; i <= ( A.length - 2 ) / 2; i++ )
		{
			int begin = i;
			int end = A.length - 1 - i;

			int minIndex = begin;
			int maxIndex = end;
			// inner loop
			for ( int j = i; j <= ( A.length - 1 ) / 2; j++ )
			{
				// take two more elements and compare them against each other
				int left = j;
				int right = A.length - 1 - j;

				if ( A[left].compareTo( A[right] ) < 0 )
				{
					// A[left] < A[right]
					// compare the current min and the smaller of the two
					// elements
					if ( A[left].compareTo( A[minIndex] ) < 0 )
					{
						minIndex = left;
					}
					// compare the current max and the larger of the two
					// elements
					if ( A[right].compareTo( A[maxIndex] ) > 0 )
					{
						maxIndex = right;
					}
				}
				else // A[left] >= A[right]
				{
					// compare the current min and the smaller of the two
					// elements
					if ( A[right].compareTo( A[minIndex] ) < 0 )
					{
						minIndex = right;
					}

					// compare the current max and the larger of the two
					// elements
					if ( A[left].compareTo( A[maxIndex] ) > 0 )
					{
						maxIndex = left;
					}
				}
			}
			// finish updating maxIndex and minIndex
			// then swap index
			// special cases
			// maxIndex is at the beginning, must avoid overwriting A[maxIndex]
			if ( maxIndex == begin )
			{
				if ( minIndex == end )
				{
					// maxIndex == begin && minIndx = end
					// only need one swap
					swap( A, minIndex, maxIndex );
				}
				else
				{
					// must swap maxIndex first
					// otherwise A[minIndex] overwrites A[maxIndex]
					swap( A, maxIndex, end );
					swap( A, minIndex, begin );
				}
			}
			else
			{
				// general case
				swap( A, minIndex, begin );
				swap( A, maxIndex, end );
			}
		}
	}

	/**
	 * <b>Extra Credit:</b> Sorts the given array using the insertion2 sort
	 * algorithm outlined below. Note: after this method finishes the array is
	 * in sorted order.
	 * <p>
	 * The insertion2 sort is a bi-directional insertion sort that sorts the
	 * array from the center out towards the ends. The insertion2 sort algorithm
	 * is:
	 * </p>
	 * 
	 * <pre>
	 * precondition: A has an even length
	 * left = element immediately to the left of the center of A
	 * right = element immediately to the right of the center of A
	 * if A[left] > A[right]
	 *     swap A[left] and A[right]
	 * left--, right++ 
	 *  
	 * // At the beginning of every iteration of this loop, we know that the elements
	 * // in A from A[left+1] to A[right-1] are in relative sorted order.
	 * do
	 *     if (A[left] > A[right])
	 *         swap A[left] and A[right]
	 *  
	 *     starting with A[right] and moving to the left, use insertion sort 
	 *     algorithm to insert the element at A[right] into the correct location 
	 *     between A[left+1] and A[right-1]
	 *     
	 *     starting with A[left] and moving to the right, use the insertion sort 
	 *     algorithm to insert the element at A[left] into the correct location 
	 *     between A[left+1] and A[right-1]
	 *  
	 *     left--, right++
	 * until left has gone off the left edge of A and right has gone off the right 
	 *       edge of A
	 * </pre>
	 * <p>
	 * This sorting algorithm described above only works on arrays of even
	 * length. If the array passed in as a parameter is not even, the method
	 * throws an IllegalArgumentException
	 * </p>
	 *
	 * @param A
	 *            the array to sort
	 * @throws IllegalArgumentException
	 *             if the length or A is not even
	 */
	public static < E extends Comparable<E> > void insertion2Sort( E[] A )
	{
		// make sure the array length is even
		if ( ( A.length % 2 ) != 0 )
		{
			throw new IllegalArgumentException();
		}
		// get right position and left position
		int right = A.length / 2;
		int left = right - 1;
		// if A[left] and A[right] are out of order
		if ( A[left].compareTo( A[right] ) > 0 )
		{
			swap( A, right, left );
		}

		// do until left has gone off the left edge of A and right has gone off
		// the right edge of A
		while ( left >= 0 )
		{
			if ( A[left].compareTo( A[right] ) > 0 )
			{
				swap( A, right, left );
			}

			// Using insertionSort to insert A[right] into the correct location
			// between A[left+1] and A[right-1]
			for ( int i = right; i > left; i-- )
			{
				if ( A[i].compareTo( A[i - 1] ) < 0 )
				{
					swap( A, i, i - 1 );
				}
			}
			// Using insertionSort to insert A[left] into the correct location
			// between A[left+1] and A[right-1]
			for ( int i = left; i < right; i++ )
			{
				if ( A[i].compareTo( A[i + 1] ) > 0 )
				{
					swap( A, i, i + 1 );
				}
			}
			// update left and right index
			left--;
			right++;
		}
	}

	/**
	 * Internal helper for printing rows of the output table.
	 * 
	 * @param sort
	 *            name of the sorting algorithm
	 * @param compares
	 *            number of comparisons performed during sort
	 * @param moves
	 *            number of data moves performed during sort
	 * @param milliseconds
	 *            time taken to sort, in milliseconds
	 */
	private static void printStatistics( String sort, int compares, int moves,
			long milliseconds )
	{
		System.out.format( "%-23s%,15d%,15d%,15d\n", sort, compares, moves,
				milliseconds );
	}

	/**
	 * Sorts the given array using the six (seven with the extra credit)
	 * different sorting algorithms and prints out statistics. The sorts
	 * performed are:
	 * <ul>
	 * <li>selection sort</li>
	 * <li>insertion sort</li>
	 * <li>merge sort</li>
	 * <li>quick sort</li>
	 * <li>heap sort</li>
	 * <li>selection2 sort</li>
	 * <li>(extra credit) insertion2 sort</li>
	 * </ul>
	 * <p>
	 * The statistics displayed for each sort are: number of comparisons, number
	 * of data moves, and time (in milliseconds).
	 * </p>
	 * <p>
	 * Note: each sort is given the same array (i.e., in the original order) and
	 * the input array A is not changed by this method.
	 * </p>
	 * 
	 * @param A
	 *            the array to sort
	 * @return None
	 */
	static public void runAllSorts( SortObject[] A )
	{
		// Internal data member to record starting time
		long startTime = 0;
		// Internal data member to record ending time
		long endTime = 0;

		System.out.format( "%-23s%15s%15s%15s\n", "algorithm", "data compares",
				"data moves", "milliseconds" );
		System.out.format( "%-23s%15s%15s%15s\n", "---------", "-------------",
				"----------", "------------" );

		// run each sort and print statistics about what it did
		// for each sorting methods:
		// (1) call resetDataMoves() and resetCompares() to reset
		// (2) copy data to a new array
		// (3) call currentTimeMillis() and record initial time
		// (4) call the sorting methods
		// (5) call currentTimeMillis() and record ending time
		// (6) printStatistics()

		// 1. selection sort
		resetDataMoves();
		SortObject.resetCompares();
		SortObject[] testArr = copyArray( A );
		startTime = System.currentTimeMillis();
		selectionSort( testArr );
		endTime = System.currentTimeMillis();
		printStatistics( "selection", SortObject.getCompares(),
				ComparisonSort.getDataMoves(), endTime - startTime );
		// System.out.println(checkSortedArray(testArr));

		// 2. insertion sort
		resetDataMoves();
		SortObject.resetCompares();
		testArr = copyArray( A );
		startTime = System.currentTimeMillis();
		insertionSort( testArr );
		endTime = System.currentTimeMillis();
		printStatistics( "insertion", SortObject.getCompares(),
				ComparisonSort.getDataMoves(), endTime - startTime );
		// System.out.println( checkSortedArray( testArr ) );

		// 3. merge sort
		resetDataMoves();
		SortObject.resetCompares();
		testArr = copyArray( A );
		startTime = System.currentTimeMillis();
		mergeSort( testArr );
		endTime = System.currentTimeMillis();
		printStatistics( "merge", SortObject.getCompares(),
				ComparisonSort.getDataMoves(), endTime - startTime );
		// System.out.println( checkSortedArray( testArr ) );

		// 4. quick sort
		resetDataMoves();
		SortObject.resetCompares();
		testArr = copyArray( A );
		startTime = System.currentTimeMillis();
		quickSort( testArr );
		endTime = System.currentTimeMillis();
		printStatistics( "quick", SortObject.getCompares(),
				ComparisonSort.getDataMoves(), endTime - startTime );
		// System.out.println( checkSortedArray( testArr ) );

		// 5. heap sort
		resetDataMoves();
		SortObject.resetCompares();
		testArr = copyArray( A );
		startTime = System.currentTimeMillis();
		heapSort( testArr );
		endTime = System.currentTimeMillis();
		printStatistics( "heap", SortObject.getCompares(),
				ComparisonSort.getDataMoves(), endTime - startTime );
		// System.out.println( checkSortedArray( testArr ) );

		// 6. selection2 sort
		resetDataMoves();
		SortObject.resetCompares();
		testArr = copyArray( A );
		startTime = System.currentTimeMillis();
		selection2Sort( testArr );
		endTime = System.currentTimeMillis();
		printStatistics( "selection2", SortObject.getCompares(),
				ComparisonSort.getDataMoves(), endTime - startTime );
		// System.out.println( checkSortedArray( testArr ) );

		// 7. insertion2 sort
		ComparisonSort.resetDataMoves();
		SortObject.resetCompares();
		testArr = copyArray( A );
		startTime = System.currentTimeMillis();
		insertion2Sort( testArr );
		endTime = System.currentTimeMillis();
		printStatistics( "insertion2", SortObject.getCompares(),
				ComparisonSort.getDataMoves(), endTime - startTime );
		// System.out.println( checkSortedArray( testArr ) );

	}

	///////////////////////////
	// Helper Methods
	///////////////////////////

	/**
	 * Internal helper for getting the number of data moves.
	 * 
	 * @param None
	 * @return datamoves the number of data moves
	 */
	private static int getDataMoves()
	{
		return datamoves;
	}

	/**
	 * Internal helper for setting the number of data moves.
	 * 
	 * @param None
	 * @return None
	 */
	private static void resetDataMoves()
	{
		datamoves = 0;
	}

	/**
	 * Internal helper for increasing the number of data moves.
	 * 
	 * @param num
	 *            the new number for data moves
	 * @return None
	 */
	private static void increaseDataMoves( int num )
	{
		datamoves += num;
	}

	/**
	 * Internal helper for copying the input array
	 * 
	 * @param A
	 *            the input array
	 * @return newArr the copied new array of input array A
	 */
	private static SortObject[] copyArray( SortObject[] A )
	{
		// create new array to hold data from input array
		SortObject[] newArr = new SortObject[A.length];
		// Array copy
		// iterate every element in input array
		// assign the data to newArr
		for ( int i = 0; i < A.length; i++ )
		{
			newArr[i] = new SortObject( A[i].getData() );
		}
		return newArr;
	}

	/**
	 * Internal helper for checking that the sorted array is indeed sorted
	 * 
	 * @param A
	 *            the sorted array
	 * @return True the sorted array is in sorted order False the sorted array
	 *         is not in sorted order
	 */
	// private static boolean checkSortedArray( SortObject[] A )
	// {
	// // loop over every items and the previous is smaller than the later one
	// // i only needs to go to length - 2
	// for ( int i = 0; i < A.length - 1; i++ )
	// {
	// if ( A[i].compareTo( A[i + 1] ) > 0 )
	// {
	// return false;
	// }
	// }
	// return true;xw
	// }

	/**
	 * Swap, the helper method for swapping two element in a array
	 * 
	 * @param A
	 *            array to be sorted
	 * @param pos1
	 *            position of the first element of swapping
	 * @param pos2
	 *            position of the second element of swapping
	 * @return None
	 */
	private static < E extends Comparable<E> > void swap( E[] A, int pos1,
			int pos2 )
	{
		E tmp = A[pos1];
		A[pos1] = A[pos2];
		A[pos2] = tmp;
		// add dataMoves()
		ComparisonSort.increaseDataMoves( 3 );
	}

}