package com.sunsigne.tuto.system.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ConcurrentModificationException;

public class Tuto extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static Tuto tuto;
	
	////////// MAIN ////////////

	public static void main(String agrs[]) {
		tuto = new Tuto();
		new Window(tuto);
		tuto.start();
	}

	////////// THREAD ////////////

	private Thread thread;
	private boolean running;

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this, Window.NAME + "_main");
		thread.start();
	}

	public synchronized void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	////////// MAIN LOOP ////////////

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;

//		int ticks = 0;
//		int frames = 0;

		double delta = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			boolean shouldRender = false;

			while (delta >= 1) {
//				ticks++;

				tick();

				delta--;
				shouldRender = true;
			}

			if (shouldRender) {
//				frames++;

				render();

			}

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
//				System.out.println("Ticks : " + ticks + ", FPS : " + frames);
//				frames = 0;
//				ticks = 0;
			}
		}
		stop();

	}

	private void tick() {
		
	}
	

	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
		///
		g.setColor(new Color(255, 0, 0));
		g.fillRect(50, 50, 50, 50);
		///
		g.dispose();
		bs.show();
	}

	
}
