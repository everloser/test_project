package com.gmail.everloser12;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialog extends JDialog

{

	public AboutDialog(JFrame owner)
	   {
	      super(owner, "About Salary Setter", true);

	      add(
	            new JLabel(
	                  "<html><h1><i>Created by Konstantin Khurs</i></h1>"
	                  + "<h2>everloser12@gmail.com<hr></h2>"
	                  + "This program is the realization of study project<br />"
	                  + "according to the Java for Android education program<br />"
	                  + "at JLTD \"EC Parka vysokih tehnologij\"<hr>You could find the Code of this program at <br />"
	                  + "<a href=\"xxx.imag/xxx.java\">xxx.imag/xxx.java</a></html>"),
	            BorderLayout.CENTER);

	      

	      JButton ok = new JButton("Good!");
	      ok.addActionListener(new ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	               setVisible(false);
	            }
	         });

	      

	      JPanel panel = new JPanel();
	      panel.add(ok);
	      add(panel, BorderLayout.SOUTH);

	      pack();
	      //setSize(400,200);
	   }
	
}
