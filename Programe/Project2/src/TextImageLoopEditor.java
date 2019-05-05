
/**************************************
 * This class is a subclass of ImageLoopEditor. It implements a text-based image
 * loop editor. Commands can be read interactively from standard input. If a
 * file is named on the command line, editing commands are read from that file.
 * 
 * This editor uses the pushXXX methods defined in ImageLoopEditor.
 * 
 * There is no need to change this class (but pushXXX methods must be defined
 * for this editor to work properly).
 * 
 * Last updated on May 23, 2017 by Charles Fischer
 **************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class TextImageLoopEditor extends ImageLoopEditor
{

	// A text-based testing interface. Testing commands are read from a file

	public static void textInterface( String[] args )
	{
		Scanner in = null;
		if ( args.length > 1 )
		{
			System.out.println( "invalid command-line arguments" );
			System.exit( 1 );
		}
		boolean useFile = ( args.length == 1 );

		if ( useFile )
		{
			File inFile = new File( args[0] );
			if ( !inFile.exists() || !inFile.canRead() )
			{
				System.out.println( "problem with input file" );
				System.exit( 1 );
			}
			try
			{
				in = new Scanner( inFile );
			}
			catch ( FileNotFoundException e )
			{
				System.out.println( "problem with input file" );
				System.exit( 1 );
			}
		}
		else
		{
			in = new Scanner( System.in );
		}
		boolean done = false;
		String[] validCommands = { "save", "load", "add", "insert", "find",
				"update", "edit", "jump", "display", "show", "test", "remove",
				"forward", "backward", "quit", "help" };
		// Map testing commands to their one character code

		HashMap<String, Character> commandSelect = new HashMap<String, Character>();
		commandSelect.put( "save", 's' );
		commandSelect.put( "load", 'l' );
		commandSelect.put( "add", 'a' );
		commandSelect.put( "insert", 'i' );
		commandSelect.put( "find", 'c' );
		commandSelect.put( "update", 'u' );
		commandSelect.put( "edit", 'e' );
		commandSelect.put( "jump", 'j' );
		commandSelect.put( "display", 'd' );
		commandSelect.put( "show", 'p' );
		commandSelect.put( "test", 't' );
		commandSelect.put( "remove", 'r' );
		commandSelect.put( "forward", 'f' );
		commandSelect.put( "backward", 'b' );
		commandSelect.put( "quit", 'x' );
		commandSelect.put( "help", '?' );

		while ( !done )
		{
			System.out.print( "Enter command: " );
			String input = in.nextLine();
			if ( useFile )
				System.out.println( input );

			String[] tokens = input.split( "[ ]+", 2 );
			String command = tokens[0];
			String parameter = "";
			if ( tokens.length > 1 )
				parameter = tokens[1];

			// only do something if the user enters at least one character
			if ( command.length() > 0 )
			{
				command = command.toLowerCase();
				char choice; // One character encoding command choice

				// Get a list of possible commands selected by command
				// (maybe a prefix)
				List<String> possibleCommands = selectByPrefix( tokens[0],
						validCommands );
				if ( possibleCommands.size() != 1 )
				{
					choice = '!'; // illegal command
				}
				else
				{
					choice = commandSelect.get( possibleCommands.get( 0 ) );
				}

				switch (choice) {
				case '?':
					System.out.print( pushHelpTextVersion() );
					break;

				case 's': // save
					System.out.print( pushSave( parameter ) );
					break;

				case 'l': // load
					System.out.print( pushLoad( parameter ) );
					break;

				case 'd': // display
					System.out.print( pushDisplay() );
					break;

				case 't': // display
					System.out.print( pushTest() );
					break;

				case 'f':
					System.out.print( pushForward() );
					break;

				case 'b':
					System.out.print( pushBack() );
					break;

				case 'j':
					System.out.print( pushJump( parameter ) );
					break;

				case 'r':
					System.out.print( pushRemove() );
					break;

				case 'a':
					System.out.print( pushAddImage( parameter ) );
					break;

				case 'i':
					System.out.print( pushInsertImage( parameter ) );
					break;

				case 'c':
					System.out.print( pushFind( parameter ) );
					break;

				case 'u':
					System.out.print( pushUpdate( parameter ) );
					break;

				case 'e':
					System.out.print( pushEdit( parameter ) );
					break;

				case 'p':
					System.out.print( pushShow() );
					break;

				case 'x':
					done = true;
					System.out.print( pushQuit() );
					break;

				default: // ignore any unknown commands
					System.out.println( "invalid command" );
					break;

				} // end switch
			} // end if
			else
			{
				System.out.println( "invalid command" );
			}
		} // end while
		System.exit( 1 );
	}

	static String pushHelpTextVersion()
	{
		String cmds = "";
		cmds += "Available commands\n"
				+ "\tsave filename\t\t Save image loop into filename\n"
				+ "\tload filename\t\t Load image loop from filename\n"
				+ "\tadd filename\t\t Add image at filename after current image\n"
				+ "\tinsert filename\t\t Insert image at filename before current image\n"
				+ "\tcontains title\t\t Find image matching title\n"
				+ "\tupdate time\t\t Update display time of current image\n"
				+ "\tedit title\t\t Edit title of current image\n"
				+ "\tjump count\t\t Jump count images (forward or back)\n"
				+ "\tdisplay\t\t\t Display loop (in text form)\n"
				+ "\tpicture\t\t\t Show current image in a window\n"
				+ "\ttest\t\t\t Test image loop by showing all images\n"
				+ "\tremove\t\t\t Remove current image from loop\n"
				+ "\tforward\t\t\t Move current image forward one step\n"
				+ "\tbackward\t\t Move current image back one step\n"
				+ "\thelp\t\t\t Help on available commands\n"
				+ "\tquit\t\t\t Treminate execution\n";
		return cmds;
	}

	// Return a list of commands that are prefixed by prefix
	static List<String> selectByPrefix( String prefix, String[] commands )
	{
		List<String> results = new ArrayList<String>();

		for ( String command : commands )
		{
			if ( prefix.length() <= command.length() )
			{
				if ( prefix.equals( command.substring( 0, prefix.length() ) ) )
				{
					results.add( command );
				}
			}
		}

		return results;
	}

	public static void main( String[] args )
	{
		textInterface( args );
	}
}
