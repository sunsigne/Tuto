package com.sunsigne.tuto.system;

import com.sunsigne.tuto.object.Player;
import com.sunsigne.tuto.object.Wall;
import com.sunsigne.tuto.object.gui.GUIDebug;
import com.sunsigne.tuto.object.gui.GUIHealth;
import com.sunsigne.tuto.ressources.HandlerRessources;
import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.ressources.images.SheetBank;
import com.sunsigne.tuto.system.controllers.GameKeyboardInput;
import com.sunsigne.tuto.system.main.Tuto;
import com.sunsigne.tuto.util.ForceInit;

public class Conductor {

	public static final DebugMode DEBUG_MODE = new DebugMode();
	public static final GameKeyboardInput KEYBOARD = new GameKeyboardInput();
	
	////////// START & STOP ////////////
	
	private static boolean running;

	public static void startApp() {
		if (running)
			return;

		running = true;
		new Window(Tuto.getInstance());
		
		Tuto.getInstance().start();
		
		// LOADING OF MINIMAL RESSOURCES
		forceInit();
		HandlerRessources.getInstance().loadMinimalRessources();
		Tuto.getInstance().addKeyListener(KEYBOARD);
		Tuto.getInstance().requestFocus();
		
		// LOADING OF RESSOURCES
		HandlerRessources.getInstance().loadRessources();
		Tuto.getInstance().forceLoop();
		
		loadLevel();
	}

	public static void stopApp() {
		System.exit(1);
	}
	
	////////// RESSOURCES ////////////

	private static void forceInit() {

		new ForceInit().forceInit(SheetBank.class);
		new ForceInit().forceInit(ImageBank.class);
	}
	
	////////// LOAD LEVEL ////////////
	
	private static void loadLevel() {
		
		Player.get().start();
		new GUIHealth().start();
		new GUIDebug().start();
		
		new Wall(500, 300).start();		
		new Wall(900, 600).start();
		
		Tuto.getInstance().forceLoop();
		
		
	}

}
