package com.sunsigne.tuto.object;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	public static final int SPEED = 32 / 3;
	
	private Player(int x, int y) {
		super(x, y);
	}
	
	////////// EXISTING ////////////
	
	private static Player player = new Player(0, 0);
	
	public static boolean isExisting() {
		return HandlerObject.getInstance().isPlayerExisting();
	}
	
	public static Player get() {
		return player;
	}
	
	public static void reset() {
		player = new Player(0, 0);
	}

	////////// TICK ////////////
	
	@Override
	public void tick() {
	}

	////////// RENDER ////////////
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);
	}

}
