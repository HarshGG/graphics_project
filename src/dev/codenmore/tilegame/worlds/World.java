package dev.codenmore.tilegame.worlds;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Stone;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.items.ItemManager;
import dev.codenmore.tilegame.tile.Tile;
import dev.codenmore.tilegame.utils.Utils;

public class World {
	
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Handler handler;
	//entities
	private EntityManager entityManager;
	
	ItemManager itemManager;
	
	public World(Handler h, String path) {
		handler = h;
		entityManager = new EntityManager(h, new Player(handler, 100, 100));
		itemManager = new ItemManager(h);
		
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Stone(handler, 100, 450));
		entityManager.addEntity(new Tree(handler, 100, 650));
		entityManager.addEntity(new Stone(handler, 100, 850));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}
	
	public void tick() {
		entityManager.tick();
		itemManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int)Math.max(0, handler.getCamera().getxOffset()/Tile.tileWidth);
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth())/Tile.tileWidth+1);
		int yStart = (int)Math.max(0, handler.getCamera().getyOffset()/Tile.tileHeight);
		int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight())/Tile.tileHeight+1);
		
		for(int y = yStart; y<yEnd;y++) {
			for(int x = xStart; x<xEnd; x++) {
				getTile(x,y).render(g, (int) (x*Tile.tileWidth - handler.getCamera().getxOffset()),
						(int) (y*Tile.tileHeight - handler.getCamera().getyOffset()));
			}
		}
		
		itemManager.render(g);
		
		entityManager.render(g);
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public Tile getTile(int x, int y) {
		if(x<0 || y<0 || x>=width || y>=height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t==null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width  = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y< height; y++) {
			for(int x = 0; x<width; x++) {
				tiles[x][y]= Utils.parseInt(tokens[(x + y * width)+4]);
			}
		}
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
