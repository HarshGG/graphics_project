package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;

public abstract class State {

	private static State currentState = null;
	
	public static void setState(State s) {
		currentState = s;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	public State(Handler h) {
		handler = h;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}

//ctrl shift o