package com.sunsigne.tuto.system;

import com.sunsigne.tuto.object.Player;
import com.sunsigne.tuto.object.Wall;
import com.sunsigne.tuto.object.gui.GUIDebug;
import com.sunsigne.tuto.object.gui.GUIHealth;
import com.sunsigne.tuto.system.controllers.GameKeyboardInput;
import com.sunsigne.tuto.system.main.Tuto;

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
		
		Tuto.getInstance().addKeyListener(KEYBOARD);
		Tuto.getInstance().requestFocus();
		
		loadLevel();
	}

	public static void stopApp() {
		System.exit(1);
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
