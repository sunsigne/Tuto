package com.sunsigne.tuto.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.tuto.object.collision.ICollisionReaction;
import com.sunsigne.tuto.ressources.images.ImageBank;

public class Wall extends GameObject implements ICollisionReaction {

	public Wall(int x, int y) {
		super(true, false, x, y);
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

		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);
	}

	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(GameObject clnDetectorObject) {
		blockPass(clnDetectorObject, this);	
	}

}
