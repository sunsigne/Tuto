package com.sunsigne.tuto.ressources;

public interface IRessources {

	// Hi modder ! If you want to add new Ressources to the game, don't forget to write the line
	// HandlerRessources.getInstance().loadRessources(); in your code to refresh the loading !
	public default void startRessources() {
		HandlerRessources.getInstance().addObject(this);
	}
	
	void loadMinimalRessources();
	
	void loadRessources();
}
