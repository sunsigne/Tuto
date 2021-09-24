package com.sunsigne.tuto.ressources.images;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.tuto.ressources.IRessources;

public class SheetBank implements IRessources {

	public SheetBank() {
		startRessources();
	}
	
	//////////MAP OR LIST ////////////
	
	private static Map<SheetBank, BufferedImage> sheets = new HashMap<>();
	
	// public for devs
	public static void addImage(SheetBank sheetBank, BufferedImage bufferedImage) {
		if(bufferedImage != null)
			sheets.put(sheetBank, bufferedImage);
	}
	
	public static BufferedImage getImage(SheetBank sheetBank) {
		return sheets.get(sheetBank);
	}

	////////// MINIMAL RESSOURCES ////////////
	
	@Override
	public void loadMinimalRessources() {
		// TODO Auto-generated method stub
		
	}
	
	////////// RESSOURCES ////////////
	
	protected static final SheetBank TOOL_SHEET = new SheetBank();
		
	@Override
	public void loadRessources() {

		ImageTask sheetTask = new ImageTask();
		
		addImage(TOOL_SHEET, sheetTask.loadImage("textures\\tool_sheet.png"));
	}


}
