package com.cydeon.airhockey;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.view.GestureDetector.OnGestureListener;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.Vector2;

public class GameScreen extends GLScreen{
		Camera2D guiCam;
	    Vector2 touchPoint;
	    SpriteBatcher batcher;    
	    World world;
	    WorldRender renderer;
	    Vector2 oldV;
	    Vector2 newV;
	    Puck puck;
	    Paddle paddle;
	
	public GameScreen(Game game) {
		super(game);
		guiCam = new Camera2D(glGraphics, World.WORLD_WIDTH, World.WORLD_HEIGHT);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
        world = new World();
        renderer = new WorldRender(glGraphics, batcher, world);
        oldV = new Vector2();
        newV = new Vector2();
        puck = World.puck;
        paddle = World.paddle;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        oldV = puck.position;
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_DOWN | event.type == TouchEvent.TOUCH_DRAGGED) {
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);
			if(touchPoint.y + Paddle.PADDLE_HEIGHT / 2 < World.WORLD_CENTER_LINE)
            paddle.position.set(touchPoint);
        }
        
        }		
        newV = touchPoint;
       // Log.d("First?", "OldV: " + Float.toString(oldV.x) + ", " + Float.toString(oldV.y) + " NewV: " + Float.toString(newV.x) + ", " + Float.toString(newV.y));
        world.update(deltaTime, touchPoint);
        oldV = touchPoint;
//       / Log.d("Second?", "OldV: " + Float.toString(oldV.x) + ", " + Float.toString(oldV.y) + " NewV: " + Float.toString(newV.x) + ", " + Float.toString(newV.y));
	}

	@Override
	public void present(float deltaTime) {
		 GL10 gl = glGraphics.getGL();
	        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        gl.glEnable(GL10.GL_TEXTURE_2D);
	        
	        renderer.render();
	        
	        guiCam.setViewportAndMatrices();
	        gl.glEnable(GL10.GL_BLEND);
	        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
