package com.sunsigne.tuto.object.livings;

import com.sunsigne.tuto.object.HandlerObject;
import com.sunsigne.tuto.object.collision.CollisionDetector;
import com.sunsigne.tuto.object.collision.ICollisionDetection;

public class Player extends LivingObject implements ICollisionDetection {

	public static final int SPEED = 10;
		
	private Player(String name, int x, int y) {
		super(name, x, y);
	}	
	
	private Player(int x, int y) {
		this("rebecca", x, y);
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

	
	
	////////// COLLISION ////////////
	
	private CollisionDetector collisionDetector = new CollisionDetector(this);
	
	@Override
	public CollisionDetector getCollisionDetector() {
		return collisionDetector;
	}


}
