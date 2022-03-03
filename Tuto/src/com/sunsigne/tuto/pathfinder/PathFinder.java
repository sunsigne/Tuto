package com.sunsigne.tuto.pathfinder;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.object.HandlerObject;
import com.sunsigne.tuto.object.Wall;
import com.sunsigne.tuto.system.main.HandlerTick;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public class PathFinder {

	public PathFinder(PathSearcher searcher, GameObject goal) {

		this.searcher = searcher;
		this.goal = goal;

		setX(((GameObject) searcher).getX());
		setY(((GameObject) searcher).getY());

		path = findPath();
	}

	private PathSearcher searcher;
	private GameObject goal;

	// WARNING ! This is not a pos ! This is the DISTANCE between searcher and goal
	private int tileX, tileY;

	private void calculDistance() {
		tileX = getTilePos(goal.getX()) - getX();
		tileY = getTilePos(goal.getY()) - getY();
	}

	////////// POSITION ////////////

	private int x, y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = getTilePos(x);
		calculDistance();
	}

	public void setY(int y) {
		this.y = getTilePos(y);
		calculDistance();
	}

	private int getTilePos(int pos) {
		return new TilePos().getTilePos(pos);
	}

	////////// PATH FINDER ////////////

	private DIRECTION path = DIRECTION.NULL;

	public DIRECTION getPath() {
		return path;
	}

	private boolean isGoalReached() {
		return tileX == 0 & tileY == 0;
	}

	private boolean isPathStraightHorizontal(boolean WOH) {
		return tileX != 0 & tileY == 0 & !WOH;
	}

	private boolean isPathStraightVertical(boolean WOV) {
		return tileX == 0 & tileY != 0 & !WOV;
	}
	
	private boolean isPathSigneCurveHV(boolean WOH, boolean WGV) {
		return tileX != 0 & tileY != 0 & !WOH & !WGV;
	}

	private boolean isPathSigneCurveVH(boolean WOV, boolean WGH) {
		return tileX != 0 & tileY != 0 & !WOV & !WGH;
	}

	private DIRECTION findPath() {

		if (isGoalReached()) {
			searcher.setGoal(null);
			return path;
		}
		
		boolean WOH = wallOnTheWay(0, true); // wall from origin to goal moving horizontally
		boolean WOV = wallOnTheWay(0, false); // wall from origin to goal moving vertically
		boolean WGH = wallOnTheWay(tileY, true); // wall from gap to goal moving horizontally
		boolean WGV = wallOnTheWay(tileX, false); // wall from gap to goal moving vertically

		if (isPathStraightHorizontal(WOH) | isPathStraightVertical(WOV))
			return getStraightPath();

		if (isPathSigneCurveHV(WOH, WGV)) {
			tileY = 0;
			return getStraightPath();
		}

		if (isPathSigneCurveVH(WOV, WGH)) {
			tileX = 0;
			return getStraightPath();
		}

		return DIRECTION.NULL;
	}

	private DIRECTION getStraightPath() {

		if (tileX < 0)
			return DIRECTION.LEFT;
		if (tileX > 0)
			return DIRECTION.RIGHT;
		if (tileY < 0)
			return DIRECTION.UP;
		if (tileY > 0)
			return DIRECTION.DOWN;

		return DIRECTION.NULL;
	}

	private boolean wallOnTheWay(int from, boolean horizontal) {

		int range = horizontal ? tileX : tileY;

		while (range != 0) {

			GameObject object;

			if (horizontal)
				object = HandlerObject.getObjectAtPos((GameObject) searcher, getX() + range, getY() + from);
			else
				object = HandlerObject.getObjectAtPos((GameObject) searcher, getX() + from, getY() + range);

			if (object instanceof Wall)
				return true;
			
			if(range > 0)
				range = range - 3 * 32;
			if(range < 0)
				range = range + 3 * 32;			
		}
		return false;
	}

}
