package com.reaction.zombiesushi.util;

import org.andengine.engine.handler.IUpdateHandler;

import com.reaction.zombiesushi.GameScreen;
import com.reaction.zombiesushi.model.Layer;

public class GameScreenUpdateHandler implements IUpdateHandler {
	
	private float timer = 0;
	private GameScreen gameScreen;
	
	public GameScreenUpdateHandler(GameScreen gameScreen){
		this.gameScreen = gameScreen;  
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		timer += pSecondsElapsed;
		if (timer > 1) {
			if (NumberUtil.getRandomInt(100)> 65) {
				gameScreen.addZombie();
			}
			timer = 0;
		}
		if(this.gameScreen.getCook().isDead()){
			this.gameScreen.gameOver();
		}
		for(Layer layer: gameScreen.getLevel().getLayers()){
			layer.spawnObject();
		}
	}

	@Override
	public void reset() {
	}
	
	

}
