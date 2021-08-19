package com.sunsigne.tuto.object.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.tuto.object.GameObject;

public class GUIHealth extends GameObject {

	public GUIHealth() {
		super(false, false, 0, 0);
		// TODO Auto-generated constructor stub
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);

		Font font = new Font("arial", 1, 50);
		String text = "hp";
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(text, x + 20, y + 60);
	}

}
