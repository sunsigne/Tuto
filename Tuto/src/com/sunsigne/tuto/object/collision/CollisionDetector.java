package com.sunsigne.tuto.object.collision;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.object.HandlerObject;
import com.sunsigne.tuto.object.Player;
import com.sunsigne.tuto.system.Conductor;
import com.sunsigne.tuto.system.main.ITick;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public class CollisionDetector implements ITick {

	private GameObject clnDetectorObject;

	public CollisionDetector(GameObject clnDetectorObject) {
		this.clnDetectorObject = clnDetectorObject;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

		boolean cameraDependant = clnDetectorObject.isCameraDependant();
		boolean layerAbove = clnDetectorObject.isLayerAbove();
		var list = HandlerObject.getInstance().getList(cameraDependant, layerAbove);

		for (GameObject clnReactorObject : list) {

			if (clnDetectorObject == clnReactorObject)
				continue;

			if (clnDetectorObjectIsColliding(clnReactorObject)) {
				ICollisionReaction clnReactorObject0 = (ICollisionReaction) clnReactorObject;
				clnReactorObject0.collidingReaction(clnDetectorObject);
			}
		}
	}

	private boolean clnDetectorObjectIsColliding(GameObject clnReactorObject) {

		if (clnReactorObject instanceof ICollisionReaction == false)
			return false;
		
		if (clnDetectorObject instanceof Player && Conductor.DEBUG_MODE.getWallPassMode().getState())
			return false;
		
		if (clnDetectorObject.getBounds(DIRECTION.LEFT).intersects(clnReactorObject.getBounds()))
			return true;
		
		if (clnDetectorObject.getBounds(DIRECTION.RIGHT).intersects(clnReactorObject.getBounds()))
			return true;
		
		if (clnDetectorObject.getBounds(DIRECTION.UP).intersects(clnReactorObject.getBounds()))
			return true;
		
		if (clnDetectorObject.getBounds(DIRECTION.DOWN).intersects(clnReactorObject.getBounds()))
			return true;
				
		return false;
	}
}
