package com.sunsigne.tuto.system.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.sunsigne.tuto.object.gui.GUIHealth;

public class GameMouseInput extends MouseAdapter {
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

    @Override
    public void mouseReleased(MouseEvent e) {
    	
    }
    
    private boolean mouseOver(MouseEvent e, int x, int y, int width, int height) {
		int mx = e.getX();
		int my = e.getY();
		
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	private boolean mouseOver(MouseEvent e, int[] rect) {
		if (rect.length == 4)
			return mouseOver(e, rect[0], rect[1], rect[2], rect[3]);
		else
			System.err.println("the method mouseOver(e, rect) is not used correcly");
		return false;
	}
}
