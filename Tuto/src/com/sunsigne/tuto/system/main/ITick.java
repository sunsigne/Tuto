package com.sunsigne.tuto.system.main;

public interface ITick {

	public default void startTick() {
		HandlerTick.getInstance().addObject(this);
	}
	
	public default void stopTick() {
		HandlerTick.getInstance().removeObject(this);
	}

	void tick();
}
