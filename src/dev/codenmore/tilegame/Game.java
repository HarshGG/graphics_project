package dev.codenmore.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.input.MouseManager;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;

public class Game implements Runnable{
	
	private int width, height;
	
	private Display display;
	private Thread thread;
	public String title;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//states
	public State gameState;
	public State menuState;
	
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public Game(String t, int w, int h) {
		width = w;
		height = h;
		title = t;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title,width,height);
		
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0,0);
	
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
		
	}
	
	
	private void tick() {
		keyManager.tick();
		
		if(State.getState()!=null)
			State.getState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if(State.getState()!=null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta+= (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta>=1) {
			tick();
			render();
			delta--;
			ticks++;
			}
			
			if(timer>=1000000000) {
				System.out.println("Ticks and Frames: "+ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getCamera() {
		return gameCamera;
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
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
