package com.sunsigne.tuto.system.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.util.AnnotationBank.Singleton;

@Singleton
public class HandlerRender implements IRender {

	////////// SIGNELTON ////////////

	private HandlerRender() {
		for (int camera = 0; camera < 2; camera++) {
			for (int layer = 0; layer < 2; layer++) {
				handler_render_list[camera][layer] = new LinkedList<IRender>();
			}
		}
	}

	private static HandlerRender instance = null;

	public static HandlerRender getInstance() {
		if (instance == null)
			instance = new HandlerRender();
		return instance;
	}

	////////// MAP OR LIST ////////////

	@SuppressWarnings("unchecked")
	private LinkedList<IRender>[][] handler_render_list = new LinkedList[2][2]; // - cameraDependency - layerAboveness

	private LinkedList<IRender> getList(boolean cameraDependant, boolean layerAbove) {

		int cameraDependency = cameraDependant ? 1 : 0;
		int layerAboveness = layerAbove ? 1 : 0;
		return handler_render_list[cameraDependency][layerAboveness];
	}

	protected void addObject(IRender renderable) {
		if (renderable == null)
			return;

		var list = getList(renderable.isCameraDependant(), renderable.isLayerAbove());
		list.add(renderable);
	}

	protected void removeObject(IRender renderable) {
		if (renderable == null)
			return;

		var list = getList(renderable.isCameraDependant(), renderable.isLayerAbove());
		list.remove(renderable);
	}

	////////// RENDER ////////////

	private boolean cameraDependant;
	private boolean layerAbove;
	
	@Override
	public boolean isCameraDependant() {
		return cameraDependant;
	}

	public void setCameraDependant(boolean cameraDependant) {
		this.cameraDependant = cameraDependant;
	}

	@Override
	public boolean isLayerAbove() {
		return layerAbove;
	}

	public void setLayerAbove(boolean layerAbove) {
		this.layerAbove = layerAbove;
	}
	
	@Override
	public ImageBank getImageBank(int... index) {
		return null;
	}
	
	@Override
	public void render(Graphics g) {
		var list = getList(cameraDependant, layerAbove);
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			// size - i - 1 --> the last object is render first, then the previous one, and
			// so on. As HandlerObject is added berofe back layers, the opposite would turns
			// objects invisible (because BEHIND the back layers)
			IRender tempRender = list.get(size - i - 1);
			tempRender.render(g);
		}
	}



}