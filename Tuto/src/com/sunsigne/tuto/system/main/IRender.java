package com.sunsigne.tuto.system.main;

import java.awt.Graphics;

public interface IRender {

	public default void startRender() {
		HandlerRender.getInstance().addObject(this);
	}
	
	public default void stopRender() {
		HandlerRender.getInstance().removeObject(this);
	}

	void render(Graphics g);
}
