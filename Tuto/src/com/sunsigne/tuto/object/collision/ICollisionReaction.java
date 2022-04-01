package com.sunsigne.tuto.object.collision;

import java.awt.Rectangle;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.pathfinder.TilePos;
import com.sunsigne.tuto.util.Facing.DIRECTION;
import com.sunsigne.tuto.util.GenericListener;

public interface ICollisionReaction {

	void collidingReaction(GameObject clnDetectorObject);

	public default Rectangle getBounds(GameObject clnReactorObject) {
		int x = clnReactorObject.getX();
		int y = clnReactorObject.getY();
		int w = clnReactorObject.getWidth();
		int h = clnReactorObject.getHeight();
		return new Rectangle(x, y, w, h);
	}

	public default void blockPass(GameObject clnDetectorObject, GameObject clnReactorObject) {
		collidingReaction(clnDetectorObject, clnReactorObject, true, null);
	}

	public default void collidingReaction(GameObject clnDetectorObject, GameObject clnReactorObject, boolean blockPass,
			GenericListener listener) {

		TilePos tilePos = new TilePos();
		
		if (clnDetectorObject.getBounds(DIRECTION.LEFT).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setX(tilePos.getTilePos(clnReactorObject.getX() + clnReactorObject.getBounds().width));
			if (listener != null)
				listener.doAction();
		}

		if (clnDetectorObject.getBounds(DIRECTION.RIGHT).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setX(tilePos.getTilePos(clnReactorObject.getX() - clnDetectorObject.getWidth()));
			if (listener != null)
				listener.doAction();
		}

		if (clnDetectorObject.getBounds(DIRECTION.UP).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setY(tilePos.getTilePos(clnReactorObject.getY() + clnReactorObject.getBounds().height));
			if (listener != null)
				listener.doAction();
		}
		if (clnDetectorObject.getBounds(DIRECTION.DOWN).intersects(clnReactorObject.getBounds())) {
			if (blockPass)
				clnDetectorObject.setY(tilePos.getTilePos(clnReactorObject.getY() - clnDetectorObject.getHeight()));
			if (listener != null)
				listener.doAction();

		}

	}
}
