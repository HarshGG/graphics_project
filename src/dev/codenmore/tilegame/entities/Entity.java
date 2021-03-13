package dev.codenmore.tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.codenmore.tilegame.Handler;

public abstract class Entity {

	protected float x,y;
	protected int width, height;	
	protected Handler handler;
	protected Rectangle bounds;
	protected boolean active = true;

	protected int health;
	public static final int defaultHealth = 3;
	
	public Entity(Handler ha, float a, float b, int w, int h) {
		handler = ha;
		x = a;
		y = b;
		width = w;
		height = h;
		bounds = new Rectangle(0,0, w, h);
		health = defaultHealth;
	}

	public abstract void tick();
		
	public abstract void render(Graphics g);
	
	public void hurt(int amt) {
		health-=amt;
		if(health<=0) {
			active = false;
		die();
		}
	}
	
	public abstract void die();
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x+ bounds.x + xOffset), (int)(y + bounds.y + yOffset),
				bounds.width, bounds.height);
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	//Getters and Setterss
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isActive() {
		return active;
	}
	
	
}
