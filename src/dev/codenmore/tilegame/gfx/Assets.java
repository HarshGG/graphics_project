package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage dirt, grass, rock, tree, stone;
	public static BufferedImage[] player_down, player_up, player_right, player_left;
	public static BufferedImage[] zombie_down, zombie_up, zombie_right, zombie_left;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] break_effects;
	public static BufferedImage wood;
	
	private static final int width = 32, height = 32;
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite.png"));
		
		wood = sheet.crop(width, height, width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_down[0] = sheet.crop(width*4, 0, width, height);
		player_down[1] = sheet.crop(width*5, 0, width, height);

		player_up = new BufferedImage[2];
		player_up[0] = sheet.crop(width*6, 0, width, height);
		player_up[1] = sheet.crop(width*7, 0, width, height);

		player_left = new BufferedImage[2];
		player_left[0] = sheet.crop(width*6, height, width, height);
		player_left[1] = sheet.crop(width*7, height, width, height);

		player_right = new BufferedImage[2];
		player_right[0] = sheet.crop(width*4, height, width, height);
		player_right[1] = sheet.crop(width*5, height, width, height);
		
		break_effects = new BufferedImage[2];
		break_effects[0] = sheet.crop(width, height, width, height);
		break_effects[1] = sheet.crop(width*2, height, width, height);
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);

		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		rock = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height*2);	
		stone = sheet.crop(0, height * 2, width, height);
	}
	
	
}
