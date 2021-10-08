package com.sunsigne.tuto.ressources.images;

import java.awt.image.BufferedImage;

public class ImageBank {

	private ImageBank(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	////////// IMAGE ////////////
	
	private BufferedImage bufferedImage;
	
	public BufferedImage getImage() {
		return bufferedImage;
	}

	////////// RESSOURCES ////////////

	private static final SpriteSheet TOOL_SHEET = new SpriteSheet(SheetBank.TOOL_SHEET);
	public static final ImageBank[][] TOOL = new ImageBank[8][6]; // - difficulty - tool

	public static void loadRessources() {

		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 8; i++) {
				TOOL[i][j] = new ImageBank(TOOL_SHEET.grabImage(i, j + 1, 32, 32));
			}
		}
	}
}
