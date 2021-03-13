package dev.codenmore.tilegame.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> items;	
	
	
	public Inventory(Handler h) {
		handler = h;
		items = new ArrayList<Item>();
	}
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		if(!active)
			return;
		
		System.out.println("INV: ");
		
		for(Item i: items) {
			System.out.println(i.getName() + "  " + i.getCount());
		}
		
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
	}
	
	//inv methods
	public void addItem(Item item) {
		for(Item i: items) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount()+item.getCount());
			return;
			}
		}
		items.add(item);
	}
	
	
	// g and s
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
