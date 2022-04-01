package com.sunsigne.tuto.pathfinder;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.object.livings.Foe;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public interface PathSearcher {

	////////// PATH FINDER ////////////

	public GameObject getGoal();
	
	public void setGoal(GameObject goal);
	
	public DIRECTION getPath();
	
	public void setPath(DIRECTION path);
	
	public default void moveToGoal() {
		GameObject searcher = (GameObject) this;
		int tile = 3 * 32;
		
		if(searcher.getX() % tile != 0 | searcher.getY() % tile != 0)
			return;
		
		if(getGoal() == null | getGoal() == searcher) {
			setPath(DIRECTION.NULL);
			return;
		}
		
		PathFinder pathFinder = new PathFinder(this, getGoal(), true);
		setPath(pathFinder.getPath());
		followPath(searcher);
	}
	
	private void followPath(GameObject searcher) {

		searcher.setMotionless();
		
		switch (getPath()) {
		case NULL:
			searcher.setMotionless();
			break;
		case LEFT:
			searcher.setVelX(-Foe.SPEED);
			break;
		case RIGHT:
			searcher.setVelX(Foe.SPEED);
			break;
		case UP:
			searcher.setVelY(-Foe.SPEED);
			break;
		case DOWN:
			searcher.setVelY(Foe.SPEED);
			break;
		}
	}
	
}
