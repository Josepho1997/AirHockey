package com.cydeon.airhockey;

import android.util.Log;

import com.badlogic.androidgames.framework.DynamicGameObject;
import com.badlogic.androidgames.framework.math.Circle;

public class PaddleAI2 extends DynamicGameObject{
	public static float PADDLE_WIDTH = 1.5f;
	public static float PADDLE_HEIGHT = 1.5f;
	public static float CENTER = World.WORLD_WIDTH / 2;
	public static float MOVE_SPEED = 0.5f;
	public static Circle circle;
	int a = 0;
	int b = 0;
	//Paddle paddle;
	//Puck puck;
	
	float startTime;
	
	public PaddleAI2(float x, float y, float width, float height) {
		super(x, y, width, height);
		circle = new Circle(position.x, position.y, (float) PADDLE_WIDTH / 2);
		//paddle = new Paddle(paddle.position.x, paddle.position.y, 2, 2);
		//puck = new Puck(puck.position.x, puck.position.y);
	}
	
	public void update(float deltaTime) {
		bounds.lowerLeft.set(position).sub(PADDLE_WIDTH / 2, PADDLE_HEIGHT / 2);
		circle.center.set(position.x, position.y);
		
		if(World.puck.velocity.y > 0) {
			/*Log.d("v", "The puck is moving down");
			if(position.x < CENTER) {
				//Log.d("puck", "The puck is left of center");
				velocity.add(MOVE_SPEED, 0);
				if(position.x > CENTER)
					position.x = CENTER;
			}
			if(position.x > CENTER) {
				//Log.d("puck", "The puck is right of center");
				velocity.add(-MOVE_SPEED, 0);
				if(position.x < CENTER)
					position.x = CENTER;
			}*/
				if(position.y > 2)
				velocity.add(0, -MOVE_SPEED);
				if(position.y - PADDLE_WIDTH / 2 < 0)
					position.y = 0 + PADDLE_WIDTH / 2;
		}
		
		//if(World.puck.velocity.y > 0) {
		//	Log.d("v", "The puck is moving up");
			if(World.puck.position.x < position.x - 0.6f) {
				if(a == 0)
					//Log.d("Moving Left", "Moving Left");
				velocity.set(0, velocity.y);
				velocity.add(-MOVE_SPEED, 0);
				a = 1;
				b = 0;
			}
			
			if(World.puck.position.x > position.x + 0.6f) {
				if(b == 0)
				//	Log.d("Moving Right", "Moving Right");
				velocity.set(0, velocity.y);
				velocity.add(MOVE_SPEED, 0);
				b = 1;
				a = 0;
			}
			
			if(World.puck.position.x > position.x - 0.5f && World.puck.position.x < position.x + 0.5f) {
				if(World.puck.velocity.x < 0.0005f && World.puck.velocity.x > -0.0005f) {
					//Log.d("Dont Move", "Don't Move");
					velocity.x = 0;
				}
			}
			
			if(World.puck.position.y - Puck.PUCK_HEIGHT / 2 < World.WORLD_CENTER_LINE) {
				if(World.puck.position.y > position.y) {
					velocity.add(0, MOVE_SPEED);
				}		
			}
			
			if(World.puck.position.y < position.y) {
				if(position.y > 2)
				velocity.add(0, -MOVE_SPEED);
				if(position.y - PADDLE_HEIGHT / 2 < 0)
					position.y = 0 + PADDLE_HEIGHT / 2 - 0.2f;
			}
			
			if(position.y - PADDLE_HEIGHT / 2 < 0)
				position.y = 0 + PADDLE_HEIGHT / 2 - 0.2f;
		//}
		
		if(position.x - PADDLE_WIDTH / 2 < 0)
			position.x = 0 + PADDLE_WIDTH / 2;
		
		if(position.x + PADDLE_WIDTH / 2 > World.WORLD_WIDTH)
			position.x = World.WORLD_WIDTH - PADDLE_WIDTH / 2;
		
		if(position.y + PADDLE_HEIGHT / 2 > World.WORLD_CENTER_LINE)
			position.y = World.WORLD_CENTER_LINE - PADDLE_WIDTH / 2;
		
		if(position.y - PADDLE_HEIGHT / 2 < 0)
			position.y = 0 + PADDLE_HEIGHT / 2 - 0.2f;
		
		//Log.d("P", Float.toString(position.y) + ", " + Float.toString(position.x));
		
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		
	}

}
