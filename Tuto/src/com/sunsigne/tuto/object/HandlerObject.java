package com.sunsigne.tuto.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.sunsigne.tuto.object.collision.ICollisionDetection;
import com.sunsigne.tuto.object.collision.ICollisionReaction;
import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.system.Conductor;
import com.sunsigne.tuto.system.main.HandlerRender;
import com.sunsigne.tuto.system.main.IRender;
import com.sunsigne.tuto.system.main.ITick;
import com.sunsigne.tuto.util.AnnotationBank.Singleton;
import com.sunsigne.tuto.util.Facing.DIRECTION;

@Singleton
public class HandlerObject implements ITick, IRender {

	////////// SIGNELTON ////////////

	private HandlerObject() {
		for (int camera = 0; camera < 2; camera++) {
			for (int layer = 0; layer < 2; layer++) {
				handler_object_list[camera][layer] = new LinkedList<GameObject>();
			}
		}

		startTick();

		startRenderDependency(true, false);
		startRenderDependency(true, true);
		startRenderDependency(false, false);
		startRenderDependency(false, true);
	}

	private void startRenderDependency(boolean cameraDependant, boolean layerAbove) {
		HandlerRender.getInstance().setCameraDependant(cameraDependant);
		HandlerRender.getInstance().setLayerAbove(layerAbove);
		startRender();
	}

	private static HandlerObject instance = null;

	public static HandlerObject getInstance() {
		if (instance == null)
			instance = new HandlerObject();
		return instance;
	}

	////////// MAP OR LIST ////////////

	@SuppressWarnings("unchecked")
	private LinkedList<GameObject>[][] handler_object_list = new LinkedList[2][2]; // - cameraDependency - layerAbove

	public LinkedList<GameObject> getList(boolean cameraDependant, boolean layerAbove) {

		int cameraDependency = cameraDependant ? 1 : 0;
		int layerAboveness = layerAbove ? 1 : 0;
		return handler_object_list[cameraDependency][layerAboveness];
	}

	protected void addObject(GameObject object) {
		if (object == null)
			return;

		var list = getList(object.isCameraDependant(), object.isLayerAbove());
		list.add(object);
	}

	protected void removeObject(GameObject object) {
		if (object == null)
			return;

		var list = getList(object.isCameraDependant(), object.isLayerAbove());
		list.remove(object);
	}

	////////// UTIL ////////////

	protected boolean isPlayerExisting() {

		if (getList(true, false).contains(Player.get()))
			return true;

		if (getList(true, true).contains(Player.get()))
			return true;

		return false;
	}

	public GameObject getObjectAtPos(boolean cameraDependant, boolean layerAbove, int x, int y) {
		for (GameObject tempObject : getList(cameraDependant, layerAbove)) {
			if (tempObject.getX() == x && tempObject.getY() == y) {
				return tempObject;
			}
		}
		return null;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		tickDependency(true, false);
		tickDependency(true, true);
		tickDependency(false, false);
		tickDependency(false, true);
	}

	private void tickDependency(boolean cameraDependant, boolean layerAbove) {
		var list = getList(cameraDependant, layerAbove);
		for (GameObject tempObject : list) {
			tempObject.tick();
			velocity(tempObject);
			collision(tempObject);
		}
	}

	private void velocity(GameObject tempObject) {
		tempObject.setX(tempObject.getX() + tempObject.getVelX());
		tempObject.setY(tempObject.getY() + tempObject.getVelY());
	}

	private void collision(GameObject tempObject) {
		if (tempObject instanceof ICollisionDetection) {
			ICollisionDetection clnDetectorObject = (ICollisionDetection) tempObject;
			clnDetectorObject.getCollisionDetector().tick();
		}			
	}
	
	////////// RENDER ////////////

	@Override
	public boolean isCameraDependant() {
		return HandlerRender.getInstance().isCameraDependant();
	}

	@Override
	public boolean isLayerAbove() {
		return HandlerRender.getInstance().isLayerAbove();
	}
	
	@Override
	public ImageBank getImageBank(int... index) {
		return null;
	}
		
	@Override
	public void render(Graphics g) {

		var list = getList(isCameraDependant(), isLayerAbove());

		for (GameObject tempObject : list)
			tempObject.render(g);

		if (Conductor.DEBUG_MODE.getHitboxMode().getState()) {
			for (GameObject tempObject : list)
				drawHitbox(tempObject, g);
		}
	}

	private void drawHitbox(GameObject tempObject, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);

		if (tempObject instanceof ICollisionReaction)
			g2d.draw(tempObject.getBounds());
		
		if (tempObject instanceof ICollisionDetection)
		{
			g2d.draw(tempObject.getBounds(DIRECTION.LEFT));
			g2d.draw(tempObject.getBounds(DIRECTION.RIGHT));
			g2d.draw(tempObject.getBounds(DIRECTION.UP));
			g2d.draw(tempObject.getBounds(DIRECTION.DOWN));
		}
			
	}
}
