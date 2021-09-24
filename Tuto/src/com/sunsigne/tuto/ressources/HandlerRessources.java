package com.sunsigne.tuto.ressources;

import java.util.LinkedList;

public class HandlerRessources implements IRessources {

	////////// SIGNELTON ////////////

	private HandlerRessources() {

	}

	private static HandlerRessources instance = null;

	public static HandlerRessources getInstance() {
		if (instance == null)
			instance = new HandlerRessources();
		return instance;
	}

	////////// MAP OR LIST ////////////

	private LinkedList<IRessources> handler_ressources_list = new LinkedList<>();

	protected void addObject(IRessources ressources) {
		if (ressources == null)
			return;

		if (isObjectAlreadyInList(ressources))
			return;

		handler_ressources_list.add(ressources);
	}

	private boolean isObjectAlreadyInList(IRessources ressources) {

		Object obj = ressources.getClass();

		for (IRessources tempRessources : handler_ressources_list) {
			Object tempObj = tempRessources.getClass();
			if (obj == tempObj)
				return true;
		}
		return false;

	}

	////////// MINIMAL RESSOURCES ////////////

	@Override
	public void loadMinimalRessources() {
		for (IRessources tempRessources : handler_ressources_list)
			tempRessources.loadMinimalRessources();
	}

	////////// RESSOURCES ////////////

	@Override
	public void loadRessources() {
		for (IRessources tempRessources : handler_ressources_list)
			tempRessources.loadRessources();
	}

}
