package com.sunsigne.tuto.object.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.tuto.object.GameObject;
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
	public void render(Graphics g) {
		
		DebugMode debugmode = Conductor.DEBUG_MODE;
		
		Font font = new Font("arial", 1, 20);
		g.setFont(font);		
		
		if(debugmode.getMultiToolMode().getState())
		{
			g.setColor(Color.GREEN);
			g.fillRect(x, y, w, h);
			g.setColor(Color.WHITE);
			g.drawString("multitool", x + 20, y + 60);
		}
		
		if(debugmode.getWallPassMode().getState())
		{
			g.setColor(Color.GREEN);
			g.fillRect(x, y - h, w, h);
			g.setColor(Color.WHITE);
			g.drawString("wallpass", x + 20, y + 60 - h);
		}
		
		if(debugmode.getSkipMode().getState())
		{
			g.setColor(Color.GREEN);
			g.fillRect(x, y - 2*h, w, h);
			g.setColor(Color.WHITE);
			g.drawString("skip", x + 20, y + 60 - 2*h);
		}
		
		if(debugmode.getHitboxMode().getState())
		{
			g.setColor(Color.GREEN);
			g.fillRect(x, y - 3*h, w, h);
			g.setColor(Color.WHITE);
			g.drawString("hitbox", x + 20, y + 60 - 3*h);
		}
		
		if(debugmode.getFastMode().getState())
		{
			g.setColor(Color.GREEN);
			g.fillRect(x, y - 4*h, w, h);
			g.setColor(Color.WHITE);
			g.drawString("fast", x + 20, y + 60 - 4*h);
		}
			
		
	}
}
