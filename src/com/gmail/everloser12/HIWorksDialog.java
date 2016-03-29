package com.gmail.everloser12;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * класс описывает JDialog, вызываемый из пункта меню Help/How It Works
 * @author al-ev
 *
 */
public class HIWorksDialog extends JDialog
	{
		public HIWorksDialog(JFrame owner)
			   {
			      super(owner, "How Salary Setter works", true);

			      add(
			            new JLabel(
			                  "<html><h1><i>Salary Setter</i></h1>"
			                  + "<h2>Created by Konstantin Khurs  everloser12@gmail.com</h2>"
			                  + "This program - is the realization of study project, according to the Java for Android"
			                  + "<br />education program at JLTD EC of High-Tech Park Belarus<hr>"
			                  + "To start working with the program you need to select the menu item <b>download</b>"
			                  + "<br />and select the type of file to download and parse."
			                  + "<br />If the download is successful, the data will appear in the textarea field"
			                  + "<br /> after that you could use another options from menu"
			                  + "<br /> .... <hr> This program demonstrates the next Java's features:"
			                  + "<ul><li> XML/JSON files parsing with SAX/JSON Simple/GSON parsers" +
			                  "<li> downloads files with HTTPUrlConnector"
			                  + "<li> exception handling"
			                  + "<li> Swing(GUI widget toolkit for Java)"
			                  + "<li> multithreading with SwingWorker"
			                  + "<li> implementations of inheritance, polymorphism and encapsulation"
			                  + "<li> uses interfaces and abstract classes"
			                  + "<li> etc... </ul></html>"),
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
