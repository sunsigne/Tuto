package com.sunsigne.tuto.system.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.sunsigne.tuto.object.livings.Player;
import com.sunsigne.tuto.pathfinder.TilePos;
import com.sunsigne.tuto.ressources.FileTask;
import com.sunsigne.tuto.system.Conductor;
import com.sunsigne.tuto.util.Facing.DIRECTION;

public class GameKeyboardInput extends KeyAdapter {

	public GameKeyboardInput() {
		setExitKeyEvent(KeyEvent.VK_ESCAPE);
		setDirectionKeyEvent(DIRECTION.LEFT, KeyEvent.VK_Q);
		setDirectionKeyEvent(DIRECTION.RIGHT, KeyEvent.VK_D);
		setDirectionKeyEvent(DIRECTION.UP, KeyEvent.VK_Z);
		setDirectionKeyEvent(DIRECTION.DOWN, KeyEvent.VK_S);
	}
	
	////////// KEY ADAPTER ////////////
	
	@Override
	 public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		degugKey(key);
		exitKey(key);
		saveKey(key);
		directionKey(key, true);
	}

	private void saveKey(int key) {
		if (key == KeyEvent.VK_ENTER)
		{
			Player player = Player.get();
			
			int x0 = 21;
			int y0 = 12;
			int registeredX = new TilePos().getTilePos(player.getX()) / (3 * 32) - x0;
			int registeredY = new TilePos().getTilePos(y0 + player.getY()) / (3 * 32) - y0;
			new FileTask().write("XPOS", "data/player.txt", Integer.toString(registeredX));
			new FileTask().write("YPOS", "data/player.txt", Integer.toString(registeredY));
		}
	}

	@Override
	 public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		directionKey(key, false);
	}
	
	////////// DEBUG MOD ////////////
	
	private void degugKey(int key) {
		
		if (key == KeyEvent.VK_F1)
			Conductor.DEBUG_MODE.getMultiToolMode().cycle();
				
		if (key == KeyEvent.VK_F2)
			Conductor.DEBUG_MODE.getWallPassMode().cycle();
		
		if (key == KeyEvent.VK_F3)
			Conductor.DEBUG_MODE.getSkipMode().cycle();
				
		if (key == KeyEvent.VK_F4)
			Conductor.DEBUG_MODE.getHitboxMode().cycle();
		
		if (key == KeyEvent.VK_F5)
			Conductor.DEBUG_MODE.getFastMode().cycle();
	}
	
	////////// EXIT KEY ////////////
	
	private int exitKeyEvent;

	public void setExitKeyEvent(int exitKeyEvent) {
		this.exitKeyEvent = exitKeyEvent;
	}

	private void exitKey(int key) {
		if (key == exitKeyEvent)
			Conductor.stopApp();
	}

	
	////////// DIRECTION KEY ////////////
	
	private int[] directionKeyEvent = new int[4];
	private boolean[] directionKeyPressed = new boolean[4];
	
	public void setDirectionKeyEvent(DIRECTION direction, int directionKeyEvent) {
		this.directionKeyEvent[direction.getNum()] = directionKeyEvent;
	}
	
	private void directionKey(int key, boolean pressed) {
		
		if(key == directionKeyEvent[DIRECTION.LEFT.getNum()] || key == KeyEvent.VK_LEFT)
			directionKeyPressed[DIRECTION.LEFT.getNum()] = pressed;
		
		if(key == directionKeyEvent[DIRECTION.RIGHT.getNum()] || key == KeyEvent.VK_RIGHT)
			directionKeyPressed[DIRECTION.RIGHT.getNum()] = pressed;
		
		if(key == directionKeyEvent[DIRECTION.UP.getNum()] || key == KeyEvent.VK_UP)
			directionKeyPressed[DIRECTION.UP.getNum()] = pressed;
		
		if(key == directionKeyEvent[DIRECTION.DOWN.getNum()] || key == KeyEvent.VK_DOWN)
			directionKeyPressed[DIRECTION.DOWN.getNum()] = pressed;
		
		if(isPlayerMovable())
			movePlayer();
	}
	
	////////// PLAYER ////////////

	private boolean isPlayerMovable() {
		
		boolean isPlayerExisting = Player.isExisting();
		boolean isPlayerPushed = Player.isExisting() ? Player.get().isPushed() : false;
		
		return isPlayerExisting & !isPlayerPushed;
	}
	
	
	private void movePlayer() {

		int speedMultiplicator = Conductor.DEBUG_MODE.getFastMode().getState() ? 3 : 1;
		movePlayerbyX(speedMultiplicator);
		movePlayerbyY(speedMultiplicator);
	}

	private void movePlayerbyX(int speedMultiplicator) {

		if(directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(- Player.SPEED * speedMultiplicator);
		else if(directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(0);
		else if(!directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(Player.SPEED * speedMultiplicator);
		else if(!directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(0);
	}

	private void movePlayerbyY(int speedMultiplicator) {
		if(directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(- Player.SPEED * speedMultiplicator);
		else if(directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(0);
		else if(!directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(Player.SPEED * speedMultiplicator);
		else if(!directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(0);
	}
	
}
