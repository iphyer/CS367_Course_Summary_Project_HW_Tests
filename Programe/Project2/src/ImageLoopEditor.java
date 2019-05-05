
///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Title: (ImageLoopEditor)
// Files: (ImageLoopEditor.java)
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
// None
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/********************************
 * This class implements a GUI-based editor for an Image Loop editor. A
 * LinkedLoop<Image> named loop is declared. You must complete the code in the
 * methods named pushXXX to implement the individual editor commands.
 ********************************/
public class ImageLoopEditor
{

	protected static LoopADT<Image> loop = new LinkedLoop<Image>();

	///////////////////////////
	// Methods
	///////////////////////////

	// Methods that implement the GUI buttons' actions
	/**
	 * If the image loop is empty, display "no images". Otherwise, find (by
	 * searching forward in the image loop) the first image whose title contains
	 * the given string (which may be quoted).
	 * 
	 * If no image with a title containing string is found, display "not found";
	 * otherwise, make that image the current image and display the current
	 * context.
	 * 
	 * 
	 * @param title
	 *            the title of the image
	 * @return strings as described above to indicate the status of the
	 *         searching results
	 * 
	 */
	static String pushFind( String title )
	{
		// empty loop
		if ( loop.isEmpty() )
		{
			return "no images";
		}
		else // the loop is not empty
		{
			// test whether title is null
			if ( title == null )
			{
				throw new IllegalArgumentException();
			}
			else // the loop is not empty and the title is not null
			{

				// Initialize variables
				// offset between the current image and the found image if we
				// can find the matched title
				int imageOffset = 0;
				// Create iterator for the loop
				Iterator<Image> iter = loop.iterator();

				while ( iter.hasNext() )
				{
					// get next image node of the loop
					Image tmp = iter.next();
					// test whether the image title contains the searched string
					if ( tmp.getTitle()
							.contains( title.replaceAll( "^\"|\"$", "" ) ) )
					{
						return pushJump( Integer.toString( imageOffset ) );
					}
					// increase offset
					imageOffset++;
				}
				// loop over all items in the loop and no match
				return "not found";

			}

		}

	}

	/**
	 * If the image loop is empty, display "no images to save". Otherwise, save
	 * all the images to a file named filename, one image per line starting with
	 * the current image and proceeding forward through the loop.
	 * 
	 * For each image, save the file name, the duration and the title. A null
	 * title (zero characters) is allowed;
	 * 
	 * If filename already exists, display "warning: file already exists, will
	 * be overwritten" before saving the images.
	 * 
	 * If filename cannot be written to, display "unable to save".
	 *
	 * @param filename
	 *            the file name of the text file to save the information of the
	 *            loop
	 * @return strings as described above to indicate the status of the saving
	 * 
	 */
	static String pushSave( String filename )
	{
		// Check whether the loop is empty
		if ( loop.isEmpty() )
		{
			return "no images to save\n";
		}

		// check filename whether it is OK
		if ( !validFilename( filename ) )
		{
			return "not vqlid filename.";
		}

		// the print stream to write output to file
		PrintStream printFileStream = null;
		// create output file
		// open the file
		File outputFile = new File( filename );
		// Check if existing file is going to be overwritten
		if ( outputFile.exists() )
		{
			return "warning: file already exists, will be overwritten\n";
		}

		try
		{
			// create output stream with outputFile
			printFileStream = new PrintStream( outputFile );
			// create iterator of the loop
			Iterator<Image> iter = loop.iterator();

			// loop over all image
			while ( iter.hasNext() )
			{
				// get next image node
				Image tmp = iter.next();
				// output information of tmp image
				String line = tmp.getFile() + " " + tmp.getDuration() + " \""
						+ tmp.getTitle() + "\"";
				// save information to file
				printFileStream.println( line );
			}
		}
		catch ( IOException E ) // Written to filename failed
		{
			return "unable to save\n";
		}
		finally
		{
			// Close the file (by closing the PrintStream).
			// Also flushes any remaining buffered output.
			if ( printFileStream != null )
			{
				printFileStream.close();
			}
		}

		return "";
	}

	/**
	 * load the images from filename in the order they are given and set the
	 * current image to be the first image read from the file.
	 * 
	 * If a file named filename does not exist or cannot be read from, display
	 * <em>"unable to load"</em>
	 * 
	 * If a filename on a line is not in the images folder display <em>"Warning:
	 * filename is not in images folder"</em>
	 * 
	 * @param filename
	 *            the filename of the input image and their display duration
	 * @return strings as described above to indicate the status of loading
	 * 
	 */
	static String pushLoad( String filename )
	{
		// check whether filename is valid
		if ( validFilename( filename ) )
		{
			// create the input file
			File inputFile = new File( filename );
			// create scanner
			Scanner inputFileScanner = null;

			try
			{
				// Create a Scanner from the file.
				// This statement can cause a FileNotFoundException.
				inputFileScanner = new Scanner( inputFile );
				// test whether the file is readable
				// simply test whether the hasNext is true
				if ( !inputFileScanner.hasNext() )
				{
					inputFileScanner.close(); // close file before returning
					return "unable to load";
				}

				// Now we are sure the filename is valid and the file exists and
				// readable
				// Next we need to
				// load the data from the input file and use it to construct
				// image loop

				try
				{
					// process the information in input information file line by
					// line
					while ( inputFileScanner.hasNextLine() )
					{
						// current line
						String currentLine = inputFileScanner.nextLine();
						// split current line to get the each image file field
						// According to homework document, we can assume that
						// there is one image per line,
						// that there are no blank lines, and that the file is
						// not empty,
						// i.e., it has at least one line.
						// Each line contains a filename (a string), a duration
						// (an integer) and a title (possible null)
						// so we split current line into 3 pieces by setting the
						// second parameter of split method 3
						// and the first two substring should be split by
						// whitespace character and the reaming part as
						// the third substring
						String[] currentLineArray = currentLine.split( "\\s+",
								3 );

						// check whether the image filename is valid
						if ( !validFilename( currentLineArray[0] ) )
						{
							inputFileScanner.close(); // close the opened files
							return "Image file name is not valid";
						}

						// check whether the image file exists or not
						if ( existImageFile( currentLineArray[0] ) )
						{
							Image img = new Image( currentLineArray[0],
									currentLineArray[2].replaceAll( "^\"|\"$",
											"" ),
									Integer.parseInt( currentLineArray[1] ) );
							// add image to the loop in the order they are given
							// and the add() method in linedLoop always adds
							// item before current item
							// so we need to return to the first image which
							// makes the next method points to the newly added
							// one

							if ( loop.isEmpty() )
							{
								loop.add( img );
							}
							else
							{

								loop.add( img );
								loop.next();
							}
						}
						else
						{

							System.out
									.println( "Warning: " + currentLineArray[0]
											+ " is not in images folder" );
						}

					}
					// close the opened files
					inputFileScanner.close();

					return "";
				}
				catch ( Exception e )
				{
					return "Error when reading lines from the text file";
				}

			}

			catch ( FileNotFoundException e )
			{
				return "unable to load";// not successfully created
										// inputFileScanner
				// no need to close file
			}
		}
		else
		{
			// Invalid file name
			return "unable to load";
		}

	}

	/**
	 * add new image AFTER current image
	 * 
	 * If the image loop is empty, add a new image with the given filename, a
	 * null title, and a duration of 5 seconds to the loop and make it the
	 * current image.
	 * 
	 * Otherwise, add the new image immediately after the current image and make
	 * the new image the new current image.
	 * 
	 * In either case, display the current context.
	 * 
	 * If filename is not in the images folder display "Warning: filename is not
	 * in images folder"
	 * 
	 * 
	 * @param filename
	 *            the filename of the image that needs to be added
	 * @return strings as described above to indicate the status of loading
	 */
	static String pushAddImage( String filename )
	{
		// check whether the filename is valid
		if ( !validFilename( filename ) )
		{
			return "the filename is not valid";
		}
		// check the image file exists
		if ( !existImageFile( filename ) )
		{
			return "Warning: filename is not in images folder";
		}
		// now the create the image with this filename, null title and 5 seconds
		// duration
		Image tmpImage = new Image( filename, "", 5 );

		// check weather the loop is empty
		if ( loop.isEmpty() )
		{
			loop.add( tmpImage );
		}
		else // the loop is not empty
		{
			loop.next();
			loop.add( tmpImage );
		}

		return displayCurrentContext();
	}

	/**
	 * insert new image BEFORE current image
	 * 
	 * If the image loop is empty, add a new image with the given filename, a
	 * null title, and a duration of 5 seconds to the loop and make it the
	 * current image.
	 * 
	 * Otherwise, insert the new image immediately before the current image and
	 * make new image the new current image.
	 * 
	 * In either case, display the current context.
	 * 
	 * If filename is not in the images folder display "Warning: filename is not
	 * in images folder"
	 * 
	 * @param filename
	 *            the filename of the image that needs to be added
	 * @return strings as described above to indicate the status of loading
	 */
	static String pushInsertImage( String filename )
	{
		// check whether the filename is valid
		if ( !validFilename( filename ) )
		{
			return "the filename is not valid";
		}
		// check the image file exists
		if ( !existImageFile( filename ) )
		{
			return "Warning: filename is not in images folder";
		}
		// now the create the image with this filename, null title and 5 seconds
		// duration
		Image tmpImage = new Image( filename, "", 5 );
		// add image to loop
		loop.add( tmpImage );

		return displayCurrentContext();
	}

	/**
	 * If the image loop is empty, display "no images". Otherwise, jump count
	 * images in the loop (forward if count > 0, backwards if count < 0) and
	 * display the current context.
	 * 
	 * 
	 * @param count
	 *            String of an integer to denote the offset of image nodes
	 * @return strings as described above to indicate the status of loading
	 * @throws NumberFormatException
	 *             exception when convert from string to int
	 */
	static String pushJump( String count )
	{
		// empty loop
		if ( loop.isEmpty() )
		{
			return "no images";
		}
		else // the loop is not empty
		{
			// create int variable to hold value of count
			int intCount = 0;
			// try to convert from string to int
			// may throw NumberFormatException
			try
			{
				intCount = Integer.valueOf( count );
			}
			catch ( NumberFormatException E )
			{
				// if there is any possible failures of conversation throws
				// NumberFormatException
				return "invalid jump count\n";
			}

			// Based the value of value of intCount
			// forward if intCount > 0, backwards if intCount < 0, do nothing if
			// intCount == 0
			// both moving forward and backward are the same times except the
			// direction of movement

			// intCount > 0 , move forward
			if ( intCount > 0 )
			{
				for ( int i = 0; i < intCount; i++ )
				{
					loop.next();
				}
			}
			// intCount < 0 , move backward
			else if ( intCount < 0 )
			{
				for ( int i = 0; i < ( -1 * intCount ); i++ )
				{
					loop.previous();
				}
			}
			// intCount == 0, nothing need to do
			else
			{
			}
			return displayCurrentContext();
		}

	}

	/**
	 * Update duration for current image
	 * 
	 * If the image loop is empty, display "no images". Otherwise, update the
	 * duration for current image to the given time and display the current
	 * context.
	 * 
	 * 
	 * @param time
	 *            String of an integer to denote the duration of display of the
	 *            image
	 * @return strings as described above to indicate the status of loading
	 * @throws NumberFormatException
	 *             exception when convert from string to int EmptyLoopException
	 *             exception when the loop is empty
	 */
	static String pushUpdate( String time )
	{
		// create int variable to hold value of count
		int intTime = 0;
		// try to convert from string to int
		// may throw NumberFormatException
		try
		{
			intTime = Integer.valueOf( time );

			if ( intTime < 0 )
			{
				// duration of displaying time can not be minus
				throw new IllegalArgumentException();
			}
			// set the duration of image
			loop.getCurrent().setDuration( intTime );
		}
		catch ( NumberFormatException E )
		{
			// if there is any possible failures of conversation throws
			// NumberFormatException
			return " String time is invaild \n the time must be an integer.";
		}
		catch ( EmptyLoopException e ) // empty loop
		{
			return "no images\n";
		}
		return displayCurrentContext();
	}

	/**
	 * Edit title of image
	 * 
	 * If the image loop is empty, display "no images". Otherwise, edit the
	 * title for current image to the given title (which may be quoted) and
	 * display the current context.
	 * 
	 * 
	 * @param title
	 *            the new title of current image
	 * @return strings as described above to indicate the status of loading
	 * @throws EmptyLoopException
	 *             exception when the loop is empty
	 */
	static String pushEdit( String title )
	{

		// Update title and remove beginning and ending double quotes in title
		try
		{
			loop.getCurrent().setTitle( title.replaceAll( "^\"|\"$", "" ) );
		}
		catch ( EmptyLoopException e ) // empty loop
		{
			return "no images\n";
		}

		// Return context
		return displayCurrentContext();

	}

	/**
	 * If the image loop is empty, display "no images". Otherwise, display all
	 * of the images in the loop, starting with the current image, one image per
	 * line (going forward through the entire loop). Each line is of the form:
	 * title [duration,filename].
	 * 
	 * @param none
	 * @return none
	 */
	static String pushDisplay()
	{
		// empty loop
		if ( loop.isEmpty() )
		{
			return "no images";
		}

		// result string
		String resultString = "";
		// create iterator for the loop
		Iterator<Image> iter = loop.iterator();

		// loop over all image
		while ( iter.hasNext() )
		{
			// get next image node
			Image tmp = iter.next();
			// output information of tmp image
			resultString += tmp.getTitle() + " [" + tmp.getDuration() + ","
					+ tmp.getFile() + "]\n";
		}
		return resultString;
	}

	/**
	 * Display current image
	 * 
	 * If the image loop is empty, display "no images". Otherwise, display the
	 * current image as a photograph, in a window with the image's title and for
	 * the specified duration.
	 * 
	 * 
	 * @param none
	 * @return none
	 * @throws EmptyLoopException
	 *             exception when the loop is empty
	 */
	static String pushShow()
	{

		// get current image and display it
		try
		{
			loop.getCurrent().displayImage();
			return "";
		}
		// empty loop exception
		catch ( EmptyLoopException empty )
		{
			return "no images\n";
		}

	}

	/**
	 * Display all images in the loop for test
	 * 
	 * If the image loop is empty, display "no images". Otherwise, test the
	 * loop, starting with the current image, by displaying each image as a
	 * photograph in a window with the image's title and for the specified
	 * duration.
	 * 
	 * @param none
	 * @return none
	 */
	static String pushTest()
	{
		// empty loop
		if ( loop.isEmpty() )
		{
			return "no images";
		}
		else
		{
			// create iterator for the loop
			Iterator<Image> iter = loop.iterator();
			// create list for image to use displayImageList method
			List<Image> imgList = new ArrayList<Image>();
			// loop over all image
			while ( iter.hasNext() )
			{
				// get next image node
				Image tmp = iter.next();
				imgList.add( tmp );
			}
			// use displayImageList method
			Image.displayImageList( imgList );
			return "";
		}

	}

	/**
	 * Remove current image
	 * 
	 * If the image loop is empty, display "no images". Otherwise, remove the
	 * current image.
	 * 
	 * If the image loop becomes empty as a result of the removal, display "no
	 * images". Otherwise, make the image after the removed image the current
	 * image and display the current context.
	 * 
	 * 
	 * @param none
	 * @return none
	 * @throws EmptyLoopException
	 *             exception when the loop is empty
	 */
	static String pushRemove()
	{
		// try to remove current image
		try
		{
			loop.removeCurrent();

			if ( loop.isEmpty() )
			{
				// become empty loop after removal
				return "no images\n";
			}
			else
			{
				// not empty after removal
				return displayCurrentContext();
			}
		}
		catch ( EmptyLoopException empty ) // empty loop
		{
			return "no image\n";
		}

	}

	/**
	 * Move current image forward
	 * 
	 * If the image loop is empty, display "no images". Otherwise, go forward to
	 * the next image in the loop and display the current context
	 * 
	 * @param none
	 * @return none
	 */
	static String pushForward()
	{

		// empty loop
		if ( loop.isEmpty() )
		{
			return "no images";
		}
		// move to next image
		loop.next();
		// return current context
		return displayCurrentContext();
	}

	/**
	 * Move current image backward
	 * 
	 * If the image loop is empty, display "no images". Otherwise, go backwards
	 * to the previous image in the loop and display the current contextsx
	 * 
	 * 
	 * @param none
	 * @return none
	 */
	static String pushBack()
	{
		// empty loop
		if ( loop.isEmpty() )
		{
			return "no images";
		}
		// move to previous image
		loop.previous();
		;
		// return current context
		return displayCurrentContext();
	}

	static String pushHelp()
	{
		// You may use this method as implemented here
		String cmds = "";
		cmds += "Available commands:\n" + "\tSave image loop into filename\n"
				+ "\tLoad image loop from filename\n"
				+ "\tAdd Image at filename after current image\n"
				+ "\tInsert Image at filename before current image\n"
				+ "\tFind image matching title\n"
				+ "\tUpdate display time of current image\n"
				+ "\tEdit title of current image\n" + "\tJump count images\n"
				+ "\tDisplay loop\n" + "\tShow current image in a window\n"
				+ "\tTest image loop by showing all images\n"
				+ "\tRemove current image from loop\n"
				+ "\tMove current image forward one step\n"
				+ "\tMove current image back one step\n"
				+ "\tHelp on available commands\n"
				+ "\tQuit and close this GUI\n";
		return cmds;
	}

	static String pushQuit()
	{
		// You may use this method as implemented here
		System.exit( 0 );
		return "";
	}

	///////////////////////////
	// Private Methods
	// (helper methods)
	///////////////////////////

	/**
	 * private method that checks whether the input string of filename is vaild
	 * 
	 * 
	 * For commands that reference a filename, the string must start with at
	 * least one non-whitespace character and must contain only letters (a - z,
	 * A - Z), digits (0 - 9), underscores ( _ ), periods ( . ), slashes (/) and
	 * dashes ( - ).
	 * 
	 * @param filename
	 *            the filename of the input image and their display duration
	 * @return true the filename is a valid filename false the filename is not
	 *         valid
	 * 
	 */

	private static boolean validFilename( String fname )
	{
		// whether start with at least one non-whitespace character
		// using isWhitespace method of Character
		if ( Character.isWhitespace( fname.charAt( 0 ) ) )
		{
			return false;
		}
		else
		{
			// check whether contains only the allowed characters
			if ( fname.matches( "^[a-zA-Z0-9_./-]+$" ) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}

	}

	/**
	 * private method that checks whether the file with certain filename exists
	 * 
	 * @param filename
	 *            the filename of the image
	 * @return true the filename exists false the filename does not exist
	 * 
	 */
	private static boolean existImageFile( String imgname )
	{
		// try to create the file with given filename
		File tmp = new File( "images/" + imgname );
		// System.out.println("images/" + imgname);
		if ( tmp.exists() )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * private method that return the current context of the image loop
	 * 
	 * Displaying the current context means displaying the image (i.e., its
	 * title, filename and duration) immediately before the current image, the
	 * current image, and the image immediately after the current image (one per
	 * line).
	 * 
	 * For example, if the current image is "Lassie [dog2.jpg ,10]", the image
	 * before current is "Rin Tin Tin [dog1.jpg, 20]", and the image after
	 * current is "Bruiser [dog3.jpg, 15]", this private method should display:
	 * Rin Tin Tin [dog1.jpg, 20] --> Lassie [dog2.jpg ,10] <-- Bruiser
	 * [dog3.jpg, 15]
	 * 
	 * Two special cases, if there are fewer than three images in the loop do
	 * the following: If the loop contains only one image, such as "Lassie
	 * [dog2.jpg ,10]", then display:
	 * 
	 * --> Lassie [dog2.jpg ,10] <--
	 * 
	 * If the loop contains only two images, such as " Lassie [dog2.jpg ,10]"
	 * and " Bruiser [dog3.jpg, 15]", then display showing the current image
	 * first:
	 * 
	 * --> Lassie [dog2.jpg ,10] <-- Bruiser [dog3.jpg, 15]
	 *
	 * @param none
	 * @return strings that shows the current context of the image loop
	 * 
	 */
	private static String displayCurrentContext()
	{
		// the size of the loop
		int loopSize = loop.size();
		// the result string should be displayed
		String resultString = "";

		// if image loop is empty
		if ( loopSize == 0 )
		{
			return "no image";
		}

		try
		{
			// only 1 image in the loop
			if ( loopSize == 1 )
			{
				resultString += "-->\t" + loop.getCurrent().getTitle() + " ["
						+ loop.getCurrent().getFile() + ", "
						+ loop.getCurrent().getDuration() + "]" + " <--\n";
			}
			// only 2 image in the loop
			else if ( loopSize == 2 )
			{
				resultString += "-->\t" + loop.getCurrent().getTitle() + " ["
						+ loop.getCurrent().getFile() + ", "
						+ loop.getCurrent().getDuration() + "]" + " <--\n";
				loop.next();
				resultString += "\t" + loop.getCurrent().getTitle() + " ["
						+ loop.getCurrent().getFile() + ", "
						+ loop.getCurrent().getDuration() + "]" + "\n";
				loop.previous(); // need to move back to previously current node
			}
			// the size of the loop >= 3
			else
			{
				// move to previous image information
				loop.previous();
				resultString += "\t" + loop.getCurrent().getTitle() + " ["
						+ loop.getCurrent().getFile() + ", "
						+ loop.getCurrent().getDuration() + "]" + "\n";
				// move to current image node
				loop.next();
				resultString += "-->\t" + loop.getCurrent().getTitle() + " ["
						+ loop.getCurrent().getFile() + ", "
						+ loop.getCurrent().getDuration() + "]" + " <--\n";
				// move to next node of current node
				loop.next();
				resultString += "\t" + loop.getCurrent().getTitle() + " ["
						+ loop.getCurrent().getFile() + ", "
						+ loop.getCurrent().getDuration() + "]" + "\n";
				// move back to previously current node again
				loop.previous();
			}
		}
		catch ( EmptyLoopException e )
		{
			resultString += "no image";
		}
		return resultString;

	}

	/********************************
	 * The following method actually implements our GUI. Major components are
	 * buttons and text fields. The components are defined and placed (in terms
	 * of pixels). You should not change any of this unless you really know what
	 * you are doing. Each button has an "action listener." When you push a
	 * button, the action listener calls a pushXXX method that YOU must define.
	 *********************************/
	public static void runGUI()
	{

		// f is the JFrame that will be our GUI
		JFrame f = new JFrame( "Image Loop" );
		// We define the buttons and text areas that will fill the GUI
		// The locations of each component are set, in terms of pixels
		final JTextField tf1 = new JTextField( "" );
		JTextArea ta = new JTextArea();
		ta.setTabSize( 4 );
		ta.setBounds( 50, 450, 370, 300 );
		JButton b1 = new JButton( "Save" );
		b1.setBounds( 50, 25, 110, 30 );
		tf1.setBounds( 170, 25, 160, 30 );
		tf1.setText( "filename" );
		JButton b2 = new JButton( "Load" );
		b2.setBounds( 50, 60, 110, 30 );
		final JTextField tf2 = new JTextField( "" );
		tf2.setBounds( 170, 60, 160, 30 );
		tf2.setText( "filename" );
		JButton b3 = new JButton( "Add after" );
		b3.setBounds( 50, 95, 110, 30 );
		final JTextField tf3 = new JTextField( "" );
		tf3.setBounds( 170, 95, 150, 30 );
		tf3.setText( "filename of image" );
		JButton b4 = new JButton( "Insert before" );
		b4.setBounds( 50, 130, 110, 30 );
		final JTextField tf4 = new JTextField( "" );
		tf4.setBounds( 170, 130, 150, 30 );
		tf4.setText( "filename of image" );
		JButton b5 = new JButton( "Find" );
		b5.setBounds( 50, 165, 110, 30 );
		final JTextField tf5 = new JTextField( "" );
		tf5.setBounds( 170, 165, 150, 30 );
		tf5.setText( "title" );
		JButton b6 = new JButton( "Update" );
		b6.setBounds( 50, 200, 110, 30 );
		final JTextField tf6 = new JTextField( "" );
		tf6.setBounds( 170, 200, 150, 30 );
		tf6.setText( "time" );
		JButton b7 = new JButton( "Edit" );
		b7.setBounds( 50, 235, 110, 30 );
		final JTextField tf7 = new JTextField( "" );
		tf7.setBounds( 170, 235, 150, 30 );
		tf7.setText( "title" );
		JButton b8 = new JButton( "Jump" );
		b8.setBounds( 50, 270, 110, 30 );
		final JTextField tf8 = new JTextField( "" );
		tf8.setBounds( 170, 270, 150, 30 );
		tf8.setText( "count" );
		JButton b9 = new JButton( "Display loop" );
		b9.setBounds( 50, 305, 110, 30 );
		JButton b10 = new JButton( "Show image" );
		b10.setBounds( 170, 305, 110, 30 );
		JButton b11 = new JButton( "Test loop" );
		b11.setBounds( 50, 340, 110, 30 );
		JButton b12 = new JButton( "Remove image" );
		b12.setBounds( 170, 340, 120, 30 );
		JButton b13 = new JButton( "Move forward" );
		b13.setBounds( 50, 375, 110, 30 );
		JButton b14 = new JButton( "Move backward" );
		b14.setBounds( 170, 375, 110, 30 );
		JButton b15 = new JButton( "Quit" );
		b15.setBounds( 50, 410, 110, 30 );
		JButton b16 = new JButton( "Help" );
		b16.setBounds( 170, 410, 110, 30 );

		// The effect of pushing a GUI button is defined in an ActionListener
		// The actionPerformed method is executed when the associated button is
		// pushed

		b1.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushSave( tf1.getText() ) );
			}
		} );

		b2.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushLoad( tf2.getText() ) );
			}
		} );

		b3.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushAddImage( tf3.getText() ) );
			}
		} );

		b4.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushInsertImage( tf4.getText() ) );
			}
		} );

		b5.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushFind( tf5.getText() ) );
			}
		} );

		b6.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushUpdate( tf6.getText() ) );
			}
		} );

		b7.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushEdit( tf7.getText() ) );
			}
		} );

		b8.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushJump( tf8.getText() ) );
			}
		} );

		b9.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushDisplay() );
			}
		} );

		b10.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{

				ta.setText( pushShow() );
			}
		} );

		b11.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushTest() );
			}
		} );

		b12.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushRemove() );
			}
		} );

		b13.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushForward() );
			}
		} );

		b14.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushBack() );
			}
		} );

		b15.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushQuit() );
			}
		} );

		b16.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				ta.setText( pushHelp() );
			}
		} );

		// Buttons and text frames are added to the JFrame to build the GUI

		f.add( b1 );
		f.add( tf1 );
		f.add( ta );
		f.add( b1 );
		f.add( tf1 );
		f.add( ta );
		f.add( b1 );
		f.add( tf1 );
		f.add( ta );
		f.add( b2 );
		f.add( tf2 );
		f.add( b3 );
		f.add( tf3 );
		f.add( b4 );
		f.add( tf4 );
		f.add( b5 );
		f.add( tf5 );
		f.add( b6 );
		f.add( tf6 );
		f.add( b7 );
		f.add( tf7 );
		f.add( b8 );
		f.add( tf8 );
		f.add( b9 );// f.add(tf9);
		f.add( b10 );// f.add(tf10);
		f.add( b11 );// f.add(tf10);
		f.add( b12 );// f.add(tf10);
		f.add( b13 );// f.add(tf10);
		f.add( b14 );// f.add(tf10);
		f.add( b15 );// f.add(tf10);
		f.add( b16 );// f.add(tf10);
		f.setBounds( 50, 50, 500, 800 );
		f.setLayout( null );
		f.setVisible( true );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// The GUI is now up and running!

	}

	public static void main( String[] args )
	{
		runGUI();
	}
}
