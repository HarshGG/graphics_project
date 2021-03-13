package dev.codenmore.tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.inventory.Inventory;

public class Player extends Creature{
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	
	private Rectangle ar = new Rectangle();
	
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	private Inventory inventory;
	
	public Player(Handler ha, float a, float b ) {
		super(ha,a, b,Creature.defaultCreatureWidth,Creature.defaultCreatureHeight);
		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 19;
		bounds.height = 33;
		
		//Animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Anims
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		//movements
		getInput();
		move();
		handler.getCamera().centerOnEntity(this);
		//attak
		checkAttacks();
		//inventory
		inventory.tick();
		
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer<attackCooldown)
			return;
		
		Rectangle cb = getCollisionBounds(0,0);
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y - arSize;
		}
		else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y +cb.height;
		}
		else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y - cb.height/2 - arSize/2;
		}
		else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y - cb.height/2;
		}else {
			return;
		}
		
		attackTimer = 0;
		
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
			
		
	}
	
	public void die() {
		System.out.println("Take de L"); 
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int) (x-handler.getCamera().getxOffset()), (int) (y-handler.getCamera().getyOffset()), width, height, null);
		inventory.render(g);			
	}

	private BufferedImage getCurrentAnimationFrame() {
		if(xMove<0) {
			return animLeft.getCurrentFrame();
		}
		else if(xMove>0) {
			return animRight.getCurrentFrame();
		}
		else if(yMove<0) {
			return animUp.getCurrentFrame();
		}
		else{
			return animDown.getCurrentFrame();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
