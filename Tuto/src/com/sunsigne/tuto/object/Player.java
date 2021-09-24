package com.sunsigne.tuto.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.tuto.object.collision.CollisionDetector;
import com.sunsigne.tuto.object.collision.ICollisionDetection;
import com.sunsigne.tuto.ressources.images.ImageBank;

public class Player extends GameObject implements ICollisionDetection {

	public static final int SPEED = 32 / 3;
	
	private Player(int x, int y) {
		super(true, false, x, y);
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
	public ImageBank getImageBank(int... index) {
		return null;
	}
		
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);
	}

	////////// COLLISION ////////////
	
	private CollisionDetector collisionDetector = new CollisionDetector(this);
	
	@Override
	public CollisionDetector getCollisionDetector() {
		return collisionDetector;
	}

}
