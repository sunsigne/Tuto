package com.sunsigne.tuto.world;

import java.awt.image.BufferedImage;

import com.sunsigne.tuto.object.Wall;
import com.sunsigne.tuto.object.livings.Foe;
import com.sunsigne.tuto.object.livings.Player;
import com.sunsigne.tuto.pathfinder.PathPointObject;
import com.sunsigne.tuto.system.main.Tuto;

public class MapCreator {

	public void loadLevel(BufferedImage level) {

		int w = level.getWidth();
		int h = level.getHeight();
		int STEP = 1;

		for (int xx = 0; xx < h; xx += STEP) {
			for (int yy = 0; yy < w; yy += STEP) {

				int pixel = level.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				int x0 = xx * 3 * 32 / STEP;
				int y0 = yy * 3 * 32 / STEP;
				
				if(red == 128 && green == 128 && blue == 128) {
					new PathPointObject(x0, y0).start();	
				}
				
				if(red == 255 && green == 255 && blue == 255) {
					new Wall(x0, y0).start();	
				}
				
				if(red == 255 && green == 0 && blue == 0) {
					Player player = Player.get();
					player.setX(x0);
					player.setY(y0);
					player.start();
				}
				
				if(red == 255 && green == 255 && blue == 0) {
					new Foe(x0, y0).start();	
				}
			}
		}
		Tuto.getInstance().forceLoop();
	}

}
