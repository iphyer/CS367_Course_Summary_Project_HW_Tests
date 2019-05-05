import java.awt.event.*;  
import java.io.*;
import java.util.*;
import javax.swing.*;    
public class Texttester extends InteractiveDBTester {  

     static Scanner stdin;  // for reading console input
     static boolean stdinOpen = false;

    /**
     * Prints the list of command options along with a short description of
     * one.  This method should not be modified.
     */
    private static void printOptions() {
        System.out.println("discontinue/d <destination> - discontinue the given <destination>");
        System.out.println("find/f <Employee> - find the given <Employee>");
        System.out.println("gui/g Switch to GUI testing interface");
        System.out.println("help/hh - display this help menu");
        System.out.println("information/ii - display information about this Employee database");
        System.out.println("list/l - list contents of Employee database");
        System.out.println("search/ss <destination> - search for the given <destination>");
        System.out.println("quit/q - quit");
        System.out.println("remove/rr <Employee> - remove the given <Employee>");
    }


    public static void activateTextTester() {
	   GUIactive = false;

           List<String> validCommands = Arrays.asList("discontinue", "find", "gui", "help",
						"information", "list", "search", "quit", "remove");

           List<String> validAbbreviatedCommands = Arrays.asList("d", "f", "g", "h",
						"i", "l", "s", "q", "r");

           if (! stdinOpen) {
           	stdin = new Scanner(System.in);  // for reading console input
		stdinOpen = true;
	   }
           //printOptions();
           boolean done = false;
           boolean found, valid;

           while (true) { // quit command will force termination of loop
            System.out.print("Enter command: ");
            String command = "";
	    String remainder = "";
            do {
           	String input = stdin.nextLine();
            	System.out.println(input); // echo input (for testing purposes)
            	input = input.toLowerCase();  // convert input to lower case
		String[] tokens = input.split("\\s+");
		if (tokens.length > 0)
			command = tokens[0];
		if (tokens.length > 1)
			remainder = tokens[1].trim();
		//System.out.println("Command: "+command+ " Remainder: "+remainder);
		valid = tokens.length >= 1 &&
			(validCommands.contains(command) ||
			 validAbbreviatedCommands.contains(command));
		if (! valid)
			System.out.println("Invalid command will be ignored");
            } while (! valid);

            char cmd = command.charAt(0);  // Extract command  character

            switch (cmd) {
                
                case 'd':
                    System.out.println(pushDiscontinue(remainder));

                    break;

                case 'f':
                    System.out.println(pushFind(remainder));

                    break;

                case 'g':
                    GUItester.activateGUIinterface();
		    return;

                    //break;

                case 'h': 
                    System.out.println(pushHelp());
                    //printOptions();
                    break;

                case 'i':
                    System.out.println(pushInformation());
                    //computeInfo();
                    break;
                    
                case 's':
                    System.out.println(pushSearch(remainder));

                    break;

                case 'q':
                    System.out.println(pushQuit());

                    break;

                case 'r':
                    System.out.println(pushRemove(remainder));

                    break;

                case 'l':
                    System.out.println(pushList());

                    break;

                default:  // abort on  unknown commands
                    assert false : "Illegal command" ;

                    break;
              }
            }
        
        //stdin.close();
 }
public static void main(String[] args) {  

    populateDB(args);

    activateTextTester();
 }  
}  
