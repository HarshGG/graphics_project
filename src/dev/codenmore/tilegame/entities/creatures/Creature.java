package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.tile.Tile;

public abstract class Creature extends Entity{
	
	
	public static final float defaultSpeed = 3.0f;
	public static final int defaultCreatureWidth = 64,
							defaultCreatureHeight = 64;
	
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler ha, float a, float b, int w, int h) {
		super(ha, a, b, w, h);
		speed = defaultSpeed;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f)) 
			moveX();
		if(!checkEntityCollisions(0f, yMove)) 
		moveY();
	}

	public void moveX() {
		if(xMove>0) {//right
			int tx = (int) (x+xMove+bounds.x+bounds.width)/Tile.tileWidth;
			if(!collisionWithTile(tx, (int)(y+bounds.y)/Tile.tileHeight)&&
					!collisionWithTile(tx, (int)(y+bounds.y+bounds.height)/Tile.tileHeight)) {
				x+=xMove;
			}else {
				x = tx * Tile.tileWidth - bounds.x - bounds.width-1;
			}
 		}
		else if(xMove<0) {//left
			int tx = (int) (x+xMove+bounds.x)/Tile.tileWidth;
			if(!collisionWithTile(tx, (int)(y+bounds.y)/Tile.tileHeight)&&
					!collisionWithTile(tx, (int)(y+bounds.y+bounds.height)/Tile.tileHeight)) {
				x+=xMove;
			}else {
				x = tx * Tile.tileWidth + Tile.tileWidth - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove<0) {//up
			int ty = (int)(y+yMove+bounds.y)/Tile.tileHeight;
			
			if(!collisionWithTile((int)(x+bounds.x)/Tile.tileWidth, ty)&&
					!collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.tileWidth, ty)) {
				y+=yMove;
			}else {
				y = ty*Tile.tileHeight + Tile.tileHeight - bounds.y;
			}
		}
		else if(yMove>0) {//down
			int ty = (int)(y+yMove+bounds.y+bounds.height)/Tile.tileHeight;
			
			if(!collisionWithTile((int)(x+bounds.x)/Tile.tileWidth, ty)&&
					!collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.tileWidth, ty)) {
				y+=yMove;
			}else {
				y = ty*Tile.tileHeight - bounds.y - bounds.height-1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//getters and setter
	
	
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
