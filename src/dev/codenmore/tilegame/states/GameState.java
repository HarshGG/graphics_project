package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.worlds.World;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler h) {
		super(h);
		world = new World(h,"res/worlds/world1.txt");
		handler.setWorld(world);
		
	}

	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}
