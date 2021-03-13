package dev.codenmore.tilegame.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject{
	
	private BufferedImage[] images;
	private ClickListener clicker;

	public UIImageButton(float x, float y, int w, int h, BufferedImage[] i, ClickListener c) {
		super(x, y, w, h);
		images = i;
		clicker = c;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
