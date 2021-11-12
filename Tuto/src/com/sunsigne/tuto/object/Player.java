package com.sunsigne.tuto.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.tuto.object.collision.CollisionDetector;
import com.sunsigne.tuto.object.collision.ICollisionDetection;
import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.ressources.images.ImageTask;
import com.sunsigne.tuto.util.Cycloid;
import com.sunsigne.tuto.util.Facing;

public class Player extends GameObject implements ICollisionDetection, Facing {

	public static final int SPEED = 32 / 3;
		
	private Player(String name, int x, int y) {
		this(x, y);
		this.name = name.toLowerCase();
	}	
	
	private Player(int x, int y) {
		super(true, false, x, y);
		this.name = "rebecca";
		initCycloid();
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
	
	////////// NAME ////////////
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	////////// FACING ////////////
	
	private DIRECTION facing = DIRECTION.DOWN;
	private boolean[] watching = new boolean[4];
	private boolean flagX, flagY;
	
	@Override
	public DIRECTION getFacing() {
		return facing;
	}

	@Override
	public void setFacing(DIRECTION facing) {
		resetWatchingDirection();
		watching[facing.getNum()] = true;
		this.facing = facing;
	}
	
	private void resetWatchingDirection() {
		watching[DIRECTION.LEFT.getNum()] = false;
		watching[DIRECTION.RIGHT.getNum()] = false;
		watching[DIRECTION.UP.getNum()] = false;
		watching[DIRECTION.DOWN.getNum()] = false;
	}

	////////// TICK ////////////
	
	private final int ANIMATION_TIME = 10;
	private int time = ANIMATION_TIME;
	
	@Override
	public void tick() {
		updateWatchingDirection();
		
		if(isMotionless())
			freezeAnimation();
		else
			runAnimation();
	}
	
	private void updateWatchingDirection() {
		if(isMotionlessbyX())
			flagX = false;
		if(isMotionlessbyY())
			flagY = false;
		
		if(!flagY && getVelX() < 0)
		{
			setFacing(DIRECTION.LEFT);
			flagX = true;
		}
		
		if(!flagY && getVelX() > 0)
		{
			setFacing(DIRECTION.RIGHT);
			flagX = true;
		}
		
		if(!flagX && getVelY() < 0)
		{
			setFacing(DIRECTION.UP);
			flagY = true;
		}
		
		if(!flagX && getVelY() > 0)
		{
			setFacing(DIRECTION.DOWN);
			flagY = true;
		}
	}
	
	private void freezeAnimation() {
		for (int i = 0; i < walking.length; i++) {
			walking[i].setState(0);
		}
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			for (int i = 0; i < walking.length; i++) {
				walking[i].cycle();
			}
		}
	}
	
	////////// RENDER ////////////

	@Override
	public ImageBank getImageBank(int... index) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private Cycloid<BufferedImage>[] walking = new Cycloid[4];
	
	private void initCycloid() {
		walking[DIRECTION.LEFT.getNum()] = new Cycloid<>(getImages(DIRECTION.LEFT));
		walking[DIRECTION.RIGHT.getNum()] = new Cycloid<>(getImages(DIRECTION.RIGHT));
		walking[DIRECTION.UP.getNum()] = new Cycloid<>(getImages(DIRECTION.UP));
		walking[DIRECTION.DOWN.getNum()] = new Cycloid<>(getImages(DIRECTION.DOWN));
	}

	private BufferedImage getImage(String imageName) {
		return new ImageTask().loadImage("textures/characters/" + getName() + "/walking_" + imageName + ".png");
	}
	
	private BufferedImage[] getImages(DIRECTION direction) {
		
		BufferedImage img0 = getImage(direction.getName() + "_1");
		BufferedImage img1 = getImage(direction.getName() + "_0");
		BufferedImage img2 = getImage(direction.getName() + "_1");
		BufferedImage img3 = getImage(direction.getName() + "_2");
		
		return new BufferedImage[] { img0, img1, img2, img3 };
	}
	
	@Override
	public void render(Graphics g) {
		
		int facing = getFacing().getNum();
		BufferedImage img = walking[facing].getState();
		g.drawImage(img, x, y, w, h, null);
	}

	////////// COLLISION ////////////
	
	private CollisionDetector collisionDetector = new CollisionDetector(this);
	
	@Override
	public CollisionDetector getCollisionDetector() {
		return collisionDetector;
	}


}
