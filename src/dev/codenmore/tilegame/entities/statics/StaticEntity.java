package dev.codenmore.tilegame.entities.statics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity{

	public StaticEntity(Handler h, float x, float y, int w, int he) {
		super(h,x,y,w,he);
	}
	
	
}
