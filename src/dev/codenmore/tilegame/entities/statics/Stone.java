package dev.codenmore.tilegame.entities.statics;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.tile.Tile;

public class Stone extends StaticEntity {

	public Stone(Handler handler, float x, float y) {
		super(handler, x, y, Tile.tileWidth, Tile.tileHeight);
		
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int)x,(int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.stone, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
	}

}