package com.sunsigne.tuto.object;

import java.awt.Rectangle;

import com.sunsigne.tuto.system.main.IRender;
import com.sunsigne.tuto.system.main.ITick;

public abstract class GameObject implements ITick, IRender {

	public GameObject(int x, int y) {
		
		this.x = x;
		this.y = y;
		initX = x;
		initY = y;
		
		w = 3 * 32;
		h = 3 * 32;		
	}
	
	////////// HANDLER ////////////
	
	public void start() {
		HandlerObject.getInstance().addObject(this);
	}
	
	public void stop() {
		HandlerObject.getInstance().removeObject(this);
	}
	
	////////// POSITION ////////////
	
	protected int x, y;
	protected int initX, initY;
		
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	////////// SIZE ////////////
	
	protected int w, h;
	
		
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getBounds().x;
		rect[1] = getBounds().y;
		rect[2] = getBounds().width;
		rect[3] = getBounds().height;
		return rect;
	}
		
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	
	////////// VELOCICY ////////////
	
	protected int velX, velY;
			
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}

	public boolean isMotionlessbyX(){
		return velX == 0;
	}
	
	public boolean isMotionlessbyY(){
		return velY == 0;
	}
	
	public void setMotionless() {
		velX = 0;
		velY = 0;
	}
}
