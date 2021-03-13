package dev.codenmore.tilegame.gfx;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.tile.Tile;

public class GameCamera {
	
	private float xOffset;
	private float yOffset;
	private Handler handler;
	public GameCamera(Handler h, float x, float y) {
		xOffset = x;
		yOffset = y;
		handler = h;
	}
	
	public void checkBlankSpace() {
		if(xOffset<0) {
			xOffset = 0;
		}else if(xOffset>handler.getWorld().getWidth()* Tile.tileWidth - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth()* Tile.tileWidth- handler.getWidth();
		}
		if(yOffset<0) {
			yOffset = 0; 	
		}else if(yOffset>handler.getWorld().getHeight()*Tile.tileHeight - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight()*Tile.tileHeight - handler.getHeight();
		}
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX()-handler.getWidth()/2 + e.getWidth()/2;
		yOffset = e.getY()-handler.getHeight()/2 + e.getHeight()/2;
		checkBlankSpace();
	}
	
	public void move(float x, float y) {
		xOffset+=x;
		yOffset+=y;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
