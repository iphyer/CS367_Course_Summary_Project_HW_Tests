
import java.util.Iterator;

/**
 * A Loop ADT is a circular sequence of items.  A Loop has a current item 
 * and the ability to move forward or backwards.  A Loop can be modified by 
 * removing the current item or by adding an item before the current item.
 */
public interface LoopADT<E> {
    
    /**
     * Adds the given item immediately <em>before</em> the current 
     * item.  After the new item has been added, the new item becomes the 
     * current item.
     * 
     * @param item the item to add
     */
    void add(E item);
    
    /**
     * Returns the current item.  If the Loop is empty, an 
     * <tt>EmptyLoopException</tt> is thrown.
     * 
     * @return the current item
     * @throws EmptyLoopException if the Loop is empty
     */
    E getCurrent() throws EmptyLoopException;
    
    /**
     * Removes and returns the current item.  The item immediately 
     * <em>after</em> the removed item then becomes the  current item.  
     * If the Loop is empty initially, an <tt>EmptyLoopException</tt> 
     * is thrown.
     * 
     * @return the removed item
     * @throws EmptyLoopException if the Loop is empty
     */
    E removeCurrent() throws EmptyLoopException;
    
    /**
     * Advances current forward one item resulting in the item that is 
     * immediately <em>after</em> the current item becoming the current item.
     */
    void next();
    
    /**
     * Moves current backwards one item resulting in the item that is 
     * immediately <em>before</em> the current item becoming the current item.
     */
    void previous();
    
    /**
     * Determines if this Loop is empty, i.e., contains no items.
     * @return true if the Loop is empty; false otherwise
     */
    boolean isEmpty();
    
    /**
     * Returns the number of items in this Loop.
     * @return the number of items in this Loop
     */
    int size();
    
    /**
     * Returns an iterator for this Loop.
     * @return an iterator for this Loop
     */
    Iterator<E> iterator();
}
