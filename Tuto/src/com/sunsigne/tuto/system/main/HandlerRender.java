package com.sunsigne.tuto.system.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.sunsigne.tuto.system.util.AnnotationBank.Singleton;

@Singleton
public class HandlerRender implements IRender {

	////////// SIGNELTON ////////////

	private HandlerRender() {

	}

	private static HandlerRender instance = null;

	protected static HandlerRender getInstance() {
		if (instance == null)
			instance = new HandlerRender();
		return instance;
	}

	////////// MAP OR LIST ////////////

	private LinkedList<IRender> handler_render_list = new LinkedList<>();

	protected void addObject(IRender renderable) {
		if (renderable == null)
			return;

		handler_render_list.add(renderable);
	}

	protected void removeObject(IRender renderable) {
		if (renderable == null)
			return;

		handler_render_list.remove(renderable);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		for (IRender temprender : handler_render_list)
			temprender.render(g);
	}

}