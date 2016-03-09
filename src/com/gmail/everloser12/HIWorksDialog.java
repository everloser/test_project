package com.gmail.everloser12;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HIWorksDialog extends JDialog
	{
		public HIWorksDialog(JFrame owner)
			   {
			      super(owner, "How Salary Setter works", true);

			      add(
			            new JLabel(
			                  "<html><h1><i>Salary Setter</i></h1>"
			                  + "<h2>Created by Konstantin Khurs  everloser12@gmail.com</h2>"
			                  + "This program is the realization of study project, according to the Java for Android"
			                  + "<br />education program at JLTD \"EC Parka vysokih tehnologij\"<hr>"
			                  + "To start working with the program you need to select the menu item <b>download</b>...."
			                  + "<br />..........<br />...<hr> This program demonstrates the next Java's features:"
			                  + "<ul><li> XML/JSON files parsing" +
			                  "<li> implementations of .... <li> lalala <li> ... </ul>"),
			            BorderLayout.CENTER);

			      

			      JButton ok = new JButton("Done!");
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
