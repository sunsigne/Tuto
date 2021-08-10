package com.sunsigne.tuto.system;

import com.sunsigne.tuto.controllers.GameKeyboardInput;
import com.sunsigne.tuto.object.Player;
import com.sunsigne.tuto.object.Wall;
import com.sunsigne.tuto.system.main.Tuto;

public class Conductor {

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
		Wall wall = new Wall(500, 300);
		wall.start();
		new Wall(900, 600).start();
	}

}
