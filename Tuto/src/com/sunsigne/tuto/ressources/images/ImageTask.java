package com.sunsigne.tuto.ressources.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sunsigne.tuto.system.main.Tuto;

public class ImageTask {

	private final URL loc = Tuto.class.getProtectionDomain().getCodeSource().getLocation();

	public BufferedImage loadImage(String path0) {

		BufferedImage image = null;

		try {
			String path = "\\ressources\\" + path0;
			URL url = new File((new File(loc.toURI())).getParent() + path).toURI().toURL();
			image = ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
			image = drawMissingTexture();
		}

		return image;
	}

	public BufferedImage drawMissingTexture() {
		return drawMissingTexture(32, 32);
	}
	
	public BufferedImage drawMissingTexture(int width, int height) {
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		
		g2d.setColor(Color.magenta);
		g2d.fillRect(0, 0, width, height);
		g2d.setColor(Color.black);
		g2d.fillRect(width / 2, 0, width / 2, height / 2);
		g2d.fillRect(0, height / 2, width / 2, height / 2);
		g2d.dispose();
		
		return img;
	}
}
