package com.cydeon.airhockey;

import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	
	public static Texture items;
	public static TextureRegion paddle1;
	public static TextureRegion paddle2;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion puck;
	public static Texture menu;
	public static TextureRegion play;
	
	
	public static void load(GLGame game) {
		background = new Texture(game, "bg.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 600, 1024);
		
		menu = new Texture(game, "menu.png");
		items = new Texture(game, "items.png");
		puck = new TextureRegion(items, 0, 140, 72, 72);
		paddle1 = new TextureRegion(items, 0, 0, 140, 140);
		paddle2 = new TextureRegion(items, 140, 0, 144, 144);
		soundOn = new TextureRegion(items, 288, 0, 72, 72);
		soundOff = new TextureRegion(items, 360, 0, 72, 72);
		play = new TextureRegion(menu, 0, 0, 128, 128);
		
	}
	
	public static void reload() {
        background.reload();
        items.reload();
        menu.reload();
       // if(Settings.soundEnabled)
       //     music.play();
    }
	
}
