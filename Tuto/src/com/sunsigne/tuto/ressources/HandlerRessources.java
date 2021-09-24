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

		if (handler_ressources_list.contains(ressources))
			return;

		handler_ressources_list.add(ressources);
	}

	////////// MINIMAL RESSOURCES ////////////

	@Override
	public void loadMinimalRessources() {
		try {
			for (IRessources tempRessources : handler_ressources_list)
				tempRessources.loadMinimalRessources();
		} catch (Exception e) {
			// same problem than in the main class
		}
	}

	////////// RESSOURCES ////////////

	@Override
	public void loadRessources() {
		try {
			for (IRessources tempRessources : handler_ressources_list)
				tempRessources.loadRessources();
		} catch (Exception e) {
			// same problem than in the main class
		}
	}

}
