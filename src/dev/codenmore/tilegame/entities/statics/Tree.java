package dev.codenmore.tilegame.entities.statics;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.tile.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler h, float x, float y) {
		super(h,x,y,Tile.tileWidth, Tile.tileHeight*2);
		
		bounds.x = 10;
		bounds.y = (int)(height/1.5f);
		bounds.width = width - 20;
		bounds.height = (int)(height - height/1.5f);
	}
	@Override
	public void tick() {
		
	}
	
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x,(int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree,(int) (x- handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()), width, height, null);
	}
	

}
