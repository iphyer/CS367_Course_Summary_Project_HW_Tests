# CS 367 Code Essence


---
(1) Assume that variable words is a List containing k strings, for some integer k greater than or equal to zero. Write code that changes words to contain 2*k strings by adding a new copy of each string right after the old copy. For example, if words is like this before your code executes:

`"happy", "birthday", "to", "you"`
then after your code executes words should be like this:
`"happy", "happy", "birthday", "birthday", "to", "to", "you", "you"`

```java
for (int pos = 0; pos < 2*k; pos+=2) {
    words.add(pos, words.get(pos));
}
```

(3) Following question (1), Again, assume that variable words is a List containing zero or more strings. Write code that removes from words all copies of the string "hello". Be sure that your code works when words has more than one "hello" in a row.

```java
int pos = 0;
while (pos < words.size()) {
    String str = (String) words.get(pos);
    if (str.equals("hello")) {
        words.remove(pos);
    }
    else {
        pos++;
    }
}
```

(3) suppose we have an ArrayList class that implements the ListADT interface. Here's some code that reads strings from a file called data.txt and stores them in a ListADT named words, initialized to be an ArrayList of Strings:
```java
ListADT<String> words = new ArrayList<String>();
File infile = new File("data.txt");
Scanner sc = new Scanner(infile);
while (sc.hasNext()) {
    String str = sc.next();
    words.add(str);
}
```

(4)The Java interface List<E> has a method iterator() that returns an object of type Iterator<E>. You can use it to iterate through the elements of a list with a while loop like this:

```java
List<String> words = new ArrayList<String>();
... // add some words to the list
Iterator<String> itr = words.iterator();
while (itr.hasNext()) {
    String nextWord = itr.next();
    ... // do something with nextWord
}
```

or with a for loop like this (**note that the increment part of the loop header is empty, since the call to the iterator's next method inside the loop causes it to advance to the next item**):
```
List<String> words = new ArrayList<String>();
... // add some words to the list
for (Iterator<String> itr = words.iterator(); itr.hasNext(); ) {
    String nextWord = itr.next();
    ... // do something with nextWord
}
```
Java also provides **extended for-loops** (sometimes also referred to as **for-each loops**), which are another way to iterate through lists. The code looks like this:
```java
List<String> words = new ArrayList<String>();
... // add some words to the list
for (String nextWord : words) {
    ... // do something with nextWord
}
```

In this context, the colon ':' is pronounced "in", so the for statement would be read out loud as "for each String nextWord in words, do ...".

(5) the complete code for removing node n from a linked list, including the special case when n is the first node in the list:
```java
if (l == n) {
  // special case: n is the first node in the list
    l = n.getNext();
} else {
  // general case: find the node before n, then "unlink" n
    Listnode<String> tmp = l;
    while (tmp.getNext() != n) {  // find the node before n
        tmp = tmp.getNext();
    }
    tmp.setNext(n.getNext());
}
```
(6)
Write the "add at the end" method (assuming that the LinkedList class includes both a header node and a lastNode field).

```java
public void add(E d) {
    lastNode.setNext(new Listnode<E>(d));
    lastNode = lastNode.getNext();
    numItems++;
}
```
(7)
Write the "add at a given position" method (assuming that the LinkedList class includes both a header node and a lastNode field).
```java
public void add(int pos, E d) {
    // check for bad position
    if (pos < 0 || pos > numItems) {
        throw new IndexOutOfBoundsException();
    }
	
    // if asked to add to end, let the other add method do the work
    if (pos == numItems) {
        add(d);
        return;
    }
 
    // find the node n after which to add a new node and add the new node
    Listnode<E> n = items;
    for (int k = 0; k < pos; k++) {
        n = n.getNext();
    }
    n.setNext(new Listnode<E>(d, n.getNext()));
    numItems++;
}
```
(8)
Write the correct code to search for value val in a circular linked list pointed to by l. (Assume that the list contains no null data values.)

```java
// Note: this code has excessive comments to help you understand it in detail.
// Returns true if val is in circular linked list l; otherwise returns false
if (l == null)  {
    return false;            // no items in list so not found
     
if (l.getData().equals(val)) 
    return true;             // val is in the first node in the list
     
Listnode<E> tmp = l.getNext();
while (tmp != l) {           // stops from going past first node and going in circles
    if (tmp.getData().equals(val)) 
        return true;         // found val at this node
    tmp = tmp.getNext();
}
return false;                // did not find val in list
```
(9)
With circular lists, you don't need pointers to both ends of the list; a pointer to one end suffices. With singly linked circular lists, it is most convenient to use only a pointer to the last node. With the structure it is easy to write code that does each of the following three operations in **O(1)** (constant) time: `addFirst`, `addLast`, `removeFirst`
 
Write the code for these operations. Assume you're adding these operations to a class named CircularLinkedList, which has a last reference to the last node in the circular singly linked list.

```java
// Adds object "o" to the start of the list pointed to by "last"
public void addFirst(E o) {
    Listnode<E> tmp = new Listnode<E>(o);
    if (last == null) {
        tmp.setNext(tmp);
        last = tmp;
    } else {
        tmp.setNext(last.getNext());
        last.setNext(tmp);
    }
}
 
// Adds object "o" to the end of the list pointed to by "last"
public void addLast(E o) {
    addFirst(o);
    last = last.getNext();
}
 
// Removes the first node from the list pointed to by "last" 
public void removeFirst() {
    if (last == null) {
        throw new IndexOutOfBoundsException(
                        "attempt to remove from empty list");
    }
    if (last.getNext() == last) {
        // one-node list
        last = null;
    } else {
        last.setNext(last.getNext().getNext());
    }
}
```

(10) Write the ArrayStack constructor.
```java
public ArrayStack() {
    items = (E[])(new Object[INITSIZE]);
    numItems = 0;
}
```

(11)
Here's the code for the enqueue method, with the "full array" case still to be filled in:

```java
public void enqueue(E ob) {
    // check for full array and expand if necessary
    if (items.length == numItems) {
        E[] tmp = (E[])(new Object[items.length*2]);
        System.arraycopy(items, frontIndex, tmp, frontIndex,
	                 items.length-frontIndex);
        if (frontIndex != 0) {
            System.arraycopy(items, 0, tmp, items.length, frontIndex);
        }
        items = tmp;
	    rearIndex = frontIndex + numItems - 1;
    }

    // use auxiliary method to increment rear index with wraparound
    rearIndex = incrementIndex(rearIndex);

    // insert new item at rear of queue
    items[rearIndex] = ob;
    numItems++;
}

private int incrementIndex(int index) {
    if (index == items.length-1) 
        return 0;
    else 
        return index + 1;
}
```

Note that instead of using incrementIndex we could use the mod operator (%), and write: rearIndex = (rearIndex + 1) % items.length. However, the mod operator is quite slow and it is easy to get that expression wrong, so we will use the auxiliary method (with a check for the "wrap-around" case) instead.


(12)
Complete method reverseQ, whose header is given below. Method reverseQ should use a Stack to reverse the order of the items in its Queue parameter.

public static<E> void reverseQ(QueueADT<E> q) {
// precondition: q contains x1 x2 ... xN (with x1 at the front)
// postcondition: q contains xN ... x2 X1 (with xN at the front)
}

```java
public static void reverseQ(QueueADT<E> q) {
// precondition: q contains x1 x2 ... xN (with x1 at the front)
// postcondition: q contains xN ... x2 X1 (with xN at the front)
    StackADT<E> s = new ArrayStack<E>();
    while (!q.empty()) {
        s.push(q.dequeue());
    }
    while (!s.empty()) {
        q.enqueue(s.pop());
    }
}
```

(13)factorial for neagative

The iterative version of factorial will return -1 as the result of the call factorial(-1). The recursive version will go into an infinite recursion (because the base case, N==0, will never be reached).

Since, mathematically, factorial is undefined for negative numbers, a call to factorial with a negative number should cause an exception. This is easily done for the iterative version:
```java
int factorial(int N) {
    if (N < 0) {
        throw new NegativeValueException();
    }
    if (N == 0) {
        return 1;
    }
    int tmp = 1;
    for (int k = N; k > 1; k--) {
        tmp = tmp*k;
    }
    return (tmp);
}
```
(where NegativeValueException would have to be defined as a public exception).

For the recursive version, we could change the method to:
```java
int factorial(int N) {
    if (N < 0){ 
        throw new NegativeValueException();
    }
    if (N == 0) {
        return 1;
    } 
    else {
        return (N * factorial( N - 1 ));
    }
}
```
However, the problem with this is that the check for N < 0 will be made on every call, including the recursive calls where it cannot be true. This makes the method slightly less efficient. A better solution would be to make the check once, the first time factorial is called and then use an auxiliary recusive method with no check:
```java
int factorial(int N) {
    if (N < 0) {
        throw new NegativeValueException();
    } 
    else {
        return factAux( N );
    }
}

int factAux(int N) {
    if (N == 0) {
        return 1;
    }
    else {
        return (N * factAux( N - 1 ));
    }
}
```
(14)suppose we want to print the values in a linked list backwards; 
itemN
itemN-1
 ...
item2
item1

It is easy to do this using recursion; all we have to do is swap the order of the print statement and the recursive call in the Example 2 code given above. Here's the new version:

```java
printList(Listnode<E> llist) {
    if (llist == null) {
        return;
    }
    printList(llist.getNext());
    System.out.println(llist.getData());
}
```
(15)
Write a recursive method called sum with the following header:

public static int sum(Listnode<Integer> node)
Assume that node points to the first node in a linked list of Integers. The method sum should return the sum of the values in the list.

```java
public static int sum(Listnode<Integer> node) {
    if (node == null) {
        return 0;
    }
    int val = node.getData();
    return val + sum(node.getNext());
}
```

(16)
Write a recursive method called vowels with the following header:

public static String vowels(String str)
The method vowels should return a String containing just the vowels (a, e, i, o, u) in str, in the order in which they occur in str. For example, vowels("hooligan") should return "ooia".

```java

public static String vowels(String str) {
    // Base case
    if (str.length() == 0) {
        return "";
    }
 
    String ch = str.substring(0, 1);
     
    if (ch.equals("a") || ch.equals("e") || ch.equals("i")
                       || ch.equals("o") || ch.equals("u")) {
        return ch + vowels(str.substring(1));   
    } else {
        return vowels(str.substring(1));
    }
}

```

(17) A level-order traversal requires using a queue (rather than a recursive algorithm, which implicitly uses a stack). Here's how to print the data in a tree in level order, using a queue Q, and using an iterator to access the children of each node (we assume that the root node is called root and that the Treenode class provides a getChildren method):
```java
Q.enqueue(root)
while (!Q.empty()) {
    Treenode<T> n = Q.dequeue();
    System.out.print(n.getData());
    ListADT<Treenode<T>> kids = n.getChildren();
    Iterator<Treenode<T>> it = kids.iterator();
    while (it.hasNext()) {
        Q.enqueue(it.next());
    }
}
```
![Queues States][1]

(18) Complete the following implementation of printReverse function that uses recursion to
print the items in a singly linked list in the reverse order. You may assume that the
function will be called from main with argument as the head reference to the linked list.

```java
void printReverse ( Listnode < Integer > curr ) {
    if ( curr == null ) return ;
    printReverse ( curr . getNext () ) ;
    System . out . println ( curr . getData () ) ;
}
```

(19) Java palindrome method

```java
boolean isPalindrome(String s){
		if (s.length() == 0 ||
			  s.length() == 1 )
			return true;
		char first =  s.charAt(0);
		char last = 
				s.charAt(s.length()-1);
		return (first == last) &&
		  isPalindrome(
         s.substring(1, s.length()-1));
	}  
```

(20) Write the auxiliary method smallest used by the delete method given above. The header for smallest is:

```java
private K smallest(BSTnode<K> n)
// precondition: n is not null
// postcondition: return the smallest value in the subtree rooted at n
```

Method 1:
```java
private K smallest(BSTnode<K> n)
// precondition: n is not null
// postcondition: return the smallest value in the subtree rooted at n

{
    if (n.getLeft() == null) {
        return n.getKey();
    } else {
        return smallest(n.getLeft());
    }
}
```

Method 2:
```java
    private K smallest(BSTnode<K> n) {
 
        /* loop down to find the leftmost leaf */
        while ( n.getLeft() != null) {
            n = n.getLeft();
        }
        return ( n.getKey() );
    }
     
```

(21) a complete program that counts the number of occurrences of each word in a document.

```java
import java.util.*;
import java.io.*;

public class CountWords {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("usage: java CountWords file_name");
            return;
        }
        
        Map<String, Integer> wordCount = new TreeMap<String, Integer>();
        Scanner in = new Scanner(new File(args[0]));
        in.useDelimiter("\\W+");
        while (in.hasNext()) {
            String word = in.next();
            Integer count = wordCount.get(word);
            if (count == null) {
                wordCount.put(word, 1);
            } else {
                wordCount.put(word, count + 1);
            }
        }
        for (String word : wordCount.keySet()) {
            System.out.println(word + " " + count.get(word));
        }
    }
} // CountWords
```
(The statement `in.useDelimiter("\\W");` tells the Scanner that words are delimited by non-word characters. Without it, the program would look for "words" separated by spaces, considering "details" and "details.)" to be (different) words.)



  [1]: http://pages.cs.wisc.edu/~hasti/cs367-common/readings/Trees/tynAns2.gif
