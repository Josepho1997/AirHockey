package com.cydeon.airhockey;

import android.util.Log;

import com.badlogic.androidgames.framework.DynamicGameObject;
import com.badlogic.androidgames.framework.math.Circle;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class Puck extends DynamicGameObject {
	public static final float PUCK_WIDTH = 1;
	public static final float PUCK_HEIGHT = 1;
	public static final float PUCK_MIN_VELOCITY = 0.1f;
	public static final int PUCK_GAME_START = 1;
	public static final int PUCK_GAME_HIT_PADDLE = 2;
	public static final int PUCK_GAME_HIT_WALL_LEFT = 3;
	public static final int PUCK_GAME_GOAL_PLAYER = 4;
	public static final int PUCK_GAME_GOAL_AI = 5;
	public static final int PUCK_GAME_HIT_PADDLE_FIRST = 6;
	public static final int PUCK_GAME_ROAM = 7;
	public static final int PUCK_GAME_HIT_WALL_RIGHT = 8;
	public static final int PUCK_GAME_HIT_PADDLEAI = 9;
	public static final int PUCK_GAME_HIT_PADDLEAI2 = 10;
	public static boolean PUCK_GAME_JUST_STARTED = true;
	
	int i;
	public static int mode;
	public float angle;
	public static Circle circle;
	public static Rectangle r;
	Vector2 friction;

	float startTime = 0;

	public Puck(float x, float y) {
		super(x, y, PUCK_WIDTH, PUCK_HEIGHT);
		velocity.set(0, -PUCK_MIN_VELOCITY);
		mode = PUCK_GAME_START;
		circle = new Circle(position.x, position.y, (float) PUCK_WIDTH / 2 - 0.13f);
		mode = PUCK_GAME_START;
		angle = 0;
		i = 0;
		friction = new Vector2(0.0005f, 0.0005f);
	}

	public void update(float deltaTime) {
		if (mode == PUCK_GAME_START) {
			
			velocity.add(0, -3 * deltaTime);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		}

		if(World.mode == World.GAME_MODE_PLAY) {
		if (OverlapTester.overlapCircles(Puck.circle, Paddle.circle)) {
				if(mode != PUCK_GAME_HIT_PADDLE)
				mode = PUCK_GAME_HIT_PADDLE;
				World.b = 0;
				//Log.d("Paddle's` center", "Paddle's center: " + Float.toString(Paddle.circle.center.x) + ", " + Float.toString(Paddle.circle.center.y));
				//Log.d("Puck's center", "Puck's center: " + Float.toString(circle.center.x) + ", " + Float.toString(circle.center.y));
		}
		}
		
		if (OverlapTester.overlapCircles(Puck.circle, PaddleAI.circle)) {
			if(mode != PUCK_GAME_HIT_PADDLEAI)
				mode = PUCK_GAME_HIT_PADDLEAI;				
				World.e = 0;
		}
		
		if(World.mode == World.GAME_MODE_PREVIEW) {
		if (OverlapTester.overlapCircles(Puck.circle, PaddleAI2.circle)) {
			if(mode != PUCK_GAME_HIT_PADDLEAI2)
				mode = PUCK_GAME_HIT_PADDLEAI2;
			World.f = 0;
		}
		}
		
		if (position.x - PUCK_WIDTH / 2 < 0) {
			if(mode != PUCK_GAME_HIT_WALL_LEFT)
			mode = PUCK_GAME_HIT_WALL_LEFT;
		    World.a = 0;
		    Log.d("Test", Float.toString(position.x - PUCK_WIDTH / 2));
		}
		    

		if(position.x + PUCK_WIDTH / 2 > World.WORLD_WIDTH) {
			if(mode != PUCK_GAME_HIT_WALL_RIGHT)
			mode = PUCK_GAME_HIT_WALL_RIGHT;
			World.d = 0;
		}
		
		if (position.y + (float) PUCK_WIDTH / 2 - 0.1f > World.WORLD_HEIGHT) {
			if(mode != PUCK_GAME_GOAL_AI)
			mode = PUCK_GAME_GOAL_AI;
			World.c = 0;
		}

		if (position.y - (float)  PUCK_WIDTH / 2 + 0.1f < 0) {
			if(mode != PUCK_GAME_GOAL_PLAYER)
			mode = PUCK_GAME_GOAL_PLAYER;
			World.i = 0;
		}
		
		if (velocity.x < 0 && velocity.y < 0) {
			velocity.add(friction);
		}
		
		if (velocity.x > 0.0005f && velocity.y > 0.0005f)
			velocity.add(-friction.x, -friction.y);
		
		if (velocity.x > 0.0005f && velocity.y < 0.0005f)
			velocity.add(-friction.x, friction.y);
		
		if (velocity.x < 0.0005f && velocity.y > 0.0005f)
			velocity.add(friction.x, - friction.y);

		circle.center.set(position.x, position.y);
		startTime += deltaTime;
		Log.d("Puck mode", Integer.toString(mode));
		Log.d("Velocity", Float.toString(velocity.x) + ", " + Float.toString(velocity.y));
	}

}
