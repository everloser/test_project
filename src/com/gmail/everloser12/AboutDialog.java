package com.gmail.everloser12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * класс описывает JDialog, вызываемый из пункта меню Help/About
 * @author al-ev
 *
 */
public class AboutDialog extends JDialog

{

	public AboutDialog(JFrame owner)
	   {
	      super(owner, "About Salary Setter", true);

	      JLabel jl  = new JLabel(
	                  "<html><h1><i>Created by Konstantin Khurs</i></h1>"
	                  + "<h2>everloser12@gmail.com<hr></h2>"
	                  + "This program - is the realization of study project<br />"
	                  + "according to the Java for Android education program<br />"
	                  + "at JLTD \"EC Parka vysokih tehnologij\"<hr>You could find the Code of this program at </html>");
	                  
	      
	      JLabel jt = new JLabel("http://github.com/everloser/test_project");
	      jt.setForeground(Color.BLUE);
	      jt.setCursor(new Cursor(Cursor.HAND_CURSOR));
	      jt.addMouseListener(new MouseListener()
			{
				@Override
				public void mouseReleased(MouseEvent e)
					{}
					
				@Override
				public void mousePressed(MouseEvent e)
					{}
					
				@Override
				public void mouseExited(MouseEvent e)
					{}
					
				@Override
				public void mouseEntered(MouseEvent e)
					{}
					
				@Override
				public void mouseClicked(MouseEvent e)
					{
						try {
		                    Desktop.getDesktop().browse(new URI("http://github.com/everloser/test_project"));
		                } catch (Exception ex) {
		                	JOptionPane.showMessageDialog(null,
									"Can not open the link " + ex.getMessage());
		                }
						
					}
			});
	      
	      
	      JPanel pc = new JPanel();
	      pc.setLayout(new BorderLayout());
	      pc.add(jl,BorderLayout.CENTER);
	      pc.add(jt,BorderLayout.SOUTH);
	      add(pc, BorderLayout.CENTER);

	      JButton ok = new JButton("Good!");
	   //   ok.setToolTipText("Copy the link to the clipboard");
	      ok.addActionListener(new ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
//	            	StringSelection ss = new StringSelection("http://github.com/everloser/test_project");
//				    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
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
