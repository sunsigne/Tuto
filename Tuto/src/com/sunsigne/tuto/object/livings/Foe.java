package com.sunsigne.tuto.object.livings;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.object.collision.CollisionDetector;
import com.sunsigne.tuto.object.collision.ICollisionDetection;
import com.sunsigne.tuto.object.collision.ICollisionReaction;
import com.sunsigne.tuto.pathfinder.PathSearcher;
import com.sunsigne.tuto.pathfinder.TilePos;

public class Foe extends LivingObject implements PathSearcher, ICollisionDetection, ICollisionReaction {

	public static final int SPEED = 6;

	public Foe(String name, int x, int y) {
		super(name, x, y);
		goal = Player.get();
	}

	public Foe(int x, int y) {
		this("gamma", x, y);
	}

	////////// TICK ////////////

	private boolean isStuned;
	private final int STUNNING_TIME = 30;
	private int stun_time = STUNNING_TIME;

	public boolean isStuned() {
		return isStuned;
	}

	public void setStuned(boolean isStuned) {
		if (isStuned)
			setMotionless();
		this.isStuned = isStuned;
	}

	@Override
	public void tick() {
		updateWatchingDirection();

		if (isStuned())
			--stun_time;
		if (stun_time < 0)
			wakeUp();

		if (!isStuned())
			moveToGoal();

		if (isMotionless())
			freezeAnimation();
		else
			runAnimation();
	}
		
	private void shiftFoe() {
		setX(new TilePos().getTilePos(getX()));
		setY(new TilePos().getTilePos(getY()));
	}

	////////// COLLISION ////////////

	private void wakeUp() {
		stun_time = STUNNING_TIME;
		setStuned(false);
	}

	private CollisionDetector collisionDetector = new CollisionDetector(this);

	@Override
	public CollisionDetector getCollisionDetector() {
		return collisionDetector;
	}

	@Override
	public void collidingReaction(GameObject clnDetectorObject) {
		if (clnDetectorObject instanceof Player) {
			Player player = Player.get();
			player.pushToward(getFacing());
			shiftFoe();
			setStuned(true);
		}
		blockPass(clnDetectorObject, this);
	}

	////////// PATH FINDER ////////////

	public GameObject goal;

	@Override
	public GameObject getGoal() {
		return goal;
	}

	@Override
	public void setGoal(GameObject goal) {
		this.goal = goal;
	}

	public DIRECTION path;

	@Override
	public DIRECTION getPath() {
		return path;
	}

	@Override
	public void setPath(DIRECTION path) {
		this.path = path;
	}


}
