package dev.codenmore.tilegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;

public class Item {
	
	//handler
	
	public static Item[] items = new Item[256];
	public static Item woodItem = new  Item(Assets.wood, "wood", 0);
	public static Item stoneItem = new  Item(Assets.stone, "stone", 1);
	
	
	//class
	
	public static final int itemWidth = 32, itemHeight = 32;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage i, String n, int num) {
		texture = i;
		name = n;
		id = num;
		count = 1;
		
		bounds  = new Rectangle(x, y, itemWidth, itemHeight);
		
		items[id] = this;
	}
	
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(bounds))
			pickedUp = true;
		handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this); 
	}
	
	public boolean isPickedUp() {
		return pickedUp;
	}

	public void render(Graphics g) {
		if(handler == null)
			return;
		render(g,(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()));
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, itemWidth, itemHeight, null);
	}
	
	public Item createNew(int a, int b) {
		Item i = new Item(texture, name, id);
		i.setPosition(a, b);
		return i;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	//g and s
	
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	
}
