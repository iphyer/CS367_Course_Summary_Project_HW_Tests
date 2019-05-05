import java.io.*;
import java.util.Random;
/**
 * This program tests some of the functionality of the <tt>HashTable</tt> class.  
 * It does not completely test the <tt>HashTable</tt> class.  You should make 
 * sure that you do completely test your <tt>HashTable</tt> class, either by 
 * modifying this file or by writing a different driver.
 **/
public class TestHash {

    /**
     * Main method to run the <tt>HashTable</tt> class.
     *
     * @param args needs to have 6 values in the array:
     * @param [0] is the number of items that will be hashed into the table.
     * @param [1] the random number seed (integer)to use in generating values
     * @param [2] maximum load factor to give the the <tt>HashTable</tt> class.  
     *            Note it is a fractional amount (e.g., 0.45), not the percent.
     * @param [3] the initial size of the hashtable to give to 
     *            <tt>HashTable</tt> class.
     * @param [4] maximum chain length to give the <tt>HashTable</tt> class.  To
     *            indicate no maximum chain length, the value 0 will be given.
     * @param [5] output file name used when calling the <tt>dump()</tt> or 
     *            <tt>displayStats()</tt> methods.
     */
    public static void main(String [] args) {
        
        // PrintStream for dump() and displayStats() method.  
        // Set to null since Java complains about an uninitialized value.
        PrintStream myOut = null;
        PrintStream sysOut = new PrintStream(System.out);

        try {
            if (args.length != 6) {
                System.err.println("Expected 6 but got " + args.length);
                System.err.println("Arguments expected:");
                System.err.println("  # items to hash");
                System.err.println("  random # seed");
                System.err.println("  max load factor (e.g., 0.75)");
                System.err.println("  initial size of hash table");
                System.err.println("  max chain length (0 = no max length)");
                System.err.println("  file for output");
                System.exit(1);
            }

            int numHash = Integer.parseInt(args[0]);
            int seed = Integer.parseInt(args[1]);
            double loadFactor = Double.parseDouble(args[2]);
            int initSize = Integer.parseInt(args[3]);
            int maxChainLength = Integer.parseInt(args[4]);
            String outFileName = args[5];

            // Open file for output
            try {
                File outFile = new File(outFileName);
                myOut = new PrintStream(outFile);
            } catch (IOException ex) {
                System.err.println("Error opening file " + outFileName +
                                   " failed so stopping.  The error was:");
                System.err.println(ex);
                System.exit(99);
            }
            System.out.println("See " + outFileName + " file for dump output");

            myOut.println("Parameters used:");
            myOut.println("  # items to hash: " + numHash);
            myOut.println("  random # seed: " + seed);
            myOut.println("  max load factor: " + loadFactor);
            myOut.println("  initial size of hash table: " + initSize);
            myOut.println("  max chain length: " +
                          ((maxChainLength == 0) ? "none" : maxChainLength));
            myOut.println("  output file name: " + outFileName);

            System.out.println("Parameters used:");
            System.out.println("  # items to hash: " + numHash);
            System.out.println("  random # seed: " + seed);
            System.out.println("  max load factor: " + loadFactor);
            System.out.println("  initial size of hash table: " + initSize);
            System.out.println("  max chain length: " +
                            ((maxChainLength == 0) ? "none" : maxChainLength));
            System.out.println("  output file name: " + outFileName);

            // Do inserts into hashtable.
            HashTable<Integer> table;
            if (maxChainLength == 0)
                table = new HashTable<Integer>(initSize, loadFactor);
            else
                table = 
                  new HashTable<Integer>(initSize, loadFactor, maxChainLength);
            
            // It is important to give the seed so you can reproduce results.
            Random randGen = new Random(seed);
            for (int k = 0; k < numHash; k++) {
            		//System.out.println("k = " + k);
                //table.insert(new Integer(randGen.nextInt()));
            		table.insert(new Integer(3*k)); 
            	}

            table.displayStats(myOut); 
            table.dump(myOut);

            table.displayStats(sysOut); 
            table.dump(sysOut);    // comment this out if you don't want to see
                                // the hash table contents on the console
            sysOut.flush();     // forces it to print everything it has buffered

        } catch(Throwable ex) {
            System.out.println("TestHash had unexpected and uncaught exception"
                               + " in main");
            ex.printStackTrace();

        } finally {
            // If you don't always close the PrintStream the file may 
            // appear empty.
            myOut.close();
            System.out.println("TestHash done");
            sysOut.close();
        }
    }
}

