package com.cydeon.airhockey;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLGraphics;

public class WorldRender {
	static final float FRUSTUM_WIDTH = 8.33333f;
	static final float FRUSTUM_HEIGHT = 14.22222f;
	GLGraphics glGraphics;
	World world;
	Camera2D cam;
	SpriteBatcher batcher;

	public WorldRender(GLGraphics glGraphics, SpriteBatcher batcher, World world) {
		this.glGraphics = glGraphics;
		this.world = world;
		this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.batcher = batcher;
	}
	
	public void renderPreview() {
		cam.setViewportAndMatrices();
		renderObject();
	}

	public void render() {
		cam.setViewportAndMatrices();
		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		batcher.beginBatch(Assets.background);
		batcher.drawSprite(cam.position.x, cam.position.y, FRUSTUM_WIDTH,
				FRUSTUM_HEIGHT, Assets.backgroundRegion);
		batcher.endBatch();
	}

	public void renderObjects() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		batcher.beginBatch(Assets.items);
		renderPuck();
		renderPaddle();
		renderPaddleAi();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}
	
	public void renderObject() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		batcher.beginBatch(Assets.items);
		renderPuck();
		renderPaddleAi();
		renderPaddleAi2();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void renderPuck() {
		batcher.drawSprite(World.puck.position.x, World.puck.position.y,
				1, 1, Assets.puck);
	}
	
	private void renderPaddle() {
		batcher.drawSprite(World.paddle.position.x, World.paddle.position.y, 
				1.5f, 1.5f, Assets.paddle1);
	}
	
	private void renderPaddleAi () {
		batcher.drawSprite(World.paddleAi.position.x, World.paddleAi.position.y,
				1.5f, 1.5f, Assets.paddle2);
	}
	
	private void renderPaddleAi2() {
		batcher.drawSprite(World.paddleAi2.position.x, World.paddleAi2.position.y,
				1.5f, 1.5f, Assets.paddle1);
	}



}
