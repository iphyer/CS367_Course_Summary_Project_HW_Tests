import java.awt.event.*;  
import javax.swing.*;    
public class GUItester extends InteractiveDBTester {  

 /********************************************

  The following commands implement a GUI panel using
  the swing tool kit.

  Individual buttons & text fields are defined and placed w/i
  the overall frame.

  The ActionListeners define the code to be executed when a button
  is pressed (clicked) by a user.
  

  *******************************************/
 public static void activateGUIinterface() {
    JFrame f=new JFrame("Employee DB Tester");  
    final JTextField tf1=new JTextField("");  
    JTextArea ta = new JTextArea();
    ta.setBounds(50,350,400,300);
    JButton b1=new JButton("Find");  
    b1.setBounds(50,25,110,30);  
    tf1.setBounds(170,25, 160,30);  
    tf1.setText("employee");
    JButton b2=new JButton("Discontinue");  
    b2.setBounds(50,60,110,30);  
    final JTextField tf2=new JTextField("");  
    tf2.setBounds(170,60, 160,30);  
    tf2.setText("destnation");
    JButton b3=new JButton("Search");  
    b3.setBounds(50,95,110,30);  
    final JTextField tf3=new JTextField("");  
    tf3.setBounds(170,95, 150,30);  
    tf3.setText("destination");
    JButton b4=new JButton("Remove");  
    b4.setBounds(50,130,110,30);  
    final JTextField tf4=new JTextField("");  
    tf4.setBounds(170,130, 150,30);  
    tf4.setText("employee");
    JButton b5=new JButton("Information");  
    b5.setBounds(50,165,110,30);  
    final JTextField tf5=new JTextField("");  
    tf5.setBounds(170,165, 150,30);  
    tf5.setText("Data5 here");
    JButton b6=new JButton("Text interface");  
    b6.setBounds(50,305,130,30);  
    final JTextField tf6=new JTextField("");  
    tf6.setBounds(170,200, 150,30);  
    tf6.setText("Data6 here");
    JButton b7=new JButton("Help");  
    b7.setBounds(50,235,110,30);  
    final JTextField tf7=new JTextField("");  
    tf7.setBounds(170,235, 150,30);  
    tf7.setText("Data7 here");
    JButton b8=new JButton("Quit");  
    b8.setBounds(50,270,110,30);  
    JButton b9=new JButton("List DB");  
    b9.setBounds(50,200,110,30);  
    final JTextField tf8=new JTextField("");  
    tf8.setBounds(170,270, 150,30);  
    tf8.setText("Data8 here");

    b1.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
            ta.setText(pushFind(tf1.getText()));  
        }  
    });  

    b2.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
            ta.setText(pushDiscontinue(tf2.getText()));  
        }  
    });  

    b3.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
            ta.setText(pushSearch(tf3.getText()));  
        }  
    });  

    b4.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
            ta.setText(pushRemove(tf4.getText()));  
        }  
    });  

    b5.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
             ta.setText(pushInformation());  
        }  
    });  

    b6.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
     // Kill GUI control panel & activae text-based interface 
	f.setVisible(false); //you can't see now!
	f.dispose(); //Destroy the JFrame object
	Texttester.activateTextTester();
       }
    });  

    b7.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
            ta.setText(pushHelp());  
        }  
    });  

    b8.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
         ta.setText(pushQuit());  
        }  
    });  


    b9.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
            ta.setText(pushList());  
        }  
    });  

    f.add(b1);f.add(tf1); f.add(ta);
    f.add(b2);f.add(tf2);
    f.add(b3);f.add(tf3);
    f.add(b4);f.add(tf4);
    f.add(b5);//f.add(tf5);
    f.add(b9);//f.add(tf9);
    f.add(b7);//f.add(tf7);
    f.add(b8);//f.add(tf8);
    f.add(b6);//f.add(tf6);
    f.setBounds(50,50,500,700);  
    f.setLayout(null);  
    f.setVisible(true);   
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
 }

public static void main(String[] args) {  

    GUIactive = true;
    populateDB(args);

// Now build & activate the GUI testing interface
    activateGUIinterface();

}
}  
