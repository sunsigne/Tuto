package com.sunsigne.tuto.system;

import com.sunsigne.tuto.system.main.Tuto;

public class Conductor {

	////////// START & STOP ////////////
	
	private static boolean running;

	public static void startApp() {
		if (running)
			return;

		running = true;
		new Window(Tuto.getInstance());
		Tuto.getInstance().start();
	}
	
	public static void stopApp() {
		System.exit(1);
	}
}
