package com.sunsigne.tuto.ressources.images;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.tuto.ressources.IRessources;

public class ImageBank implements IRessources {

	public ImageBank() {
		startRessources();
	}

	////////// MAP OR LIST ////////////

	private static Map<ImageBank, BufferedImage> images = new HashMap<>();

	// public for devs
	public static void addImage(ImageBank imageBank, BufferedImage bufferedImage) {
		if (bufferedImage != null)
			images.put(imageBank, bufferedImage);
	}

	public static BufferedImage getImage(ImageBank imageBank) {
		return images.get(imageBank);
	}

	////////// MINIMAL RESSOURCES ////////////

	@Override
	public void loadMinimalRessources() {
		// TODO Auto-generated method stub

	}

	////////// RESSOURCES ////////////

	private static SpriteSheet TOOL_SHEET;
	public static ImageBank[][] TOOL = new ImageBank[8][6]; // - difficulty - tool

	@Override
	public void loadRessources() {

		TOOL_SHEET = new SpriteSheet(SheetBank.TOOL_SHEET);
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 8; i++) {
				TOOL[i][j] = new ImageBank();
				addImage(TOOL[i][j], TOOL_SHEET.grabImage(i, j + 1, 32, 32));
			}
		}
	}
}
