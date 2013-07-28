package com.cydeon.airhockey;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class MainMenuScreen extends GLScreen{
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle soundBounds;
	Rectangle playBounds;
	Vector2 touchPoint;
	World world;
	WorldRender renderer;
	
	public MainMenuScreen(Game game) {
		super(game);
		guiCam = new Camera2D(glGraphics, 600, 1024);
		batcher = new SpriteBatcher(glGraphics, 100);
		soundBounds = new Rectangle(72, 1024 - 72, 72, 72);
		playBounds = new Rectangle((600 / 2) - 64, (1024 - 300) - 64, 128, 128);
		touchPoint = new Vector2();
		world = new World();
		renderer = new WorldRender(glGraphics, batcher, world);
		World.mode = World.GAME_MODE_PREVIEW;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        world.updatePaddleAI(deltaTime);
        world.updatePuck(deltaTime);
        world.updatePaddleAI2(deltaTime);
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                
                if(OverlapTester.pointInRectangle(playBounds, touchPoint)) {
                	game.setScreen(new GameScreen(game));
                	World.mode = World.GAME_MODE_PLAY;
                	return;
                }
                
                if(OverlapTester.pointInRectangle(soundBounds, touchPoint)) {
                	Settings.soundEnabled = !Settings.soundEnabled;
                	return;
                }
            }
        }
	}

	@Override
	public void present(float deltaTime) {
		 GL10 gl = glGraphics.getGL();        
	        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        guiCam.setViewportAndMatrices();
	        
	        gl.glEnable(GL10.GL_TEXTURE_2D);
	        
	      //  batcher.beginBatch(Assets.background);
	      //  batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
	      //  batcher.endBatch();
	        
	        
	        
	        gl.glEnable(GL10.GL_BLEND);
	        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	        
	        gl.glEnable(GL10.GL_BLEND);
	        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);               

	        batcher.beginBatch(Assets.background);
	        batcher.drawSprite(600 / 2, 1024 / 2, 600, 1024, Assets.backgroundRegion);
	        
	        batcher.endBatch();
	        
	        batcher.beginBatch(Assets.menu);                 
	        
	        batcher.drawSprite(600 / 2, 1024 - 300, 128, 128, Assets.play);
	      //  batcher.drawSprite(0, 0, 128, 128, Settings.soundEnabled?Assets.soundOn:Assets.soundOff);
	                
	        batcher.endBatch();
	        
	        renderer.renderPreview();
	        
	        gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
}
