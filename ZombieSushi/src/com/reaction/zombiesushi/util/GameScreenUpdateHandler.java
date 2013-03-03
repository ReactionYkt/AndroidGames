package com.reaction.zombiesushi.util;

import java.util.Random;

import org.andengine.engine.handler.IUpdateHandler;

import com.reaction.zombiesushi.GameScreen;

public class GameScreenUpdateHandler implements IUpdateHandler {
	
	private Random random;
	private float timer = 0;
	private GameScreen gameScreen;
	
	public GameScreenUpdateHandler(GameScreen gameScreen){
		this.random = new Random();
		this.gameScreen = gameScreen;  
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		timer += pSecondsElapsed;
		if (timer > 1) {
			if (random.nextInt(100) > 65) {
				gameScreen.addZombie();
			}
			timer = 0;
		}
		if(this.gameScreen.getCook().isDead()){
			this.gameScreen.gameOver();
		}
	}

	@Override
	public void reset() {
	}
	
	

}
