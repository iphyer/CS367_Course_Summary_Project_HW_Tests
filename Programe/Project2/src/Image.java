/****************************************************
  The Image class defines an Image as a photo, title, and duration.
  The most important methods are:
   displayImage()  --This displays (in a frame) a photo for the defined duration
   displayImageList(List<Image>) --Displays, in sequence a list of Images

  You need not change anything in this file!
  Last updated on May 23, 2017, by Charles Fischer
*****************************************************/

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.lang.*;
import java.util.*;

// A frame is the window we'll use to display a picture;
//  duration tells us how log to keep the window visible.

class FramePair{
  JFrame frame;
  int    duration;
  FramePair(JFrame f, int d){
    frame = f;
    duration = d;  }
}

// Swing (the GUI package being used) doesn't fully update frames until
// the code activated by an ActionListener completes.
// Therefore displayImage forks a new thread (via MyRunnable.run())
// that activates the frames used to display images.
// A call to displayImage (or displayImageList) returns immediately,
// completing the code  activated by pushing a button.

class MyRunnable implements Runnable {

     static List<FramePair> frameList = new ArrayList<FramePair>();
     static Random rand = new Random();

     // Choose a random location for frame in range (400,200) to (600,400)
     // (so frames don't always appear in the same place!)

     static void chooseLoc( JFrame f){
         int x = 400+rand.nextInt(201);
         int y = 200+rand.nextInt(201);
         f.setLocation(x,y);
     }

     void displayFrame(JFrame frame,int duration){
        frame.setAlwaysOnTop (true);
        frame.setVisible(true);
        chooseLoc(frame);
        try {
            Thread.sleep(duration); // The thread sleeps here
        } catch (InterruptedException ex) {};
        // The sleep duration is now over; we make the frame disappear!
        frame.setVisible(false);
     }

     // When a new Thread is started in MyRunable,
     //  method run() is automatically called.
     // It displays each frame in the frameList

    public void run() {
      for (FramePair fp: frameList) {
        displayFrame(fp.frame,fp.duration);
      }
    }
}

/**
 * An <tt>Image</tt> object contains the name of the file containing the image,
 * a title (possibly null),
 * and a positive integer indicating the length of time (duration) the image
 * should be displayed in seconds.
 *
 * DO NOT modify anything in this file</b>
 * (except possibly displayPicture() if you know
 * what you are doing)
 */

public class Image {
  private String fileName;  // name of file containing the image
  private String title;     // title (or caption) of the image
  private int duration;     // duration in seconds

  /**
   * Creates a new <tt>Image</tt> object with specified file name.
   * The durations is initially 5; title is null string
   * @param fileName the name of the file containing the image.
   */
  public Image(String fileName) {
    this.fileName = fileName;
    this.duration = 5;
    this.title = "";
  }

  /**
   * Creates a new <tt>Image</tt> object with specified file name,
   * durations and title.
   * @param fileName the name of the file containing the image.
   * @param title the title (or caption) of  the image.
   * @param duration the duration of the image's display.
   */

  public Image(String fileName, String title, int duration) {
    this.fileName = fileName;
    this.duration = duration;
    this.title = title;
  }

  /**
   * Returns the file name associated with this image.
   * @return the file associated with this image
   */
  public String getFile() {
    return fileName;
  }

  /**
   * Returns the duration for this image (in seconds).
   * @return the duration for this image (in seconds)
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Returns the title of this image.
   * @return the title of this image.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the duration for this image to the value supplied.
   * @param duration the new duration (in miliseconds) for this photo
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Sets the title of this image.
   * @param title the new title for this photo
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the string representation of this image in the form:
   * <p><tt><i>title</i> [<i>file_name</i> ,<i>duration</i>]</tt></p>
   * If title is null we use:
   * <p><tt><i>file_name</i> [<i>duration</i>]</tt></p>
   *
   * @return The string representation of this photo.
   */
  public String toString() {
    if (title.length() == 0)
      return "[" + fileName + ", "+duration + "]";
    else
      return title + " [" + fileName + ", "+duration + "]";
  }


  /***** Create a JFrame  that will display this Image ***/
  JFrame createFrame(){
    String fileName = "images/"+getFile();
    JFrame frame = new JFrame(getTitle());
    ImageIcon icon = new ImageIcon(fileName);
    JLabel label = new JLabel(icon);
    frame.add(label);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocation(400,200);
    frame.setVisible(false);
    return frame;
  }

  // Displays, in order, a list of Images
  public static void displayImageList(List<Image> imageList) {

    // We build a list of frames and their designated duration
    List<FramePair> frameList = new ArrayList<FramePair>();

    for (Image image: imageList) {
      frameList.add(
			  new FramePair(image.createFrame(),image.getDuration()*1000));
    }

    // Copy list of pairs over to MyRunnable, then activate a thread to
    // display the frames

    MyRunnable.frameList = frameList;

    // We start a new thread to actually display the list of frames
    // This method returns immediately while the display code is
    //  running independently
    (new Thread(new MyRunnable())).start();
  }

  // Display the contents of this Image object.
  // It is a special case of the above method, which displays a list of
  // Image objects

  public void displayImage() {
    List<FramePair> frameList = new ArrayList<FramePair>();
    frameList.add(new FramePair(createFrame(),getDuration()*1000));
    MyRunnable.frameList = frameList;

    // We start a new thread to actually display the frame
    // This method returns immediately while the display code is
    //  running independently
    (new Thread(new MyRunnable())).start();
  }

  // You can run this class to see if the displayImage() method is
  //  working.
  // Be sure a folder named images exists & holds an image file named pets1.jpg

  public static void main(String [] args) throws InterruptedException {
    Image test = new Image("pets1.jpg","test",10);
    test.displayImage();

    // We sleep here because the display task is running as an independent
    // thread. If main returns too soon, the display process will be
    // killed too!
    try {
      Thread.sleep(10100);
    } catch (InterruptedException ex) {};
    System.exit(0);
  }
}
