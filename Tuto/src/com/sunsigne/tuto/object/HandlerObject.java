package com.sunsigne.tuto.object;

import java.awt.Graphics;
import java.util.LinkedList;

import com.sunsigne.tuto.system.main.IRender;
import com.sunsigne.tuto.system.main.ITick;
import com.sunsigne.tuto.util.AnnotationBank.Singleton;

@Singleton
public class HandlerObject implements ITick, IRender {

	////////// SIGNELTON ////////////

	private HandlerObject() {
		startTick();
		startRender();
	}

	private static HandlerObject instance = null;

	protected static HandlerObject getInstance() {
		if (instance == null)
			instance = new HandlerObject();
		return instance;
	}

	////////// MAP OR LIST ////////////

	private LinkedList<GameObject> handler_object_list = new LinkedList<>();

	protected void addObject(GameObject object) {
		if (object == null)
			return;

		handler_object_list.add(object);
	}

	protected void removeObject(GameObject object) {
		if (object == null)
			return;

		handler_object_list.remove(object);
	}

	////////// UTIL ////////////

	public GameObject getObjectAtPos(int x, int y) {
		for (GameObject tempObject : handler_object_list) {
			if (tempObject.getX() == x && tempObject.getY() == y) {
				return tempObject;
			}
		}
		return null;
	}

	protected boolean isPlayerExisting() {
		return handler_object_list.contains(Player.get());
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		for (GameObject tempObject : handler_object_list) {
			tempObject.tick();
			velocity(tempObject);
		}
	}

	private void velocity(GameObject tempObject) {
		tempObject.setX(tempObject.getX() + tempObject.getVelX());
		tempObject.setY(tempObject.getY() + tempObject.getVelY());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		for (GameObject tempObject : handler_object_list)
			tempObject.render(g);
	}
}
