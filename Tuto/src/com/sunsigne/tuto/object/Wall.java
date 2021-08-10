package com.sunsigne.tuto.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Wall extends GameObject {

	public Wall(int x, int y) {
		super(x, y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.draw(getBounds());
	}

}
