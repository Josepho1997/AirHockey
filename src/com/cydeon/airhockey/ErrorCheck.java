package com.cydeon.airhockey;

public class ErrorCheck {
	World world;
	Paddle paddle;
	Puck puck;
	PaddleAI paddleAi;
	public static boolean puckError = false;
	public float startTime = 0;
	float[] posx = new float[50];
	float[] posy = new float[50];
	
	public ErrorCheck() {
		world = new World();
		paddle = new Paddle(World.paddle.position.x, World.paddle.position.y, 
				Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
		paddleAi = new PaddleAI(World.paddleAi.position.x, World.paddleAi.position.y, 
				PaddleAI.PADDLE_WIDTH, PaddleAI.PADDLE_HEIGHT);
		puck = new Puck(World.puck.position.x, World.puck.position.y);
	}
	
	public void checkPuckError(float deltaTime) {
		startTime += deltaTime;
		float x = puck.position.x;
		float y = puck.position.y;
		for(int i = 0; i < posx.length; i++) {
			posx[i] = x;
			for(int j = 0; j < posy.length; i++) {
				posy[i] = y;
			}
			
		}
		if(startTime > 5) {
			for(int i = 0; i < posx.length; i++) {
				if(posx[i] < 0 | posx[i] > World.WORLD_WIDTH)
					puckError = true;
			}
			for(int i = 0; i < posy.length; i++) {
				if(posy[i] < 0 | posy[i] > World.WORLD_HEIGHT)
					puckError = true;
			}
			
			startTime  -= 5;
		}
		
		if(puckError == true) {
			puck.position.set((float) 5 - 1 / 2,
				(float) (World.WORLD_HEIGHT / 2 - 2 / 2));
		}
		
	}
}
