package dev.codenmore.tilegame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.codenmore.tilegame.Handler;

public class ItemManager {

	Handler handler;
	private ArrayList<Item> items;
	 
	public ItemManager(Handler h) {
		handler = h;
		items = new ArrayList<Item>();
	}
	
	public void tick() {
		Iterator<Item> iter = items.iterator();
		while(iter.hasNext()) {
			Item i = iter.next();
			i.tick();
			if(i.isPickedUp())
				iter.remove();
		}
	}
	
	public void render(Graphics g) {
		for(Item i: items)
			i.render(g);
	}

	public void addItem(Item i) {
		i.setHandler(handler);
		items.add(i);
	}

	//g and s
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
