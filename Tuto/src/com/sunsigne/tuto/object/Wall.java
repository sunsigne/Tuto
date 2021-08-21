package com.sunsigne.tuto.object;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {

	public Wall(int x, int y) {
		super(true, false, x, y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);
	}

}
