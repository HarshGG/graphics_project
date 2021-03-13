package dev.codenmore.tilegame.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//STATICS
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new Dirt(1);
	public static Tile RoctTile = new Rock(2);
	
	//CLASS
	public static final int tileWidth = 64, tileHeight = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int num) {
		this.texture = texture;
		id = num;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture,x,y,tileWidth,tileHeight,null);
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isSolid() {
		return false;
	}
}
