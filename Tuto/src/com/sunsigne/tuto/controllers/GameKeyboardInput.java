package com.sunsigne.tuto.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.sunsigne.tuto.object.Player;
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
		
		exitKey(key);
		directionKey(key, true);
	}
	
	@Override
	 public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		directionKey(key, false);
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
		
		return isPlayerExisting;
	}
	
	
	private void movePlayer() {

		movePlayerbyX();
		movePlayerbyY();
	}

	private void movePlayerbyX() {

		if(directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(- Player.SPEED);
		else if(directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(0);
		else if(!directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(Player.SPEED);
		else if(!directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			Player.get().setVelX(0);
	}

	private void movePlayerbyY() {
		if(directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(- Player.SPEED);
		else if(directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(0);
		else if(!directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(Player.SPEED);
		else if(!directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			Player.get().setVelY(0);
	}
	
}
