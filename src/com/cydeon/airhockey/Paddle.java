package com.cydeon.airhockey;

import com.badlogic.androidgames.framework.DynamicGameObject;
import com.badlogic.androidgames.framework.math.Circle;

public class Paddle extends DynamicGameObject{
	public static float PADDLE_WIDTH = 1.5f;
	public static float PADDLE_HEIGHT = 1.5f;
	public static Circle circle;
	
	float startTime;
	
	public Paddle(float x, float y, float width, float height) {
		super(x, y, width, height);
		circle = new Circle(position.x, position.y, (float) PADDLE_WIDTH / 2);
	}
	
	public void update(float deltaTime) {
		bounds.lowerLeft.set(position).sub(PADDLE_WIDTH / 2, PADDLE_HEIGHT / 2);
		circle.center.set(position.x, position.y);
		
		if(position.x - PADDLE_WIDTH / 2 < 0)
			position.x = 0 + PADDLE_WIDTH / 2;
		
		if(position.x + PADDLE_WIDTH / 2 > World.WORLD_WIDTH) {
			position.x = World.WORLD_WIDTH - PADDLE_WIDTH / 2;
		}
		
		if(position.y + PADDLE_HEIGHT / 2 - 1 > World.WORLD_CENTER_LINE)
			position.y = World.WORLD_CENTER_LINE - PADDLE_HEIGHT / 2;
		
		if(position.y - PADDLE_HEIGHT / 2 < 0)
			position.y = 0 + PADDLE_HEIGHT / 2 - 0.2f;
				
		startTime += deltaTime;
	}

}
