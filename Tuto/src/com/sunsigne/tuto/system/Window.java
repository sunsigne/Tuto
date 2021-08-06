package com.sunsigne.tuto.system;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.sunsigne.tuto.system.main.Tuto;

public class Window {

	public static final String NAME = "Tuto";
	
	public static final int WIDHT = 1920;
	public static final int HEIGHT = 1080;
	
	protected Window(Tuto tuto) {
		
		tuto.setMinimumSize(new Dimension(WIDHT, HEIGHT));
		tuto.setMaximumSize(new Dimension(WIDHT, HEIGHT));
		tuto.setPreferredSize(new Dimension(WIDHT, HEIGHT));
		
		JFrame frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.add(tuto, BorderLayout.CENTER);
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.pack();
	}
	
}
