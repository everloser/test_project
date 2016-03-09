package com.gmail.everloser12;

import java.awt.*;
import java.net.*;
import javax.swing.*;

public class MainTest
{

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new UIFrame();
              frame.setTitle("SalarySetter");
              URL aboutURL = getClass().getResource("/calc-icons.png");
              Image img = new ImageIcon(aboutURL).getImage();
              frame.setIconImage(img);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });	


	}

}
