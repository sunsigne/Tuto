package com.sunsigne.tuto.ressources.images;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(SheetBank sheetBank) {
		this.image = SheetBank.getImage(sheetBank);
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		
		BufferedImage img = null;
		
		try {
		img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		
		} catch (Exception e) {
			img = new ImageTask().drawMissingTexture(width, height);
		}
		
		return img;
	}
	
}
