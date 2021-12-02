package com.sunsigne.tuto.object.livings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.ressources.images.ImageTask;
import com.sunsigne.tuto.util.Cycloid;
import com.sunsigne.tuto.util.Facing;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public abstract class LivingObject extends GameObject implements Facing {

	protected LivingObject(String name, int x, int y) {
		super(true, false, x, y);
		
		this.name = name.toLowerCase();
		initCycloid();
	}	
	
	////////// NAME ////////////
	
	private String name;
	
	public String getName() {
		return name;
	}
		
	////////// FACING ////////////
	
	private DIRECTION facing = DIRECTION.DOWN;
	private boolean flagX, flagY;
	
	@Override
	public DIRECTION getFacing() {
		return facing;
	}

	@Override
	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}
	

	protected void updateWatchingDirection() {
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
	
	////////// PUSHING ////////////

	private final int PUSHSPEED = 20;
	
	private boolean isPushed;

	public boolean isPushed() {
		return isPushed;
	}

	public void setPushed(boolean isPushed) {
		this.isPushed = isPushed;
	}	
	
	public void pushToward(DIRECTION facing) {
		paralyse();
		pushed(facing);
		setPushed(true);
	}

	private void paralyse() {
		if(isPushed())
			setMotionless();
	}

	private void pushed(DIRECTION facing) {
		if (isPushed())
			return;
		if (facing == DIRECTION.LEFT)
			setVelX(-PUSHSPEED);
		if (facing == DIRECTION.RIGHT)
			setVelX(PUSHSPEED);
		if (facing == DIRECTION.UP)
			setVelY(-PUSHSPEED);
		if (facing == DIRECTION.DOWN)
			setVelY(PUSHSPEED);
	}
	
	////////// TICK ////////////

	private final int ANIMATION_TIME = 10;
	private int animation_time = ANIMATION_TIME;
	
	private final int PUSHING_TIME = 10;
	private int push_time = PUSHING_TIME;
	
	@Override
	public void tick() {
		updateWatchingDirection();
		
		if(isPushed()) --push_time;
		if (push_time < 0) stabilize();
		
		if(isMotionless())
			freezeAnimation();
		else
			runAnimation();
	}
	
	private void stabilize() {
		push_time = PUSHING_TIME;
		setPushed(false);
		setMotionless();
	}

	protected void freezeAnimation() {
		for (int i = 0; i < walking.length; i++) {
			walking[i].setState(0);
		}
	}

	protected void runAnimation() {
		animation_time--;
		if (animation_time < 0) {
			animation_time = ANIMATION_TIME;
			for (int i = 0; i < walking.length; i++) {
				walking[i].cycle();
			}
		}
	}
	
	//////////RENDER ////////////
	
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
		BufferedImage img = facing > -1 ? walking[facing].getState() : getImage("ground");
		g.drawImage(img, x, y, w, h, null);
	}
	
		
	

}
