package com.cydeon.airhockey;

import android.util.FloatMath;
import android.util.Log;

import com.badlogic.androidgames.framework.math.Vector2;

public class World {
	public static float WORLD_WIDTH = 8.33333f;
	public static float WORLD_HEIGHT = 14.22222f;
	public static float WORLD_CENTER_LINE = WORLD_HEIGHT / 2;
	public static final int WORLD_STATE_RUNNING = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	
	public static int GAME_MODE_PREVIEW = 3;
	public static int GAME_MODE_PLAY = 4;
	
	public static int mode;

	public static Paddle paddle;
	public static PaddleAI paddleAi;
	public static PaddleAI2 paddleAi2;
	
	public static Puck puck;

	public int scoreAi;
	public int scoreP;
	public int state;

	public Vector2 direction;
	public Vector2 velocity;
	public Vector2 origin;
	public static int i, a, b, c, d, e, f, j;


	public World() {
		paddle = new Paddle(5, 5,
				Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
		paddleAi = new PaddleAI(WORLD_WIDTH / 2, WORLD_HEIGHT - 1,
				PaddleAI.PADDLE_WIDTH, PaddleAI.PADDLE_HEIGHT);
		paddleAi2 = new PaddleAI2(WORLD_WIDTH / 2, 1,
				PaddleAI.PADDLE_WIDTH, PaddleAI.PADDLE_HEIGHT);
		puck = new Puck(WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
		this.scoreAi = 0;
		this.scoreP = 0;
		this.state = WORLD_STATE_RUNNING;
		direction = new Vector2();
		velocity = new Vector2();
		origin = new Vector2();
		i = 0; a = 0; b = 0; c = 0; d = 0; e = 0; f = 0; j = 0;
	}

	public void update(float deltaTime, Vector2 touchPos) {
		if(touchPos.y + Paddle.PADDLE_HEIGHT / 2 < World.WORLD_CENTER_LINE)
		updatePaddle(deltaTime, touchPos);
		updatePuck(deltaTime);
		updatePaddleAI(deltaTime);
	}

	public void updatePuck(float deltaTime) {
		if (Puck.mode == Puck.PUCK_GAME_HIT_PADDLE) {
			j = 5;
			//Log.d("Position1", Float.toString(puck.position.x) + ", " + Float.toString(puck.velocity.y));
			//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
			if(b == 0) {
				origin.set(puck.position);
				puck.angle = puck.position.sub(paddle.position).angle();
				puck.position.set(origin);
				float radians = puck.angle * Vector2.TO_RADIANS;
				
				puck.velocity.x = (FloatMath.cos(radians) * 5) / 20;
				puck.velocity.y = (FloatMath.sin(radians) * 5) / 20;
				Log.d("Angle", Float.toString(puck.angle));
				//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
				//Log.d("Position2", Float.toString(puck.position.x) + ", " + Float.toString(puck.velocity.y));
			}
			puck.position.add(puck.velocity.x, puck.velocity.y);
			//Log.d("Position3", Float.toString(puck.position.x) + ", " + Float.toString(puck.velocity.y));
			if(b == 0)
			b++;
			Puck.mode = Puck.PUCK_GAME_ROAM;
		
		}
		
		if (Puck.mode == Puck.PUCK_GAME_HIT_PADDLEAI) {
			if(e == 0) {
				origin.set(puck.position);
				puck.angle = puck.position.sub(paddleAi.position).angle();
				puck.position.set(origin);
				float radians = puck.angle * Vector2.TO_RADIANS;
				
				puck.velocity.x = (FloatMath.cos(radians) * 5) / 20;
				puck.velocity.y = (FloatMath.sin(radians) * 5) / 20;
				//Log.d("Angle", Float.toString(puck.angle));
				//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
				//Log.d("Position2", Float.toString(puck.position.x) + ", " + Float.toString(puck.velocity.y));
			}
			puck.position.add(puck.velocity.x, puck.velocity.y);
			//Log.d("Position3", Float.toString(puck.position.x) + ", " + Float.toString(puck.velocity.y));
			if(e == 0)
			e++;
			Puck.mode = Puck.PUCK_GAME_ROAM;
		}
		
		if (Puck.mode == Puck.PUCK_GAME_HIT_PADDLEAI2) {
			if(f == 0) {
				origin.set(puck.position);
				puck.angle = puck.position.sub(paddleAi2.position).angle();
				puck.position.set(origin);
				float radians = puck.angle * Vector2.TO_RADIANS;
				
				puck.velocity.x = (FloatMath.cos(radians) * 5) / 20;
				puck.velocity.y = (FloatMath.sin(radians) * 5) / 20;
				//Log.d("Angle", Float.toString(puck.angle));
				//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
				//Log.d("Position2", Float.toString(puck.position.x) + ", " + Float.toString(puck.velocity.y));
			}
			puck.position.add(puck.velocity.x, puck.velocity.y);
			//Log.d("Position3", Float.toString(puck.position.x) + ", " + Float.toString(puck.velocity.y));
			f = 1;
			Puck.mode = Puck.PUCK_GAME_ROAM;
		}

		if (Puck.mode == Puck.PUCK_GAME_HIT_WALL_LEFT) {
			if(a == 0) {
			puck.velocity.set(-puck.velocity.x / 2, puck.velocity.y / 2);
			direction.set(puck.velocity.x, puck.velocity.y);
			puck.velocity.add(direction);
			}
			puck.position.add(puck.velocity.x, puck.velocity.y);
			//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
			a = 1;
			Puck.mode = Puck.PUCK_GAME_ROAM;
		}
		
		if (Puck.mode == Puck.PUCK_GAME_HIT_WALL_RIGHT) {
			if(d == 0) {
			puck.velocity.set(-puck.velocity.x / 2, puck.velocity.y / 2);
			direction.set(puck.velocity.x, puck.velocity.y);
			puck.velocity.add(direction);
			//Log.d("DID ONCE", "DID ONCE, d = " + Integer.toString(d));
			}
			puck.position.add(puck.velocity.x, puck.velocity.y);
			//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
			d = 1;
			Puck.mode = Puck.PUCK_GAME_ROAM;
		}

		if (Puck.mode == Puck.PUCK_GAME_GOAL_AI) {
			scoreAi += 1;
			if(c == 0) {
			puck.velocity.set(puck.velocity.x / 2, -puck.velocity.y / 2);
			direction.set(puck.velocity.x, puck.velocity.y);
			puck.velocity.add(direction);
			}			
			puck.position.add(puck.velocity.x, puck.velocity.y);
			c = 1;
			Puck.mode = Puck.PUCK_GAME_ROAM;
		}

		if (Puck.mode == Puck.PUCK_GAME_GOAL_PLAYER) {
			scoreP += 1;
			if(i == 0) {
			//	Log.d("I", "I = 0");
			/*if(j == 0) {
			//Log.d("J", "J = 0");
			puck.velocity.set(puck.velocity.x / 30, -puck.velocity.y / 30);
			direction.set(puck.velocity.x * 0.01f, puck.velocity.y * 0.01f);
			puck.velocity.add(direction);
			} else {*/
				puck.velocity.set(puck.velocity.x / 2, -puck.velocity.y / 2);
				direction.set(puck.velocity.x, puck.velocity.y);
				puck.velocity.add(direction);
			//}
			//Log.d("Did Once", "Did Once");
			j = 1;
			}
			puck.position.add(puck.velocity.x, puck.velocity.y);
			i = 5;
			//Log.d("Position", Float.toString(puck.position.x) + ", " + Float.toString(puck.position.y));
			//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
			Puck.mode = Puck.PUCK_GAME_ROAM;
			if(Puck.mode == Puck.PUCK_GAME_GOAL_PLAYER)
				Puck.mode = Puck.PUCK_GAME_ROAM;
		}
		
		if (Puck.mode == Puck.PUCK_GAME_ROAM) {
			puck.position.add(puck.velocity.x, puck.velocity.y);
			//Log.d("Mode", Float.toString(Puck.mode));
			//Log.d("Velocity", Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
		}
		
		if (puck.position.x > WORLD_WIDTH || puck.position.x < 0 ||
				puck.position.y + 0.1f > WORLD_HEIGHT) {
			puck.position.set(3, 3);
		}

		puck.update(deltaTime);
		//Log.d("Updated Puck", "Updated Puck");
		//Log.d("OldVector", " " + Float.toString(velo.x) + ", " + Float.toString(velo.y));
		//Log.d("NewVector", " " + Float.toString(dire.x) + ", " + Float.toString(dire.y));
		//Log.d("Velocity", " " +  Float.toString(puck.velocity.x) + ", " + Float.toString(puck.velocity.y));
		//Log.d("Puck Mode", Integer.toString(Puck.mode));
	}

	public void updatePaddle(float deltaTime, Vector2 touchPos) {
		if(touchPos.x <= 0 || touchPos.y <= 0) {
		paddle.position.set(touchPos);
		}
		paddle.update(deltaTime);
		//Log.d("Updated Paddle", "Updated Paddle");
	}
	
	public void updatePaddleAI(float deltaTime) {
		paddleAi.update(deltaTime);
	}
	
	public void updatePaddleAI2(float deltaTime) {
		paddleAi2.update(deltaTime);
	}
}
