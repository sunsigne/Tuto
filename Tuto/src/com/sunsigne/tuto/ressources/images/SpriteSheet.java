package com.sunsigne.tuto.ressources.images;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	public SpriteSheet(SheetBank sheetBank) {
		this.image = sheetBank.getImage();
	}

	////////// IMAGE ////////////

	private BufferedImage image;

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
