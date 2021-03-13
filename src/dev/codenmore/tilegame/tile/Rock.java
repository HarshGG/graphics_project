package dev.codenmore.tilegame.tile;

import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.gfx.Assets;

public class Rock extends Tile{

	public Rock(int num) {
		super(Assets.rock, num);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
