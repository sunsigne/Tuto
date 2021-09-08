package com.sunsigne.tuto.object.collision;

import java.awt.Rectangle;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public interface ICollisionDetection {

	public CollisionDetector getCollisionDetector();

	public default Rectangle getBounds(DIRECTION direction, GameObject clnDetectorObject) {

		switch (direction) {

		case LEFT:
			return getBoundsLeft(clnDetectorObject);
		case RIGHT:
			return getBoundsRight(clnDetectorObject);
		case UP:
			return getBoundsUp(clnDetectorObject);
		case DOWN:
			return getBoundsDown(clnDetectorObject);
		case NULL:
			return null;
		}
		return null;
	}

	private Rectangle getBoundsLeft(GameObject clnDetectorObject) {
		int x = clnDetectorObject.getX();
		int y = clnDetectorObject.getY() + clnDetectorObject.getHeight() / 8;
		int w = clnDetectorObject.getWidth() / 6;
		int h = 6 * clnDetectorObject.getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsRight(GameObject clnDetectorObject) {
		int x = clnDetectorObject.getX() + 5 * clnDetectorObject.getWidth() / 6;
		int y = clnDetectorObject.getY() + clnDetectorObject.getHeight() / 8;
		int w = clnDetectorObject.getWidth() / 6;
		int h = 6 * clnDetectorObject.getHeight() / 8;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsUp(GameObject clnDetectorObject) {
		int x = clnDetectorObject.getX() + clnDetectorObject.getWidth() / 4;
		int y = clnDetectorObject.getY();
		int w = clnDetectorObject.getWidth() / 2;
		int h = clnDetectorObject.getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

	private Rectangle getBoundsDown(GameObject clnDetectorObject) {
		int x = clnDetectorObject.getX() + clnDetectorObject.getWidth() / 4;
		int y = clnDetectorObject.getY() + clnDetectorObject.getHeight() / 2;
		int w = clnDetectorObject.getWidth() / 2;
		int h = clnDetectorObject.getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}
}
