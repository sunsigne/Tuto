package com.sunsigne.tuto.object.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.tuto.object.GameObject;
import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.system.Conductor;
import com.sunsigne.tuto.system.DebugMode;
import com.sunsigne.tuto.system.Window;

public class GUIDebug extends GameObject {

	public GUIDebug() {
		super(false, false, 0, 0);


		x = Window.WIDHT - 4 * 32;
		y = Window.HEIGHT - 4 * 32;
		w = 4 * 32;
		h = 4 * 32;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		
	}

	////////// RENDER ////////////
		
	@Override
	public ImageBank getImageBank(int... index) {
		return ImageBank.TOOL[index[0]][0];
	}
	
	
	@Override
	public void render(Graphics g) {
		
		DebugMode debugmode = Conductor.DEBUG_MODE;
		
		Font font = new Font("arial", 1, 20);
		g.setFont(font);		
		
		if(debugmode.getMultiToolMode().getState())
		{
			g.drawImage(getImage(1), x, y, w, h, null);
		}
		
		if(debugmode.getWallPassMode().getState())
		{
			g.drawImage(getImage(2), x, y - h, w, h, null);
		}
		
		if(debugmode.getSkipMode().getState())
		{
			g.drawImage(getImage(3), x, y - 2*h, w, h, null);
		}
		
		if(debugmode.getHitboxMode().getState())
		{
			g.drawImage(getImage(4), x, y - 3*h, w, h, null);
		}
		
		if(debugmode.getFastMode().getState())
		{
			g.drawImage(getImage(5), x, y - 4*h, w, h, null);
		}
			
		
	}
}
