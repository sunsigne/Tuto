package com.sunsigne.tuto.system.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.tuto.ressources.images.ImageBank;
import com.sunsigne.tuto.ressources.images.ImageTask;

public interface IRender {

	boolean isCameraDependant();

	boolean isLayerAbove();

	public default void startRender() {
		HandlerRender.getInstance().addObject(this);
	}

	public default void stopRender() {
		HandlerRender.getInstance().removeObject(this);
	}

	ImageBank getImageBank(int... index);

	public default BufferedImage getImage(int... index) {
		if (getImageBank(index) == null)
			return new ImageTask().drawMissingTexture();

		if (index == null)
			return new ImageBank().getImage(getImageBank());

		return new ImageBank().getImage(getImageBank(index));
	}

	void render(Graphics g);
}
