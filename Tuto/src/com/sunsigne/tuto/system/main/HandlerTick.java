package com.sunsigne.tuto.system.main;

import java.util.LinkedList;

import com.sunsigne.tuto.util.AnnotationBank.Singleton;

@Singleton
public class HandlerTick implements ITick {

	////////// SIGNELTON ////////////

	private HandlerTick() {

	}

	private static HandlerTick instance = null;

	protected static HandlerTick getInstance() {
		if (instance == null)
			instance = new HandlerTick();
		return instance;
	}

	////////// MAP OR LIST ////////////

	private LinkedList<ITick> handler_tick_list = new LinkedList<>();

	protected void addObject(ITick tickable) {
		if (tickable == null)
			return;

		handler_tick_list.add(tickable);
	}

	protected void removeObject(ITick tickable) {
		if (tickable == null)
			return;

		handler_tick_list.remove(tickable);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		for (ITick temptick : handler_tick_list)
			temptick.tick();
	}

}
