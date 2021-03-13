package dev.codenmore.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Tree;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> sorter1 = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
		if(a.getY() + a.getHeight()< b.getY()+b.getHeight())
			return -1;
		return 1;
		}
	};
	
	public EntityManager(Handler h, Player p) {
		handler = h;
		player = p;
		entities = new ArrayList<Entity>();
		addEntity(player);
		addEntity(new Tree(h, 100, 250));
		addEntity(new Tree(h, 200, 250));
	}
	
	public void tick() {
		Iterator<Entity> iter = entities.iterator();
		while(iter.hasNext()) {
			Entity e = iter.next();
			e.tick();
			if(!e.isActive())
				iter.remove();
		}
		entities.sort(sorter1);
	}
	
	public void render(Graphics g) {
		for(Entity e: entities) {
			e.render(g);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}


	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	
}
