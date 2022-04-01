package com.sunsigne.tuto.pathfinder;

import java.awt.Graphics;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public class PathPointObject extends GameObject implements PathSearcher {

	public PathPointObject(int x, int y) {
		super(true, false, x, y);
	}

	////////// PATH FINDER ////////////

	private GameObject goal;

	@Override
	public GameObject getGoal() {
		return goal;
	}

	@Override
	public void setGoal(GameObject goal) {
		this.goal = goal;
	}

	private DIRECTION path;

	@Override
	public DIRECTION getPath() {
		return path;
	}

	@Override
	public void setPath(DIRECTION path) {
		this.path = path;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////
	
	@Override
	public ImageBank getImageBank(int... index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void render(Graphics g) {

	}

}
