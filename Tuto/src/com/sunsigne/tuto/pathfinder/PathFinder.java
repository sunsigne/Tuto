package com.sunsigne.tuto.pathfinder;

import java.util.LinkedList;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.object.HandlerObject;
import com.sunsigne.tuto.object.Wall;
import com.sunsigne.tuto.system.main.ITick;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public class PathFinder {

	public PathFinder(PathSearcher searcher, GameObject goal, boolean allow_complex_path) {

		this.searcher = searcher;
		this.goal = goal;

		setX(((GameObject) searcher).getX());
		setY(((GameObject) searcher).getY());

		path = findPath(allow_complex_path);
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

	private DIRECTION findPath(boolean allow_complex_path) {

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

		if (!allow_complex_path)
			return DIRECTION.NULL;

		return findComplexePath();
	}

	private DIRECTION findComplexePath() {

		LinkedList<PathPointObject> valid_path_point_list = createValidPathPointList();

		if (valid_path_point_list.isEmpty())
			return DIRECTION.NULL;

		boolean pathDoesExist = true;

		while (pathDoesExist) {

			DIRECTION tryPath = getPathFromSeacherToValidPoint(valid_path_point_list);

			if (tryPath != DIRECTION.NULL)
				return tryPath;

			pathDoesExist = expandingValidPoint(valid_path_point_list);
		}

		// occurs when there is definitively no existing path between searcher and goal
		return DIRECTION.NULL;
	}

	private boolean expandingValidPoint(LinkedList<PathPointObject> valid_path_point_list) {

		boolean there_are_more_paths = false;

		// creation of a copy list, to avoir currentModification
		LinkedList<PathPointObject> copy_list = new LinkedList<PathPointObject>();
		copy_list.addAll(valid_path_point_list);

		for (ITick tickable : HandlerObject.getList(true, false)) {

			if (tickable instanceof PathPointObject == false)
				continue;

			PathPointObject tempPassPoint = (PathPointObject) tickable;

			if (valid_path_point_list.contains(tempPassPoint))
				continue;

			for (PathPointObject previousPassPoint : copy_list) {

				PathFinder tempPathFinder = new PathFinder(tempPassPoint, previousPassPoint, false);

				if (tempPathFinder.getPath() != DIRECTION.NULL) {
					valid_path_point_list.add(tempPassPoint);
					there_are_more_paths = true;
				}
			}
		}

		return there_are_more_paths;
	}

	private DIRECTION getPathFromSeacherToValidPoint(LinkedList<PathPointObject> valid_path_point_list) {

		// searching if any valid path point is reachable by seacher
		for (PathPointObject tempPassPoint : valid_path_point_list) {

			PathFinder tempPathFinder = new PathFinder(searcher, tempPassPoint, false);

			if (tempPathFinder.getPath() != DIRECTION.NULL)
				return tempPathFinder.getPath();
		}
		return DIRECTION.NULL;
	}

	private LinkedList<PathPointObject> createValidPathPointList() {

		var valid_path_point_list = new LinkedList<PathPointObject>();

		// searching for all path points than can reach goal
		for (ITick tickable : HandlerObject.getList(true, false)) {
			if (tickable instanceof PathPointObject == false)
				continue;

			PathPointObject tempPassPoint = (PathPointObject) tickable;
			PathFinder tempPathFinder = new PathFinder(tempPassPoint, searcher.getGoal(), false);

			if (tempPathFinder.getPath() != DIRECTION.NULL)
				valid_path_point_list.add(tempPassPoint);
		}
		return valid_path_point_list;
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

			if (range > 0)
				range = range - 3 * 32;
			if (range < 0)
				range = range + 3 * 32;
		}
		return false;
	}

}
